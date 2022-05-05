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

package io.netty.util;

public class IllegalReferenceCountException extends IllegalStateException {
    private static final long serialVersionUID = -2507492394288153468L;
    
    public IllegalReferenceCountException() {}
    
    public IllegalReferenceCountException(int refCnt) {
        this("refCnt: " + refCnt);
    }
    
    public IllegalReferenceCountException(int refCnt, int increment) {
        this("refCnt: " + refCnt + ", " + ((increment > 0) ? ("increment: " + increment) : ("decrement: " + -increment)));
    }
    
    public IllegalReferenceCountException(String message) {
        super(message);
    }
    
    public IllegalReferenceCountException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalReferenceCountException(Throwable cause) {
        super(cause);
    }
}
