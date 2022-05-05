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

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.input.ClosedInputStream;

public abstract class AbstractByteArrayOutputStream extends OutputStream {
    static final int DEFAULT_SIZE = 1024;
    
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    
    private final List<byte[]> buffers = (List)new ArrayList<>();
    
    private int currentBufferIndex;
    
    private int filledBufferSum;
    
    private byte[] currentBuffer;
    
    protected int count;
    
    private boolean reuseBuffers = true;
    
    protected void needNewBuffer(int newcount) {
        if (this.currentBufferIndex < this.buffers.size() - 1) {
            this.filledBufferSum += this.currentBuffer.length;
            this.currentBufferIndex++;
            this.currentBuffer = this.buffers.get(this.currentBufferIndex);
        } else {
            int newBufferSize;
            if (this.currentBuffer == null) {
                newBufferSize = newcount;
                this.filledBufferSum = 0;
            } else {
                newBufferSize = Math.max(this.currentBuffer.length << 1, newcount - this.filledBufferSum);
                this.filledBufferSum += this.currentBuffer.length;
            } 
            this.currentBufferIndex++;
            this.currentBuffer = new byte[newBufferSize];
            this.buffers.add(this.currentBuffer);
        } 
    }
    
    public abstract void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
    
    protected void writeImpl(byte[] b, int off, int len) {
        int newcount = this.count + len;
        int remaining = len;
        int inBufferPos = this.count - this.filledBufferSum;
        while (remaining > 0) {
            int part = Math.min(remaining, this.currentBuffer.length - inBufferPos);
            System.arraycopy(b, off + len - remaining, this.currentBuffer, inBufferPos, part);
            remaining -= part;
            if (remaining > 0) {
                needNewBuffer(newcount);
                inBufferPos = 0;
            } 
        } 
        this.count = newcount;
    }
    
    public abstract void write(int paramInt);
    
    protected void writeImpl(int b) {
        int inBufferPos = this.count - this.filledBufferSum;
        if (inBufferPos == this.currentBuffer.length) {
            needNewBuffer(this.count + 1);
            inBufferPos = 0;
        } 
        this.currentBuffer[inBufferPos] = (byte)b;
        this.count++;
    }
    
    public abstract int write(InputStream paramInputStream) throws IOException;
    
    protected int writeImpl(InputStream in) throws IOException {
        int readCount = 0;
        int inBufferPos = this.count - this.filledBufferSum;
        int n = in.read(this.currentBuffer, inBufferPos, this.currentBuffer.length - inBufferPos);
        while (n != -1) {
            readCount += n;
            inBufferPos += n;
            this.count += n;
            if (inBufferPos == this.currentBuffer.length) {
                needNewBuffer(this.currentBuffer.length);
                inBufferPos = 0;
            } 
            n = in.read(this.currentBuffer, inBufferPos, this.currentBuffer.length - inBufferPos);
        } 
        return readCount;
    }
    
    public abstract int size();
    
    public void close() throws IOException {}
    
    public abstract void reset();
    
    protected void resetImpl() {
        this.count = 0;
        this.filledBufferSum = 0;
        this.currentBufferIndex = 0;
        if (this.reuseBuffers) {
            this.currentBuffer = this.buffers.get(this.currentBufferIndex);
        } else {
            this.currentBuffer = null;
            int size = ((byte[])this.buffers.get(0)).length;
            this.buffers.clear();
            needNewBuffer(size);
            this.reuseBuffers = true;
        } 
    }
    
    public abstract void writeTo(OutputStream paramOutputStream) throws IOException;
    
    protected void writeToImpl(OutputStream out) throws IOException {
        int remaining = this.count;
        for (byte[] buf : this.buffers) {
            int c = Math.min(buf.length, remaining);
            out.write(buf, 0, c);
            remaining -= c;
            if (remaining == 0)
                break; 
        } 
    }
    
    public abstract InputStream toInputStream();
    
    protected <T extends InputStream> InputStream toInputStream(InputStreamConstructor<T> isConstructor) {
        int remaining = this.count;
        if (remaining == 0)
            return (InputStream)ClosedInputStream.CLOSED_INPUT_STREAM; 
        List<T> list = new ArrayList<>(this.buffers.size());
        for (byte[] buf : this.buffers) {
            int c = Math.min(buf.length, remaining);
            list.add(isConstructor.construct(buf, 0, c));
            remaining -= c;
            if (remaining == 0)
                break; 
        } 
        this.reuseBuffers = false;
        return new SequenceInputStream(Collections.enumeration(list));
    }
    
    public abstract byte[] toByteArray();
    
    protected byte[] toByteArrayImpl() {
        int remaining = this.count;
        if (remaining == 0)
            return EMPTY_BYTE_ARRAY; 
        byte[] newbuf = new byte[remaining];
        int pos = 0;
        for (byte[] buf : this.buffers) {
            int c = Math.min(buf.length, remaining);
            System.arraycopy(buf, 0, newbuf, pos, c);
            pos += c;
            remaining -= c;
            if (remaining == 0)
                break; 
        } 
        return newbuf;
    }
    
    @Deprecated
    public String toString() {
        return new String(toByteArray(), Charset.defaultCharset());
    }
    
    public String toString(String enc) throws UnsupportedEncodingException {
        return new String(toByteArray(), enc);
    }
    
    public String toString(Charset charset) {
        return new String(toByteArray(), charset);
    }
    
    @FunctionalInterface
    protected static interface InputStreamConstructor<T extends InputStream> {
        T construct(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2);
    }
}
