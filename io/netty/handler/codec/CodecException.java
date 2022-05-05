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

package io.netty.handler.codec;

public class CodecException extends RuntimeException {
    private static final long serialVersionUID = -1464830400709348473L;
    
    public CodecException() {}
    
    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CodecException(String message) {
        super(message);
    }
    
    public CodecException(Throwable cause) {
        super(cause);
    }
}
