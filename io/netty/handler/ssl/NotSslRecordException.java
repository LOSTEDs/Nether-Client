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

package io.netty.handler.ssl;

import javax.net.ssl.SSLException;

public class NotSslRecordException extends SSLException {
    private static final long serialVersionUID = -4316784434770656841L;
    
    public NotSslRecordException() {
        super("");
    }
    
    public NotSslRecordException(String message) {
        super(message);
    }
    
    public NotSslRecordException(Throwable cause) {
        super(cause);
    }
    
    public NotSslRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
