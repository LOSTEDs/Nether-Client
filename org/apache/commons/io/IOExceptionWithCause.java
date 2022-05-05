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

package org.apache.commons.io;

import java.io.IOException;

@Deprecated
public class IOExceptionWithCause extends IOException {
    private static final long serialVersionUID = 1L;
    
    public IOExceptionWithCause(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IOExceptionWithCause(Throwable cause) {
        super(cause);
    }
}
