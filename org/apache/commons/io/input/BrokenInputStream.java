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

public class BrokenInputStream extends InputStream {
    private final IOException exception;
    
    public BrokenInputStream(IOException exception) {
        this.exception = exception;
    }
    
    public BrokenInputStream() {
        this(new IOException("Broken input stream"));
    }
    
    public int read() throws IOException {
        throw this.exception;
    }
    
    public int available() throws IOException {
        throw this.exception;
    }
    
    public long skip(long n) throws IOException {
        throw this.exception;
    }
    
    public synchronized void reset() throws IOException {
        throw this.exception;
    }
    
    public void close() throws IOException {
        throw this.exception;
    }
}
