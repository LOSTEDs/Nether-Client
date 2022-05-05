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

package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ImageBufferDownload implements IImageBuffer {
    private int[] imageData;
    
    private int imageWidth;
    
    private int imageHeight;
    
    private static final String __OBFID = "CL_00000956";
    
    public BufferedImage parseUserSkin(BufferedImage image) {
        if (image == null)
            return null; 
        this.imageWidth = 64;
        this.imageHeight = 64;
        int i = image.getWidth();
        int j = image.getHeight();
        int k;
        for (k = 1; this.imageWidth < i || this.imageHeight < j; k *= 2) {
            this.imageWidth *= 2;
            this.imageHeight *= 2;
        } 
        BufferedImage bufferedimage = new BufferedImage(this.imageWidth, this.imageHeight, 2);
        Graphics graphics = bufferedimage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        if (image.getHeight() == 32 * k) {
            graphics.drawImage(bufferedimage, 24 * k, 48 * k, 20 * k, 52 * k, 4 * k, 16 * k, 8 * k, 20 * k, null);
            graphics.drawImage(bufferedimage, 28 * k, 48 * k, 24 * k, 52 * k, 8 * k, 16 * k, 12 * k, 20 * k, null);
            graphics.drawImage(bufferedimage, 20 * k, 52 * k, 16 * k, 64 * k, 8 * k, 20 * k, 12 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 24 * k, 52 * k, 20 * k, 64 * k, 4 * k, 20 * k, 8 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 28 * k, 52 * k, 24 * k, 64 * k, 0 * k, 20 * k, 4 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 32 * k, 52 * k, 28 * k, 64 * k, 12 * k, 20 * k, 16 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 40 * k, 48 * k, 36 * k, 52 * k, 44 * k, 16 * k, 48 * k, 20 * k, null);
            graphics.drawImage(bufferedimage, 44 * k, 48 * k, 40 * k, 52 * k, 48 * k, 16 * k, 52 * k, 20 * k, null);
            graphics.drawImage(bufferedimage, 36 * k, 52 * k, 32 * k, 64 * k, 48 * k, 20 * k, 52 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 40 * k, 52 * k, 36 * k, 64 * k, 44 * k, 20 * k, 48 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 44 * k, 52 * k, 40 * k, 64 * k, 40 * k, 20 * k, 44 * k, 32 * k, null);
            graphics.drawImage(bufferedimage, 48 * k, 52 * k, 44 * k, 64 * k, 52 * k, 20 * k, 56 * k, 32 * k, null);
        } 
        graphics.dispose();
        this.imageData = ((DataBufferInt)bufferedimage.getRaster().getDataBuffer()).getData();
        setAreaOpaque(0, 0, 32 * k, 16 * k);
        setAreaTransparent(32 * k, 0, 64 * k, 32 * k);
        setAreaOpaque(0, 16 * k, 64 * k, 32 * k);
        setAreaTransparent(0, 32 * k, 16 * k, 48 * k);
        setAreaTransparent(16 * k, 32 * k, 40 * k, 48 * k);
        setAreaTransparent(40 * k, 32 * k, 56 * k, 48 * k);
        setAreaTransparent(0, 48 * k, 16 * k, 64 * k);
        setAreaOpaque(16 * k, 48 * k, 48 * k, 64 * k);
        setAreaTransparent(48 * k, 48 * k, 64 * k, 64 * k);
        return bufferedimage;
    }
    
    public void skinAvailable() {}
    
    private void setAreaTransparent(int p_78434_1_, int p_78434_2_, int p_78434_3_, int p_78434_4_) {
        if (!hasTransparency(p_78434_1_, p_78434_2_, p_78434_3_, p_78434_4_))
            for (int i = p_78434_1_; i < p_78434_3_; i++) {
                for (int j = p_78434_2_; j < p_78434_4_; j++)
                    this.imageData[i + j * this.imageWidth] = this.imageData[i + j * this.imageWidth] & 0xFFFFFF; 
            }  
    }
    
    private void setAreaOpaque(int p_78433_1_, int p_78433_2_, int p_78433_3_, int p_78433_4_) {
        for (int i = p_78433_1_; i < p_78433_3_; i++) {
            for (int j = p_78433_2_; j < p_78433_4_; j++)
                this.imageData[i + j * this.imageWidth] = this.imageData[i + j * this.imageWidth] | 0xFF000000; 
        } 
    }
    
    private boolean hasTransparency(int p_78435_1_, int p_78435_2_, int p_78435_3_, int p_78435_4_) {
        for (int i = p_78435_1_; i < p_78435_3_; i++) {
            for (int j = p_78435_2_; j < p_78435_4_; j++) {
                int k = this.imageData[i + j * this.imageWidth];
                if ((k >> 24 & 0xFF) < 128)
                    return true; 
            } 
        } 
        return false;
    }
}
