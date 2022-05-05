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

package org.newdawn.slick;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.ImageData;

public class ImageBuffer implements ImageData {
    private int width;
    
    private int height;
    
    private int texWidth;
    
    private int texHeight;
    
    private byte[] rawData;
    
    public ImageBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.texWidth = get2Fold(width);
        this.texHeight = get2Fold(height);
        this.rawData = new byte[this.texWidth * this.texHeight * 4];
    }
    
    public byte[] getRGBA() {
        return this.rawData;
    }
    
    public int getDepth() {
        return 32;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getTexHeight() {
        return this.texHeight;
    }
    
    public int getTexWidth() {
        return this.texWidth;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public ByteBuffer getImageBufferData() {
        ByteBuffer scratch = BufferUtils.createByteBuffer(this.rawData.length);
        scratch.put(this.rawData);
        scratch.flip();
        return scratch;
    }
    
    public void setRGBA(int x, int y, int r, int g, int b, int a) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height)
            throw new RuntimeException("Specified location: " + x + "," + y + " outside of image"); 
        int ofs = (x + y * this.texWidth) * 4;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            this.rawData[ofs] = (byte)b;
            this.rawData[ofs + 1] = (byte)g;
            this.rawData[ofs + 2] = (byte)r;
            this.rawData[ofs + 3] = (byte)a;
        } else {
            this.rawData[ofs] = (byte)r;
            this.rawData[ofs + 1] = (byte)g;
            this.rawData[ofs + 2] = (byte)b;
            this.rawData[ofs + 3] = (byte)a;
        } 
    }
    
    public Image getImage() {
        return new Image(this);
    }
    
    public Image getImage(int filter) {
        return new Image(this, filter);
    }
    
    private int get2Fold(int fold) {
        int ret = 2;
        while (ret < fold)
            ret *= 2; 
        return ret;
    }
}