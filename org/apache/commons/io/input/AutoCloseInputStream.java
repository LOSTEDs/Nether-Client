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
import java.io.InputStream;

public class AutoCloseInputStream extends ProxyInputStream {
    public AutoCloseInputStream(InputStream in) {
        super(in);
    }
    
    public void close() throws IOException {
        this.in.close();
        this.in = ClosedInputStream.CLOSED_INPUT_STREAM;
    }
    
    protected void afterRead(int n) throws IOException {
        if (n == -1)
            close(); 
    }
    
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
