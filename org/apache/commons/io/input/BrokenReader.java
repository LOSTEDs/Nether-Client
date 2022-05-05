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

public class BrokenReader extends Reader {
    private final IOException exception;
    
    public BrokenReader(IOException exception) {
        this.exception = exception;
    }
    
    public BrokenReader() {
        this(new IOException("Broken reader"));
    }
    
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw this.exception;
    }
    
    public long skip(long n) throws IOException {
        throw this.exception;
    }
    
    public boolean ready() throws IOException {
        throw this.exception;
    }
    
    public void mark(int readAheadLimit) throws IOException {
        throw this.exception;
    }
    
    public synchronized void reset() throws IOException {
        throw this.exception;
    }
    
    public void close() throws IOException {
        throw this.exception;
    }
}
