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
import java.io.OutputStream;

public class TeeInputStream extends ProxyInputStream {
    private final OutputStream branch;
    
    private final boolean closeBranch;
    
    public TeeInputStream(InputStream input, OutputStream branch) {
        this(input, branch, false);
    }
    
    public TeeInputStream(InputStream input, OutputStream branch, boolean closeBranch) {
        super(input);
        this.branch = branch;
        this.closeBranch = closeBranch;
    }
    
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            if (this.closeBranch)
                this.branch.close(); 
        } 
    }
    
    public int read() throws IOException {
        int ch = super.read();
        if (ch != -1)
            this.branch.write(ch); 
        return ch;
    }
    
    public int read(byte[] bts, int st, int end) throws IOException {
        int n = super.read(bts, st, end);
        if (n != -1)
            this.branch.write(bts, st, n); 
        return n;
    }
    
    public int read(byte[] bts) throws IOException {
        int n = super.read(bts);
        if (n != -1)
            this.branch.write(bts, 0, n); 
        return n;
    }
}
