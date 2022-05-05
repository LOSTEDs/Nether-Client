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

package org.apache.commons.io.output;

import java.io.Writer;

public class CloseShieldWriter extends ProxyWriter {
    public CloseShieldWriter(Writer out) {
        super(out);
    }
    
    public void close() {
        this.out = ClosedWriter.CLOSED_WRITER;
    }
}
