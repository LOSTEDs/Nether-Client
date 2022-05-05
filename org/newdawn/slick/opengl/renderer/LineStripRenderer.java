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

package org.newdawn.slick.opengl.renderer;

public interface LineStripRenderer {
    boolean applyGLLineFixes();
    
    void start();
    
    void end();
    
    void vertex(float paramFloat1, float paramFloat2);
    
    void color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
    
    void setWidth(float paramFloat);
    
    void setAntiAlias(boolean paramBoolean);
    
    void setLineCaps(boolean paramBoolean);
}
