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

import java.io.InputStream;

public class ShaderPackNone implements IShaderPack {
    public void close() {}
    
    public InputStream getResourceAsStream(String resName) {
        return null;
    }
    
    public boolean hasDirectory(String name) {
        return false;
    }
    
    public String getName() {
        return Shaders.packNameNone;
    }
}
