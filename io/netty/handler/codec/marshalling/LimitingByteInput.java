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

package io.netty.handler.codec.marshalling;

import java.io.IOException;
import org.jboss.marshalling.ByteInput;

class LimitingByteInput implements ByteInput {
    private static final TooBigObjectException EXCEPTION = new TooBigObjectException();
    
    private final ByteInput input;
    
    private final long limit;
    
    private long read;
    
    LimitingByteInput(ByteInput input, long limit) {
        if (limit <= 0L)
            throw new IllegalArgumentException("The limit MUST be > 0"); 
        this.input = input;
        this.limit = limit;
    }
    
    public void close() throws IOException {}
    
    public int available() throws IOException {
        return readable(this.input.available());
    }
    
    public int read() throws IOException {
        int readable = readable(1);
        if (readable > 0) {
            int b = this.input.read();
            this.read++;
            return b;
        } 
        throw EXCEPTION;
    }
    
    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }
    
    public int read(byte[] array, int offset, int length) throws IOException {
        int readable = readable(length);
        if (readable > 0) {
            int i = this.input.read(array, offset, readable);
            this.read += i;
            return i;
        } 
        throw EXCEPTION;
    }
    
    public long skip(long bytes) throws IOException {
        int readable = readable((int)bytes);
        if (readable > 0) {
            long i = this.input.skip(readable);
            this.read += i;
            return i;
        } 
        throw EXCEPTION;
    }
    
    private int readable(int length) {
        return (int)Math.min(length, this.limit - this.read);
    }
    
    static final class TooBigObjectException extends IOException {
        private static final long serialVersionUID = 1L;
    }
}
