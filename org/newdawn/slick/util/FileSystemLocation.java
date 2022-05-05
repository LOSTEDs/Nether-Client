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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileSystemLocation implements ResourceLocation {
    private File root;
    
    public FileSystemLocation(File root) {
        this.root = root;
    }
    
    public URL getResource(String ref) {
        try {
            File file = new File(this.root, ref);
            if (!file.exists())
                file = new File(ref); 
            if (!file.exists())
                return null; 
            return file.toURI().toURL();
        } catch (IOException e) {
            return null;
        } 
    }
    
    public InputStream getResourceAsStream(String ref) {
        try {
            File file = new File(this.root, ref);
            if (!file.exists())
                file = new File(ref); 
            return new FileInputStream(file);
        } catch (IOException e) {
            return null;
        } 
    }
}
