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

package io.netty.buffer;

import io.netty.util.ReferenceCounted;
import java.nio.ByteBuffer;

public abstract class AbstractDerivedByteBuf extends AbstractByteBuf {
    protected AbstractDerivedByteBuf(int maxCapacity) {
        super(maxCapacity);
    }
    
    public final int refCnt() {
        return unwrap().refCnt();
    }
    
    public final ByteBuf retain() {
        unwrap().retain();
        return this;
    }
    
    public final ByteBuf retain(int increment) {
        unwrap().retain(increment);
        return this;
    }
    
    public final boolean release() {
        return unwrap().release();
    }
    
    public final boolean release(int decrement) {
        return unwrap().release(decrement);
    }
    
    public ByteBuffer internalNioBuffer(int index, int length) {
        return nioBuffer(index, length);
    }
    
    public ByteBuffer nioBuffer(int index, int length) {
        return unwrap().nioBuffer(index, length);
    }
}
