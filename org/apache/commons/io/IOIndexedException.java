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

public class IOIndexedException extends IOException {
    private static final long serialVersionUID = 1L;
    
    private final int index;
    
    public IOIndexedException(int index, Throwable cause) {
        super(toMessage(index, cause), cause);
        this.index = index;
    }
    
    protected static String toMessage(int index, Throwable cause) {
        String unspecified = "Null";
        String name = (cause == null) ? "Null" : cause.getClass().getSimpleName();
        String msg = (cause == null) ? "Null" : cause.getMessage();
        return String.format("%s #%,d: %s", new Object[] { name, Integer.valueOf(index), msg });
    }
    
    public int getIndex() {
        return this.index;
    }
}
