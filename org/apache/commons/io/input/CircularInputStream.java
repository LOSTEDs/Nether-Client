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

import java.io.InputStream;
import java.util.Objects;

public class CircularInputStream extends InputStream {
    private long byteCount;
    
    private static byte[] validate(byte[] repeatContent) {
        Objects.requireNonNull(repeatContent, "repeatContent");
        for (byte b : repeatContent) {
            if (b == -1)
                throw new IllegalArgumentException("repeatContent contains the end-of-stream marker -1"); 
        } 
        return repeatContent;
    }
    
    private int position = -1;
    
    private final byte[] repeatedContent;
    
    private final long targetByteCount;
    
    public CircularInputStream(byte[] repeatContent, long targetByteCount) {
        this.repeatedContent = validate(repeatContent);
        if (repeatContent.length == 0)
            throw new IllegalArgumentException("repeatContent is empty."); 
        this.targetByteCount = targetByteCount;
    }
    
    public int read() {
        if (this.targetByteCount >= 0L) {
            if (this.byteCount == this.targetByteCount)
                return -1; 
            this.byteCount++;
        } 
        this.position = (this.position + 1) % this.repeatedContent.length;
        return this.repeatedContent[this.position] & 0xFF;
    }
}
