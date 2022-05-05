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

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.jboss.marshalling.ByteInput;

class ChannelBufferByteInput implements ByteInput {
    private final ByteBuf buffer;
    
    ChannelBufferByteInput(ByteBuf buffer) {
        this.buffer = buffer;
    }
    
    public void close() throws IOException {}
    
    public int available() throws IOException {
        return this.buffer.readableBytes();
    }
    
    public int read() throws IOException {
        if (this.buffer.isReadable())
            return this.buffer.readByte() & 0xFF; 
        return -1;
    }
    
    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }
    
    public int read(byte[] dst, int dstIndex, int length) throws IOException {
        int available = available();
        if (available == 0)
            return -1; 
        length = Math.min(available, length);
        this.buffer.readBytes(dst, dstIndex, length);
        return length;
    }
    
    public long skip(long bytes) throws IOException {
        int readable = this.buffer.readableBytes();
        if (readable < bytes)
            bytes = readable; 
        this.buffer.readerIndex((int)(this.buffer.readerIndex() + bytes));
        return bytes;
    }
}
