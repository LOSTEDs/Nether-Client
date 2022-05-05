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

package org.newdawn.slick.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class BasicComponent extends AbstractComponent {
    protected int x;
    
    protected int y;
    
    protected int width;
    
    protected int height;
    
    public BasicComponent(GUIContext container) {
        super(container);
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public abstract void renderImpl(GUIContext paramGUIContext, Graphics paramGraphics);
    
    public void render(GUIContext container, Graphics g) throws SlickException {
        renderImpl(container, g);
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
