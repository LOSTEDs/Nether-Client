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

package org.newdawn.slick.opengl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public interface LoadableImageData extends ImageData {
    void configureEdging(boolean paramBoolean);
    
    ByteBuffer loadImage(InputStream paramInputStream) throws IOException;
    
    ByteBuffer loadImage(InputStream paramInputStream, boolean paramBoolean, int[] paramArrayOfint) throws IOException;
    
    ByteBuffer loadImage(InputStream paramInputStream, boolean paramBoolean1, boolean paramBoolean2, int[] paramArrayOfint) throws IOException;
}
