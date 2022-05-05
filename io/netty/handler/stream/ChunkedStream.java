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

package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class ChunkedStream implements ChunkedInput<ByteBuf> {
    static final int DEFAULT_CHUNK_SIZE = 8192;
    
    private final PushbackInputStream in;
    
    private final int chunkSize;
    
    private long offset;
    
    public ChunkedStream(InputStream in) {
        this(in, 8192);
    }
    
    public ChunkedStream(InputStream in, int chunkSize) {
        if (in == null)
            throw new NullPointerException("in"); 
        if (chunkSize <= 0)
            throw new IllegalArgumentException("chunkSize: " + chunkSize + " (expected: a positive integer)"); 
        if (in instanceof PushbackInputStream) {
            this.in = (PushbackInputStream)in;
        } else {
            this.in = new PushbackInputStream(in);
        } 
        this.chunkSize = chunkSize;
    }
    
    public long transferredBytes() {
        return this.offset;
    }
    
    public boolean isEndOfInput() throws Exception {
        int b = this.in.read();
        if (b < 0)
            return true; 
        this.in.unread(b);
        return false;
    }
    
    public void close() throws Exception {
        this.in.close();
    }
    
    public ByteBuf readChunk(ChannelHandlerContext ctx) throws Exception {
        int chunkSize;
        if (isEndOfInput())
            return null; 
        int availableBytes = this.in.available();
        if (availableBytes <= 0) {
            chunkSize = this.chunkSize;
        } else {
            chunkSize = Math.min(this.chunkSize, this.in.available());
        } 
        boolean release = true;
        ByteBuf buffer = ctx.alloc().buffer(chunkSize);
        try {
            this.offset += buffer.writeBytes(this.in, chunkSize);
            release = false;
            return buffer;
        } finally {
            if (release)
                buffer.release(); 
        } 
    }
}
