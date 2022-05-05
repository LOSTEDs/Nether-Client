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

package io.netty.channel.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.util.ReferenceCounted;
import java.net.InetSocketAddress;

public final class DatagramPacket extends DefaultAddressedEnvelope<ByteBuf, InetSocketAddress> implements ByteBufHolder {
    public DatagramPacket(ByteBuf data, InetSocketAddress recipient) {
        super(data, recipient);
    }
    
    public DatagramPacket(ByteBuf data, InetSocketAddress recipient, InetSocketAddress sender) {
        super(data, recipient, sender);
    }
    
    public DatagramPacket copy() {
        return new DatagramPacket(((ByteBuf)content()).copy(), (InetSocketAddress)recipient(), (InetSocketAddress)sender());
    }
    
    public DatagramPacket duplicate() {
        return new DatagramPacket(((ByteBuf)content()).duplicate(), (InetSocketAddress)recipient(), (InetSocketAddress)sender());
    }
    
    public DatagramPacket retain() {
        super.retain();
        return this;
    }
    
    public DatagramPacket retain(int increment) {
        super.retain(increment);
        return this;
    }
}
