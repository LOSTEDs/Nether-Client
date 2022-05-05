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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageOut {
    private static final boolean DEFAULT_ALPHA_WRITE = false;
    
    public static String TGA = "tga";
    
    public static String PNG = "png";
    
    public static String JPG = "jpg";
    
    public static String[] getSupportedFormats() {
        return ImageWriterFactory.getSupportedFormats();
    }
    
    public static void write(Image image, String format, OutputStream out) throws SlickException {
        write(image, format, out, false);
    }
    
    public static void write(Image image, String format, OutputStream out, boolean writeAlpha) throws SlickException {
        try {
            ImageWriter writer = ImageWriterFactory.getWriterForFormat(format);
            writer.saveImage(image, format, out, writeAlpha);
        } catch (IOException e) {
            throw new SlickException("Unable to write out the image in format: " + format, e);
        } 
    }
    
    public static void write(Image image, String dest) throws SlickException {
        write(image, dest, false);
    }
    
    public static void write(Image image, String dest, boolean writeAlpha) throws SlickException {
        try {
            int ext = dest.lastIndexOf('.');
            if (ext < 0)
                throw new SlickException("Unable to determine format from: " + dest); 
            String format = dest.substring(ext + 1);
            write(image, format, new FileOutputStream(dest), writeAlpha);
        } catch (IOException e) {
            throw new SlickException("Unable to write to the destination: " + dest, e);
        } 
    }
    
    public static void write(Image image, String format, String dest) throws SlickException {
        write(image, format, dest, false);
    }
    
    public static void write(Image image, String format, String dest, boolean writeAlpha) throws SlickException {
        try {
            write(image, format, new FileOutputStream(dest), writeAlpha);
        } catch (IOException e) {
            throw new SlickException("Unable to write to the destination: " + dest, e);
        } 
    }
}
