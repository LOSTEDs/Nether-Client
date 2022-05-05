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

package org.newdawn.slick;

import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class ScalableGame implements Game {
    private static SGL GL = Renderer.get();
    
    private float normalWidth;
    
    private float normalHeight;
    
    private Game held;
    
    private boolean maintainAspect;
    
    private int targetWidth;
    
    private int targetHeight;
    
    private GameContainer container;
    
    public ScalableGame(Game held, int normalWidth, int normalHeight) {
        this(held, normalWidth, normalHeight, false);
    }
    
    public ScalableGame(Game held, int normalWidth, int normalHeight, boolean maintainAspect) {
        this.held = held;
        this.normalWidth = normalWidth;
        this.normalHeight = normalHeight;
        this.maintainAspect = maintainAspect;
    }
    
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        recalculateScale();
        this.held.init(container);
    }
    
    public void recalculateScale() throws SlickException {
        this.targetWidth = this.container.getWidth();
        this.targetHeight = this.container.getHeight();
        if (this.maintainAspect) {
            boolean normalIsWide = ((this.normalWidth / this.normalHeight) > 1.6D);
            boolean containerIsWide = ((this.targetWidth / this.targetHeight) > 1.6D);
            float wScale = this.targetWidth / this.normalWidth;
            float hScale = this.targetHeight / this.normalHeight;
            if (normalIsWide & containerIsWide) {
                float scale = (wScale < hScale) ? wScale : hScale;
                this.targetWidth = (int)(this.normalWidth * scale);
                this.targetHeight = (int)(this.normalHeight * scale);
            } else if ((normalIsWide & (!containerIsWide ? 1 : 0)) != 0) {
                this.targetWidth = (int)(this.normalWidth * wScale);
                this.targetHeight = (int)(this.normalHeight * wScale);
            } else if (((!normalIsWide ? 1 : 0) & containerIsWide) != 0) {
                this.targetWidth = (int)(this.normalWidth * hScale);
                this.targetHeight = (int)(this.normalHeight * hScale);
            } else {
                float scale = (wScale < hScale) ? wScale : hScale;
                this.targetWidth = (int)(this.normalWidth * scale);
                this.targetHeight = (int)(this.normalHeight * scale);
            } 
        } 
        if (this.held instanceof InputListener)
            this.container.getInput().addListener((InputListener)this.held); 
        this.container.getInput().setScale(this.normalWidth / this.targetWidth, this.normalHeight / this.targetHeight);
        int yoffset = 0;
        int xoffset = 0;
        if (this.targetHeight < this.container.getHeight())
            yoffset = (this.container.getHeight() - this.targetHeight) / 2; 
        if (this.targetWidth < this.container.getWidth())
            xoffset = (this.container.getWidth() - this.targetWidth) / 2; 
        this.container.getInput().setOffset(-xoffset / this.targetWidth / this.normalWidth, -yoffset / this.targetHeight / this.normalHeight);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        if (this.targetHeight != container.getHeight() || this.targetWidth != container.getWidth())
            recalculateScale(); 
        this.held.update(container, delta);
    }
    
    public final void render(GameContainer container, Graphics g) throws SlickException {
        int yoffset = 0;
        int xoffset = 0;
        if (this.targetHeight < container.getHeight())
            yoffset = (container.getHeight() - this.targetHeight) / 2; 
        if (this.targetWidth < container.getWidth())
            xoffset = (container.getWidth() - this.targetWidth) / 2; 
        SlickCallable.enterSafeBlock();
        g.setClip(xoffset, yoffset, this.targetWidth, this.targetHeight);
        GL.glTranslatef(xoffset, yoffset, 0.0F);
        g.scale(this.targetWidth / this.normalWidth, this.targetHeight / this.normalHeight);
        GL.glPushMatrix();
        this.held.render(container, g);
        GL.glPopMatrix();
        g.clearClip();
        SlickCallable.leaveSafeBlock();
        renderOverlay(container, g);
    }
    
    protected void renderOverlay(GameContainer container, Graphics g) {}
    
    public boolean closeRequested() {
        return this.held.closeRequested();
    }
    
    public String getTitle() {
        return this.held.getTitle();
    }
}
