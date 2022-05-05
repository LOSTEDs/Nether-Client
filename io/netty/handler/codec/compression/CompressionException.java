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

package io.netty.handler.codec.compression;

import io.netty.handler.codec.EncoderException;

public class CompressionException extends EncoderException {
    private static final long serialVersionUID = 5603413481274811897L;
    
    public CompressionException() {}
    
    public CompressionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CompressionException(String message) {
        super(message);
    }
    
    public CompressionException(Throwable cause) {
        super(cause);
    }
}
