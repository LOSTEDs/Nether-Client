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

package org.apache.commons.io.input.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class PeekableInputStream extends CircularBufferInputStream {
    public PeekableInputStream(InputStream inputStream, int bufferSize) {
        super(inputStream, bufferSize);
    }
    
    public PeekableInputStream(InputStream inputStream) {
        super(inputStream);
    }
    
    public boolean peek(byte[] sourceBuffer) throws IOException {
        Objects.requireNonNull(sourceBuffer, "Buffer");
        if (sourceBuffer.length > this.bufferSize)
            throw new IllegalArgumentException("Peek request size of " + sourceBuffer.length + " bytes exceeds buffer size of " + this.bufferSize + " bytes"); 
        if (this.buffer.getCurrentNumberOfBytes() < sourceBuffer.length)
            fillBuffer(); 
        return this.buffer.peek(sourceBuffer, 0, sourceBuffer.length);
    }
    
    public boolean peek(byte[] sourceBuffer, int offset, int length) throws IOException {
        Objects.requireNonNull(sourceBuffer, "Buffer");
        if (sourceBuffer.length > this.bufferSize)
            throw new IllegalArgumentException("Peek request size of " + sourceBuffer.length + " bytes exceeds buffer size of " + this.bufferSize + " bytes"); 
        if (this.buffer.getCurrentNumberOfBytes() < sourceBuffer.length)
            fillBuffer(); 
        return this.buffer.peek(sourceBuffer, offset, length);
    }
}
