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

public class Renderer {
    public static final int IMMEDIATE_RENDERER = 1;
    
    public static final int VERTEX_ARRAY_RENDERER = 2;
    
    public static final int DEFAULT_LINE_STRIP_RENDERER = 3;
    
    public static final int QUAD_BASED_LINE_STRIP_RENDERER = 4;
    
    private static SGL renderer = new ImmediateModeOGLRenderer();
    
    private static LineStripRenderer lineStripRenderer = new DefaultLineStripRenderer();
    
    public static void setRenderer(int type) {
        switch (type) {
            case 1:
                setRenderer(new ImmediateModeOGLRenderer());
                return;
            case 2:
                setRenderer(new VAOGLRenderer());
                return;
        } 
        throw new RuntimeException("Unknown renderer type: " + type);
    }
    
    public static void setLineStripRenderer(int type) {
        switch (type) {
            case 3:
                setLineStripRenderer(new DefaultLineStripRenderer());
                return;
            case 4:
                setLineStripRenderer(new QuadBasedLineStripRenderer());
                return;
        } 
        throw new RuntimeException("Unknown line strip renderer type: " + type);
    }
    
    public static void setLineStripRenderer(LineStripRenderer renderer) {
        lineStripRenderer = renderer;
    }
    
    public static void setRenderer(SGL r) {
        renderer = r;
    }
    
    public static SGL get() {
        return renderer;
    }
    
    public static LineStripRenderer getLineStripRenderer() {
        return lineStripRenderer;
    }
}
