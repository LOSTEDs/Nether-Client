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

import java.io.IOException;
import java.io.InputStream;

public class TextureLoader {
    public static Texture getTexture(String format, InputStream in) throws IOException {
        return getTexture(format, in, false, 9729);
    }
    
    public static Texture getTexture(String format, InputStream in, boolean flipped) throws IOException {
        return getTexture(format, in, flipped, 9729);
    }
    
    public static Texture getTexture(String format, InputStream in, int filter) throws IOException {
        return getTexture(format, in, false, filter);
    }
    
    public static Texture getTexture(String format, InputStream in, boolean flipped, int filter) throws IOException {
        return InternalTextureLoader.get().getTexture(in, in.toString() + "." + format, flipped, filter);
    }
}
