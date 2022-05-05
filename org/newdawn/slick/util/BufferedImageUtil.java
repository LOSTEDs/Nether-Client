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

package org.newdawn.slick.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;

public class BufferedImageUtil {
    public static Texture getTexture(String resourceName, BufferedImage resourceImage) throws IOException {
        Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, 9729, 9729);
        return tex;
    }
    
    public static Texture getTexture(String resourceName, BufferedImage resourceImage, int filter) throws IOException {
        Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, filter, filter);
        return tex;
    }
    
    public static Texture getTexture(String resourceName, BufferedImage resourceimage, int target, int dstPixelFormat, int minFilter, int magFilter) throws IOException {
        ImageIOImageData data = new ImageIOImageData();
        int srcPixelFormat = 0;
        int textureID = InternalTextureLoader.createTextureID();
        TextureImpl texture = new TextureImpl(resourceName, target, textureID);
        Renderer.get().glEnable(3553);
        Renderer.get().glBindTexture(target, textureID);
        BufferedImage bufferedImage = resourceimage;
        texture.setWidth(bufferedImage.getWidth());
        texture.setHeight(bufferedImage.getHeight());
        if (bufferedImage.getColorModel().hasAlpha()) {
            srcPixelFormat = 6408;
        } else {
            srcPixelFormat = 6407;
        } 
        ByteBuffer textureBuffer = data.imageToByteBuffer(bufferedImage, false, false, null);
        texture.setTextureHeight(data.getTexHeight());
        texture.setTextureWidth(data.getTexWidth());
        texture.setAlpha((data.getDepth() == 32));
        if (target == 3553) {
            Renderer.get().glTexParameteri(target, 10241, minFilter);
            Renderer.get().glTexParameteri(target, 10240, magFilter);
            if (Renderer.get().canTextureMirrorClamp()) {
                Renderer.get().glTexParameteri(3553, 10242, 34627);
                Renderer.get().glTexParameteri(3553, 10243, 34627);
            } else {
                Renderer.get().glTexParameteri(3553, 10242, 10496);
                Renderer.get().glTexParameteri(3553, 10243, 10496);
            } 
        } 
        Renderer.get().glTexImage2D(target, 0, dstPixelFormat, texture.getTextureWidth(), texture.getTextureHeight(), 0, srcPixelFormat, 5121, textureBuffer);
        return (Texture)texture;
    }
    
    private static void copyArea(BufferedImage image, int x, int y, int width, int height, int dx, int dy) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.drawImage(image.getSubimage(x, y, width, height), x + dx, y + dy, null);
    }
}