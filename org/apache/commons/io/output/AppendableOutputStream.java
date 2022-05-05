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

import java.io.IOException;
import java.io.OutputStream;

public class AppendableOutputStream<T extends Appendable> extends OutputStream {
    private final T appendable;
    
    public AppendableOutputStream(T appendable) {
        this.appendable = appendable;
    }
    
    public void write(int b) throws IOException {
        this.appendable.append((char)b);
    }
    
    public T getAppendable() {
        return this.appendable;
    }
}
