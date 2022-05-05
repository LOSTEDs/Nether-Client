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

package io.netty.channel.rxtx;

import io.netty.channel.ChannelOption;

public final class RxtxChannelOption<T> extends ChannelOption<T> {
    public static final RxtxChannelOption<Integer> BAUD_RATE = new RxtxChannelOption("BAUD_RATE");
    
    public static final RxtxChannelOption<Boolean> DTR = new RxtxChannelOption("DTR");
    
    public static final RxtxChannelOption<Boolean> RTS = new RxtxChannelOption("RTS");
    
    public static final RxtxChannelOption<RxtxChannelConfig.Stopbits> STOP_BITS = new RxtxChannelOption("STOP_BITS");
    
    public static final RxtxChannelOption<RxtxChannelConfig.Databits> DATA_BITS = new RxtxChannelOption("DATA_BITS");
    
    public static final RxtxChannelOption<RxtxChannelConfig.Paritybit> PARITY_BIT = new RxtxChannelOption("PARITY_BIT");
    
    public static final RxtxChannelOption<Integer> WAIT_TIME = new RxtxChannelOption("WAIT_TIME");
    
    public static final RxtxChannelOption<Integer> READ_TIMEOUT = new RxtxChannelOption("READ_TIMEOUT");
    
    private RxtxChannelOption(String name) {
        super(name);
    }
}
