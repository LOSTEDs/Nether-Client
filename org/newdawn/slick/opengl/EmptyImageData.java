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

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;

public class EmptyImageData implements ImageData {
    private int width;
    
    private int height;
    
    public EmptyImageData(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getDepth() {
        return 32;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public ByteBuffer getImageBufferData() {
        return BufferUtils.createByteBuffer(getTexWidth() * getTexHeight() * 4);
    }
    
    public int getTexHeight() {
        return InternalTextureLoader.get2Fold(this.height);
    }
    
    public int getTexWidth() {
        return InternalTextureLoader.get2Fold(this.width);
    }
    
    public int getWidth() {
        return this.width;
    }
}
