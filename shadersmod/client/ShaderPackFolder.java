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

package shadersmod.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.src.StrUtils;

public class ShaderPackFolder implements IShaderPack {
    protected File packFile;
    
    public ShaderPackFolder(String name, File file) {
        this.packFile = file;
    }
    
    public void close() {}
    
    public InputStream getResourceAsStream(String resName) {
        try {
            String s = StrUtils.removePrefixSuffix(resName, "/", "/");
            File file1 = new File(this.packFile, s);
            return !file1.exists() ? null : new BufferedInputStream(new FileInputStream(file1));
        } catch (Exception var4) {
            return null;
        } 
    }
    
    public boolean hasDirectory(String name) {
        File file1 = new File(this.packFile, name.substring(1));
        return !file1.exists() ? false : file1.isDirectory();
    }
    
    public String getName() {
        return this.packFile.getName();
    }
}
