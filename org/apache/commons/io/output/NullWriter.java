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
import java.io.Writer;

public class NullWriter extends Writer {
    public static final NullWriter NULL_WRITER = new NullWriter();
    
    public Writer append(char c) {
        return this;
    }
    
    public Writer append(CharSequence csq, int start, int end) {
        return this;
    }
    
    public Writer append(CharSequence csq) {
        return this;
    }
    
    public void write(int idx) {}
    
    public void write(char[] chr) {}
    
    public void write(char[] chr, int st, int end) {}
    
    public void write(String str) {}
    
    public void write(String str, int st, int end) {}
    
    public void flush() {}
    
    public void close() {}
}
