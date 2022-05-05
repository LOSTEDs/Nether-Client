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

public enum SocksCmdStatus {
    SUCCESS((byte)0),
    FAILURE((byte)1),
    FORBIDDEN((byte)2),
    NETWORK_UNREACHABLE((byte)3),
    HOST_UNREACHABLE((byte)4),
    REFUSED((byte)5),
    TTL_EXPIRED((byte)6),
    COMMAND_NOT_SUPPORTED((byte)7),
    ADDRESS_NOT_SUPPORTED((byte)8),
    UNASSIGNED((byte)-1);
    
    private final byte b;
    
    SocksCmdStatus(byte b) {
        this.b = b;
    }
    
    @Deprecated
    public static SocksCmdStatus fromByte(byte b) {
        return valueOf(b);
    }
    
    public byte byteValue() {
        return this.b;
    }
}
