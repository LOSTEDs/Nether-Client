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

package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;

public class UnsynchronizedByteArrayInputStream extends InputStream {
    public static final int END_OF_STREAM = -1;
    
    private final byte[] data;
    
    private final int eod;
    
    private int offset;
    
    private int markedOffset;
    
    public UnsynchronizedByteArrayInputStream(byte[] data) {
        Objects.requireNonNull(data);
        this.data = data;
        this.offset = 0;
        this.eod = data.length;
        this.markedOffset = this.offset;
    }
    
    public UnsynchronizedByteArrayInputStream(byte[] data, int offset) {
        Objects.requireNonNull(data);
        if (offset < 0)
            throw new IllegalArgumentException("offset cannot be negative"); 
        this.data = data;
        this.offset = Math.min(offset, (data.length > 0) ? data.length : offset);
        this.eod = data.length;
        this.markedOffset = this.offset;
    }
    
    public UnsynchronizedByteArrayInputStream(byte[] data, int offset, int length) {
        Objects.requireNonNull(data);
        if (offset < 0)
            throw new IllegalArgumentException("offset cannot be negative"); 
        if (length < 0)
            throw new IllegalArgumentException("length cannot be negative"); 
        this.data = data;
        this.offset = Math.min(offset, (data.length > 0) ? data.length : offset);
        this.eod = Math.min(this.offset + length, data.length);
        this.markedOffset = this.offset;
    }
    
    public int available() {
        return (this.offset < this.eod) ? (this.eod - this.offset) : 0;
    }
    
    public int read() {
        return (this.offset < this.eod) ? (this.data[this.offset++] & 0xFF) : -1;
    }
    
    public int read(byte[] b) {
        Objects.requireNonNull(b);
        return read(b, 0, b.length);
    }
    
    public int read(byte[] b, int off, int len) {
        Objects.requireNonNull(b);
        if (off < 0 || len < 0 || off + len > b.length)
            throw new IndexOutOfBoundsException(); 
        if (this.offset >= this.eod)
            return -1; 
        int actualLen = this.eod - this.offset;
        if (len < actualLen)
            actualLen = len; 
        if (actualLen <= 0)
            return 0; 
        System.arraycopy(this.data, this.offset, b, off, actualLen);
        this.offset += actualLen;
        return actualLen;
    }
    
    public long skip(long n) {
        if (n < 0L)
            throw new IllegalArgumentException("Skipping backward is not supported"); 
        long actualSkip = (this.eod - this.offset);
        if (n < actualSkip)
            actualSkip = n; 
        this.offset = (int)(this.offset + actualSkip);
        return actualSkip;
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public void mark(int readlimit) {
        this.markedOffset = this.offset;
    }
    
    public void reset() {
        this.offset = this.markedOffset;
    }
}
