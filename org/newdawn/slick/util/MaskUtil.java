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

import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class MaskUtil {
    protected static SGL GL = Renderer.get();
    
    public static void defineMask() {
        GL.glDepthMask(true);
        GL.glClearDepth(1.0F);
        GL.glClear(256);
        GL.glDepthFunc(519);
        GL.glEnable(2929);
        GL.glDepthMask(true);
        GL.glColorMask(false, false, false, false);
    }
    
    public static void finishDefineMask() {
        GL.glDepthMask(false);
        GL.glColorMask(true, true, true, true);
    }
    
    public static void drawOnMask() {
        GL.glDepthFunc(514);
    }
    
    public static void drawOffMask() {
        GL.glDepthFunc(517);
    }
    
    public static void resetMask() {
        GL.glDepthMask(true);
        GL.glClearDepth(0.0F);
        GL.glClear(256);
        GL.glDepthMask(false);
        GL.glDisable(2929);
    }
}
