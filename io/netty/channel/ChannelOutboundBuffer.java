/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public final class ChannelOutboundBuffer {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelOutboundBuffer.class);
    
    private static final FastThreadLocal<ByteBuffer[]> NIO_BUFFERS = new FastThreadLocal<ByteBuffer[]>() {
            protected ByteBuffer[] initialValue() throws Exception {
                return new ByteBuffer[1024];
            }
        };
    
    private final Channel channel;
    
    private Entry flushedEntry;
    
    private Entry unflushedEntry;
    
    private Entry tailEntry;
    
    private int flushed;
    
    private int nioBufferCount;
    
    private long nioBufferSize;
    
    private boolean inFail;
    
    private static final AtomicLongFieldUpdater<ChannelOutboundBuffer> TOTAL_PENDING_SIZE_UPDATER;
    
    private volatile long totalPendingSize;
    
    private static final AtomicIntegerFieldUpdater<ChannelOutboundBuffer> WRITABLE_UPDATER;
    
    private volatile int writable = 1;
    
    static {
        AtomicIntegerFieldUpdater<ChannelOutboundBuffer> writableUpdater = PlatformDependent.newAtomicIntegerFieldUpdater(ChannelOutboundBuffer.class, "writable");
        if (writableUpdater == null)
            writableUpdater = AtomicIntegerFieldUpdater.newUpdater(ChannelOutboundBuffer.class, "writable"); 
        WRITABLE_UPDATER = writableUpdater;
        AtomicLongFieldUpdater<ChannelOutboundBuffer> pendingSizeUpdater = PlatformDependent.newAtomicLongFieldUpdater(ChannelOutboundBuffer.class, "totalPendingSize");
        if (pendingSizeUpdater == null)
            pendingSizeUpdater = AtomicLongFieldUpdater.newUpdater(ChannelOutboundBuffer.class, "totalPendingSize"); 
        TOTAL_PENDING_SIZE_UPDATER = pendingSizeUpdater;
    }
    
    ChannelOutboundBuffer(AbstractChannel channel) {
        this.channel = channel;
    }
    
    public void addMessage(Object msg, int size, ChannelPromise promise) {
        Entry entry = Entry.newInstance(msg, size, total(msg), promise);
        if (this.tailEntry == null) {
            this.flushedEntry = null;
            this.tailEntry = entry;
        } else {
            Entry tail = this.tailEntry;
            tail.next = entry;
            this.tailEntry = entry;
        } 
        if (this.unflushedEntry == null)
            this.unflushedEntry = entry; 
        incrementPendingOutboundBytes(size);
    }
    
    public void addFlush() {
        Entry entry = this.unflushedEntry;
        if (entry != null) {
            if (this.flushedEntry == null)
                this.flushedEntry = entry; 
            do {
                this.flushed++;
                if (!entry.promise.setUncancellable()) {
                    int pending = entry.cancel();
                    decrementPendingOutboundBytes(pending);
                } 
                entry = entry.next;
            } while (entry != null);
            this.unflushedEntry = null;
        } 
    }
    
    void incrementPendingOutboundBytes(long size) {
        if (size == 0L)
            return; 
        long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, size);
        if (newWriteBufferSize > this.channel.config().getWriteBufferHighWaterMark() && 
            WRITABLE_UPDATER.compareAndSet(this, 1, 0))
            this.channel.pipeline().fireChannelWritabilityChanged(); 
    }
    
    void decrementPendingOutboundBytes(long size) {
        if (size == 0L)
            return; 
        long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -size);
        if ((newWriteBufferSize == 0L || newWriteBufferSize < this.channel.config().getWriteBufferLowWaterMark()) && 
            WRITABLE_UPDATER.compareAndSet(this, 0, 1))
            this.channel.pipeline().fireChannelWritabilityChanged(); 
    }
    
    private static long total(Object msg) {
        if (msg instanceof ByteBuf)
            return ((ByteBuf)msg).readableBytes(); 
        if (msg instanceof FileRegion)
            return ((FileRegion)msg).count(); 
        if (msg instanceof ByteBufHolder)
            return ((ByteBufHolder)msg).content().readableBytes(); 
        return -1L;
    }
    
    public Object current() {
        Entry entry = this.flushedEntry;
        if (entry == null)
            return null; 
        return entry.msg;
    }
    
    public void progress(long amount) {
        Entry e = this.flushedEntry;
        assert e != null;
        ChannelPromise p = e.promise;
        if (p instanceof ChannelProgressivePromise) {
            long progress = e.progress + amount;
            e.progress = progress;
            ((ChannelProgressivePromise)p).tryProgress(progress, e.total);
        } 
    }
    
    public boolean remove() {
        Entry e = this.flushedEntry;
        if (e == null)
            return false; 
        Object msg = e.msg;
        ChannelPromise promise = e.promise;
        int size = e.pendingSize;
        removeEntry(e);
        if (!e.cancelled) {
            ReferenceCountUtil.safeRelease(msg);
            safeSuccess(promise);
            decrementPendingOutboundBytes(size);
        } 
        e.recycle();
        return true;
    }
    
    public boolean remove(Throwable cause) {
        Entry e = this.flushedEntry;
        if (e == null)
            return false; 
        Object msg = e.msg;
        ChannelPromise promise = e.promise;
        int size = e.pendingSize;
        removeEntry(e);
        if (!e.cancelled) {
            ReferenceCountUtil.safeRelease(msg);
            safeFail(promise, cause);
            decrementPendingOutboundBytes(size);
        } 
        e.recycle();
        return true;
    }
    
    private void removeEntry(Entry e) {
        if (--this.flushed == 0) {
            this.flushedEntry = null;
            if (e == this.tailEntry) {
                this.tailEntry = null;
                this.unflushedEntry = null;
            } 
        } else {
            this.flushedEntry = e.next;
        } 
    }
    
    public void removeBytes(long writtenBytes) {
        while (true) {
            Object msg = current();
            if (!(msg instanceof ByteBuf)) {
                assert writtenBytes == 0L;
                break;
            } 
            ByteBuf buf = (ByteBuf)msg;
            int readerIndex = buf.readerIndex();
            int readableBytes = buf.writerIndex() - readerIndex;
            if (readableBytes <= writtenBytes) {
                if (writtenBytes != 0L) {
                    progress(readableBytes);
                    writtenBytes -= readableBytes;
                } 
                remove();
                continue;
            } 
            if (writtenBytes != 0L) {
                buf.readerIndex(readerIndex + (int)writtenBytes);
                progress(writtenBytes);
            } 
            break;
        } 
    }
    
    public ByteBuffer[] nioBuffers() {
        long nioBufferSize = 0L;
        int nioBufferCount = 0;
        InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.get();
        ByteBuffer[] nioBuffers = (ByteBuffer[])NIO_BUFFERS.get(threadLocalMap);
        Entry entry = this.flushedEntry;
        while (isFlushedEntry(entry) && entry.msg instanceof ByteBuf) {
            if (!entry.cancelled) {
                ByteBuf buf = (ByteBuf)entry.msg;
                int readerIndex = buf.readerIndex();
                int readableBytes = buf.writerIndex() - readerIndex;
                if (readableBytes > 0) {
                    nioBufferSize += readableBytes;
                    int count = entry.count;
                    if (count == -1)
                        entry.count = count = buf.nioBufferCount(); 
                    int neededSpace = nioBufferCount + count;
                    if (neededSpace > nioBuffers.length) {
                        nioBuffers = expandNioBufferArray(nioBuffers, neededSpace, nioBufferCount);
                        NIO_BUFFERS.set(threadLocalMap, nioBuffers);
                    } 
                    if (count == 1) {
                        ByteBuffer nioBuf = entry.buf;
                        if (nioBuf == null)
                            entry.buf = nioBuf = buf.internalNioBuffer(readerIndex, readableBytes); 
                        nioBuffers[nioBufferCount++] = nioBuf;
                    } else {
                        ByteBuffer[] nioBufs = entry.bufs;
                        if (nioBufs == null)
                            entry.bufs = nioBufs = buf.nioBuffers(); 
                        nioBufferCount = fillBufferArray(nioBufs, nioBuffers, nioBufferCount);
                    } 
                } 
            } 
            entry = entry.next;
        } 
        this.nioBufferCount = nioBufferCount;
        this.nioBufferSize = nioBufferSize;
        return nioBuffers;
    }
    
    private static int fillBufferArray(ByteBuffer[] nioBufs, ByteBuffer[] nioBuffers, int nioBufferCount) {
        for (ByteBuffer nioBuf : nioBufs) {
            if (nioBuf == null)
                break; 
            nioBuffers[nioBufferCount++] = nioBuf;
        } 
        return nioBufferCount;
    }
    
    private static ByteBuffer[] expandNioBufferArray(ByteBuffer[] array, int neededSpace, int size) {
        int newCapacity = array.length;
        do {
            newCapacity <<= 1;
            if (newCapacity < 0)
                throw new IllegalStateException(); 
        } while (neededSpace > newCapacity);
        ByteBuffer[] newArray = new ByteBuffer[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }
    
    public int nioBufferCount() {
        return this.nioBufferCount;
    }
    
    public long nioBufferSize() {
        return this.nioBufferSize;
    }
    
    boolean isWritable() {
        return (this.writable != 0);
    }
    
    public int size() {
        return this.flushed;
    }
    
    public boolean isEmpty() {
        return (this.flushed == 0);
    }
    
    void failFlushed(Throwable cause) {
        if (this.inFail)
            return; 
        try {
            this.inFail = true;
            do {
            
            } while (remove(cause));
        } finally {
            this.inFail = false;
        } 
    }
    
    void close(final ClosedChannelException cause) {
        if (this.inFail) {
            this.channel.eventLoop().execute(new Runnable() {
                        public void run() {
                            ChannelOutboundBuffer.this.close(cause);
                        }
                    });
            return;
        } 
        this.inFail = true;
        if (this.channel.isOpen())
            throw new IllegalStateException("close() must be invoked after the channel is closed."); 
        if (!isEmpty())
            throw new IllegalStateException("close() must be invoked after all flushed writes are handled."); 
        try {
            Entry e = this.unflushedEntry;
            while (e != null) {
                int size = e.pendingSize;
                TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -size);
                if (!e.cancelled) {
                    ReferenceCountUtil.safeRelease(e.msg);
                    safeFail(e.promise, cause);
                } 
                e = e.recycleAndGetNext();
            } 
        } finally {
            this.inFail = false;
        } 
    }
    
    private static void safeSuccess(ChannelPromise promise) {
        if (!(promise instanceof VoidChannelPromise) && !promise.trySuccess())
            logger.warn("Failed to mark a promise as success because it is done already: {}", promise); 
    }
    
    private static void safeFail(ChannelPromise promise, Throwable cause) {
        if (!(promise instanceof VoidChannelPromise) && !promise.tryFailure(cause))
            logger.warn("Failed to mark a promise as failure because it's done already: {}", promise, cause); 
    }
    
    @Deprecated
    public void recycle() {}
    
    public long totalPendingWriteBytes() {
        return this.totalPendingSize;
    }
    
    public void forEachFlushedMessage(MessageProcessor processor) throws Exception {
        if (processor == null)
            throw new NullPointerException("processor"); 
        Entry entry = this.flushedEntry;
        if (entry == null)
            return; 
        do {
            if (!entry.cancelled && 
                !processor.processMessage(entry.msg))
                return; 
            entry = entry.next;
        } while (isFlushedEntry(entry));
    }
    
    private boolean isFlushedEntry(Entry e) {
        return (e != null && e != this.unflushedEntry);
    }
    
    public static interface MessageProcessor {
        boolean processMessage(Object param1Object) throws Exception;
    }
    
    static final class Entry {
        private static final Recycler<Entry> RECYCLER = new Recycler<Entry>() {
                protected ChannelOutboundBuffer.Entry newObject(Recycler.Handle handle) {
                    return new ChannelOutboundBuffer.Entry(handle);
                }
            };
        
        private final Recycler.Handle handle;
        
        Entry next;
        
        Object msg;
        
        ByteBuffer[] bufs;
        
        ByteBuffer buf;
        
        ChannelPromise promise;
        
        long progress;
        
        long total;
        
        int pendingSize;
        
        int count = -1;
        
        boolean cancelled;
        
        private Entry(Recycler.Handle handle) {
            this.handle = handle;
        }
        
        static Entry newInstance(Object msg, int size, long total, ChannelPromise promise) {
            Entry entry = (Entry)RECYCLER.get();
            entry.msg = msg;
            entry.pendingSize = size;
            entry.total = total;
            entry.promise = promise;
            return entry;
        }
        
        int cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                int pSize = this.pendingSize;
                ReferenceCountUtil.safeRelease(this.msg);
                this.msg = Unpooled.EMPTY_BUFFER;
                this.pendingSize = 0;
                this.total = 0L;
                this.progress = 0L;
                this.bufs = null;
                this.buf = null;
                return pSize;
            } 
            return 0;
        }
        
        void recycle() {
            this.next = null;
            this.bufs = null;
            this.buf = null;
            this.msg = null;
            this.promise = null;
            this.progress = 0L;
            this.total = 0L;
            this.pendingSize = 0;
            this.count = -1;
            this.cancelled = false;
            RECYCLER.recycle(this, this.handle);
        }
        
        Entry recycleAndGetNext() {
            Entry next = this.next;
            recycle();
            return next;
        }
    }
}