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

public class BoundedReader extends Reader {
    private static final int INVALID = -1;
    
    private final Reader target;
    
    private int charsRead = 0;
    
    private int markedAt = -1;
    
    private int readAheadLimit;
    
    private final int maxCharsFromTargetReader;
    
    public BoundedReader(Reader target, int maxCharsFromTargetReader) throws IOException {
        this.target = target;
        this.maxCharsFromTargetReader = maxCharsFromTargetReader;
    }
    
    public void close() throws IOException {
        this.target.close();
    }
    
    public void reset() throws IOException {
        this.charsRead = this.markedAt;
        this.target.reset();
    }
    
    public void mark(int readAheadLimit) throws IOException {
        this.readAheadLimit = readAheadLimit - this.charsRead;
        this.markedAt = this.charsRead;
        this.target.mark(readAheadLimit);
    }
    
    public int read() throws IOException {
        if (this.charsRead >= this.maxCharsFromTargetReader)
            return -1; 
        if (this.markedAt >= 0 && this.charsRead - this.markedAt >= this.readAheadLimit)
            return -1; 
        this.charsRead++;
        return this.target.read();
    }
    
    public int read(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            int c = read();
            if (c == -1)
                return (i == 0) ? -1 : i; 
            cbuf[off + i] = (char)c;
        } 
        return len;
    }
}
