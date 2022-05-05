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

import io.netty.util.ResourceLeak;
import java.nio.ByteOrder;

final class SimpleLeakAwareByteBuf extends WrappedByteBuf {
    private final ResourceLeak leak;
    
    SimpleLeakAwareByteBuf(ByteBuf buf, ResourceLeak leak) {
        super(buf);
        this.leak = leak;
    }
    
    public boolean release() {
        boolean deallocated = super.release();
        if (deallocated)
            this.leak.close(); 
        return deallocated;
    }
    
    public boolean release(int decrement) {
        boolean deallocated = super.release(decrement);
        if (deallocated)
            this.leak.close(); 
        return deallocated;
    }
    
    public ByteBuf order(ByteOrder endianness) {
        this.leak.record();
        if (order() == endianness)
            return this; 
        return new SimpleLeakAwareByteBuf(super.order(endianness), this.leak);
    }
    
    public ByteBuf slice() {
        return new SimpleLeakAwareByteBuf(super.slice(), this.leak);
    }
    
    public ByteBuf slice(int index, int length) {
        return new SimpleLeakAwareByteBuf(super.slice(index, length), this.leak);
    }
    
    public ByteBuf duplicate() {
        return new SimpleLeakAwareByteBuf(super.duplicate(), this.leak);
    }
    
    public ByteBuf readSlice(int length) {
        return new SimpleLeakAwareByteBuf(super.readSlice(length), this.leak);
    }
}
