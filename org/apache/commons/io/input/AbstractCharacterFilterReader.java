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

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractCharacterFilterReader extends FilterReader {
    protected AbstractCharacterFilterReader(Reader reader) {
        super(reader);
    }
    
    public int read() throws IOException {
        while (true) {
            int ch = this.in.read();
            if (!filter(ch))
                return ch; 
        } 
    }
    
    protected abstract boolean filter(int paramInt);
    
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        if (read == -1)
            return -1; 
        int pos = off - 1;
        for (int readPos = off; readPos < off + read; readPos++) {
            if (!filter(cbuf[readPos])) {
                pos++;
                if (pos < readPos)
                    cbuf[pos] = cbuf[readPos]; 
            } 
        } 
        return pos - off + 1;
    }
}
