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

public class DefaultLineStripRenderer implements LineStripRenderer {
    private SGL GL = Renderer.get();
    
    public void end() {
        this.GL.glEnd();
    }
    
    public void setAntiAlias(boolean antialias) {
        if (antialias) {
            this.GL.glEnable(2848);
        } else {
            this.GL.glDisable(2848);
        } 
    }
    
    public void setWidth(float width) {
        this.GL.glLineWidth(width);
    }
    
    public void start() {
        this.GL.glBegin(3);
    }
    
    public void vertex(float x, float y) {
        this.GL.glVertex2f(x, y);
    }
    
    public void color(float r, float g, float b, float a) {
        this.GL.glColor4f(r, g, b, a);
    }
    
    public void setLineCaps(boolean caps) {}
    
    public boolean applyGLLineFixes() {
        return true;
    }
}
