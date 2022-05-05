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

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.newdawn.slick.util.Log;

public class ImageDataFactory {
    private static boolean usePngLoader = true;
    
    private static boolean pngLoaderPropertyChecked = false;
    
    private static final String PNG_LOADER = "org.newdawn.slick.pngloader";
    
    private static void checkProperty() {
        if (!pngLoaderPropertyChecked) {
            pngLoaderPropertyChecked = true;
            try {
                AccessController.doPrivileged(new PrivilegedAction() {
                            public Object run() {
                                String val = System.getProperty("org.newdawn.slick.pngloader");
                                if ("false".equalsIgnoreCase(val))
                                    ImageDataFactory.usePngLoader = false; 
                                Log.info("Use Java PNG Loader = " + ImageDataFactory.usePngLoader);
                                return null;
                            }
                        });
            } catch (Throwable e) {}
        } 
    }
    
    public static LoadableImageData getImageDataFor(String ref) {
        checkProperty();
        ref = ref.toLowerCase();
        if (ref.endsWith(".tga"))
            return new TGAImageData(); 
        if (ref.endsWith(".png")) {
            CompositeImageData data = new CompositeImageData();
            if (usePngLoader)
                data.add(new PNGImageData()); 
            data.add(new ImageIOImageData());
            return data;
        } 
        return new ImageIOImageData();
    }
}
