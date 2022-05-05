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

package io.netty.channel.udt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.util.ReferenceCounted;

public final class UdtMessage extends DefaultByteBufHolder {
    public UdtMessage(ByteBuf data) {
        super(data);
    }
    
    public UdtMessage copy() {
        return new UdtMessage(content().copy());
    }
    
    public UdtMessage duplicate() {
        return new UdtMessage(content().duplicate());
    }
    
    public UdtMessage retain() {
        super.retain();
        return this;
    }
    
    public UdtMessage retain(int increment) {
        super.retain(increment);
        return this;
    }
}
