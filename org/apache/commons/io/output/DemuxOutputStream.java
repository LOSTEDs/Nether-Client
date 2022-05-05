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
import org.apache.commons.io.IOUtils;

public class DemuxOutputStream extends OutputStream {
    private final InheritableThreadLocal<OutputStream> outputStreamThreadLocal = new InheritableThreadLocal<>();
    
    public OutputStream bindStream(OutputStream output) {
        OutputStream stream = this.outputStreamThreadLocal.get();
        this.outputStreamThreadLocal.set(output);
        return stream;
    }
    
    public void close() throws IOException {
        IOUtils.close(this.outputStreamThreadLocal.get());
    }
    
    public void flush() throws IOException {
        OutputStream output = this.outputStreamThreadLocal.get();
        if (null != output)
            output.flush(); 
    }
    
    public void write(int ch) throws IOException {
        OutputStream output = this.outputStreamThreadLocal.get();
        if (null != output)
            output.write(ch); 
    }
}
