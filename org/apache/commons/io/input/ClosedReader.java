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

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class ClosedReader extends Reader {
    public static final ClosedReader CLOSED_READER = new ClosedReader();
    
    public int read(char[] cbuf, int off, int len) {
        return -1;
    }
    
    public void close() throws IOException {}
}
