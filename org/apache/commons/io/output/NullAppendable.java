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

public class NullAppendable implements Appendable {
    public static final NullAppendable INSTANCE = new NullAppendable();
    
    public Appendable append(char c) throws IOException {
        return this;
    }
    
    public Appendable append(CharSequence csq) throws IOException {
        return this;
    }
    
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return this;
    }
}
