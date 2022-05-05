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

public class ChannelException extends RuntimeException {
    private static final long serialVersionUID = 2908618315971075004L;
    
    public ChannelException() {}
    
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ChannelException(String message) {
        super(message);
    }
    
    public ChannelException(Throwable cause) {
        super(cause);
    }
}
