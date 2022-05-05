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

public class BrokenOutputStream extends OutputStream {
    private final IOException exception;
    
    public BrokenOutputStream(IOException exception) {
        this.exception = exception;
    }
    
    public BrokenOutputStream() {
        this(new IOException("Broken output stream"));
    }
    
    public void write(int b) throws IOException {
        throw this.exception;
    }
    
    public void flush() throws IOException {
        throw this.exception;
    }
    
    public void close() throws IOException {
        throw this.exception;
    }
}
