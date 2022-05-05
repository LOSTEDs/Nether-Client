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
import org.jboss.marshalling.ByteOutput;

class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;
    
    ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }
    
    public void close() throws IOException {}
    
    public void flush() throws IOException {}
    
    public void write(int b) throws IOException {
        this.buffer.writeByte(b);
    }
    
    public void write(byte[] bytes) throws IOException {
        this.buffer.writeBytes(bytes);
    }
    
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        this.buffer.writeBytes(bytes, srcIndex, length);
    }
    
    ByteBuf getBuffer() {
        return this.buffer;
    }
}
