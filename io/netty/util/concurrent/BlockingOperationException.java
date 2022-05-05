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

package io.netty.util.concurrent;

public class BlockingOperationException extends IllegalStateException {
    private static final long serialVersionUID = 2462223247762460301L;
    
    public BlockingOperationException() {}
    
    public BlockingOperationException(String s) {
        super(s);
    }
    
    public BlockingOperationException(Throwable cause) {
        super(cause);
    }
    
    public BlockingOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
