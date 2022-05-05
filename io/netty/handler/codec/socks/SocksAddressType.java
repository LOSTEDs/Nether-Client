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

package io.netty.handler.codec.socks;

public enum SocksAddressType {
    IPv4((byte)1),
    DOMAIN((byte)3),
    IPv6((byte)4),
    UNKNOWN((byte)-1);
    
    private final byte b;
    
    SocksAddressType(byte b) {
        this.b = b;
    }
    
    @Deprecated
    public static SocksAddressType fromByte(byte b) {
        return valueOf(b);
    }
    
    public byte byteValue() {
        return this.b;
    }
}
