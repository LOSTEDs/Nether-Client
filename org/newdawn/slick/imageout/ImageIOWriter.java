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

package org.newdawn.slick.imageout;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class ImageIOWriter implements ImageWriter {
    public void saveImage(Image image, String format, OutputStream output, boolean hasAlpha) throws IOException {
        PixelInterleavedSampleModel sampleModel;
        ColorModel cm;
        int len = 4 * image.getWidth() * image.getHeight();
        if (!hasAlpha)
            len = 3 * image.getWidth() * image.getHeight(); 
        ByteBuffer out = ByteBuffer.allocate(len);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color c = image.getColor(x, y);
                out.put((byte)(int)(c.r * 255.0F));
                out.put((byte)(int)(c.g * 255.0F));
                out.put((byte)(int)(c.b * 255.0F));
                if (hasAlpha)
                    out.put((byte)(int)(c.a * 255.0F)); 
            } 
        } 
        DataBufferByte dataBuffer = new DataBufferByte(out.array(), len);
        if (hasAlpha) {
            int[] offsets = { 0, 1, 2, 3 };
            sampleModel = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 4, 4 * image.getWidth(), offsets);
            cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
        } else {
            int[] offsets = { 0, 1, 2 };
            sampleModel = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 3, 3 * image.getWidth(), offsets);
            cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
        } 
        WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));
        BufferedImage img = new BufferedImage(cm, raster, false, null);
        ImageIO.write(img, format, output);
    }
}
