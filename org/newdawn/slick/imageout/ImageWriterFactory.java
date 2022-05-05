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

import java.util.HashMap;
import javax.imageio.ImageIO;
import org.newdawn.slick.SlickException;

public class ImageWriterFactory {
    private static HashMap writers = new HashMap<Object, Object>();
    
    static {
        String[] formats = ImageIO.getWriterFormatNames();
        ImageIOWriter writer = new ImageIOWriter();
        for (int i = 0; i < formats.length; i++)
            registerWriter(formats[i], writer); 
        TGAWriter tga = new TGAWriter();
        registerWriter("tga", tga);
    }
    
    public static void registerWriter(String format, ImageWriter writer) {
        writers.put(format, writer);
    }
    
    public static String[] getSupportedFormats() {
        return (String[])writers.keySet().toArray((Object[])new String[0]);
    }
    
    public static ImageWriter getWriterForFormat(String format) throws SlickException {
        ImageWriter writer = (ImageWriter)writers.get(format);
        if (writer != null)
            return writer; 
        writer = (ImageWriter)writers.get(format.toLowerCase());
        if (writer != null)
            return writer; 
        writer = (ImageWriter)writers.get(format.toUpperCase());
        if (writer != null)
            return writer; 
        throw new SlickException("No image writer available for: " + format);
    }
}
