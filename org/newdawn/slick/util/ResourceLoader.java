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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ResourceLoader {
    private static ArrayList locations = new ArrayList();
    
    static {
        locations.add(new ClasspathLocation());
        locations.add(new FileSystemLocation(new File(".")));
    }
    
    public static void addResourceLocation(ResourceLocation location) {
        locations.add(location);
    }
    
    public static void removeResourceLocation(ResourceLocation location) {
        locations.remove(location);
    }
    
    public static void removeAllResourceLocations() {
        locations.clear();
    }
    
    public static InputStream getResourceAsStream(String ref) {
        InputStream in = null;
        for (int i = 0; i < locations.size(); i++) {
            ResourceLocation location = locations.get(i);
            in = location.getResourceAsStream(ref);
            if (in != null)
                break; 
        } 
        if (in == null)
            throw new RuntimeException("Resource not found: " + ref); 
        return new BufferedInputStream(in);
    }
    
    public static boolean resourceExists(String ref) {
        URL url = null;
        for (int i = 0; i < locations.size(); i++) {
            ResourceLocation location = locations.get(i);
            url = location.getResource(ref);
            if (url != null)
                return true; 
        } 
        return false;
    }
    
    public static URL getResource(String ref) {
        URL url = null;
        for (int i = 0; i < locations.size(); i++) {
            ResourceLocation location = locations.get(i);
            url = location.getResource(ref);
            if (url != null)
                break; 
        } 
        if (url == null)
            throw new RuntimeException("Resource not found: " + ref); 
        return url;
    }
}
