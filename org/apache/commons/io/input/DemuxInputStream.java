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
import org.apache.commons.io.IOUtils;

public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> inputStream = new InheritableThreadLocal<>();
    
    public InputStream bindStream(InputStream input) {
        InputStream oldValue = this.inputStream.get();
        this.inputStream.set(input);
        return oldValue;
    }
    
    public void close() throws IOException {
        IOUtils.close(this.inputStream.get());
    }
    
    public int read() throws IOException {
        InputStream input = this.inputStream.get();
        if (null != input)
            return input.read(); 
        return -1;
    }
}
