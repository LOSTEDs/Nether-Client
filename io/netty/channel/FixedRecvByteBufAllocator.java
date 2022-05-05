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
import io.netty.buffer.ByteBufAllocator;

public class FixedRecvByteBufAllocator implements RecvByteBufAllocator {
    private final RecvByteBufAllocator.Handle handle;
    
    private static final class HandleImpl implements RecvByteBufAllocator.Handle {
        private final int bufferSize;
        
        HandleImpl(int bufferSize) {
            this.bufferSize = bufferSize;
        }
        
        public ByteBuf allocate(ByteBufAllocator alloc) {
            return alloc.ioBuffer(this.bufferSize);
        }
        
        public int guess() {
            return this.bufferSize;
        }
        
        public void record(int actualReadBytes) {}
    }
    
    public FixedRecvByteBufAllocator(int bufferSize) {
        if (bufferSize <= 0)
            throw new IllegalArgumentException("bufferSize must greater than 0: " + bufferSize); 
        this.handle = new HandleImpl(bufferSize);
    }
    
    public RecvByteBufAllocator.Handle newHandle() {
        return this.handle;
    }
}
