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

package org.newdawn.slick.openal;

import java.io.IOException;

interface AudioInputStream {
    int getChannels();
    
    int getRate();
    
    int read() throws IOException;
    
    int read(byte[] paramArrayOfbyte) throws IOException;
    
    int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
    
    boolean atEnd();
    
    void close() throws IOException;
}
