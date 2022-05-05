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

package org.newdawn.slick.state.transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class VerticalSplitTransition implements Transition {
    protected static SGL GL = Renderer.get();
    
    private GameState prev;
    
    private float offset;
    
    private boolean finish;
    
    private Color background;
    
    public VerticalSplitTransition() {}
    
    public VerticalSplitTransition(Color background) {
        this.background = background;
    }
    
    public void init(GameState firstState, GameState secondState) {
        this.prev = secondState;
    }
    
    public boolean isComplete() {
        return this.finish;
    }
    
    public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
        g.translate(0.0F, -this.offset);
        g.setClip(0, (int)-this.offset, container.getWidth(), container.getHeight() / 2);
        if (this.background != null) {
            Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
            g.setColor(c);
        } 
        GL.glPushMatrix();
        this.prev.render(container, game, g);
        GL.glPopMatrix();
        g.clearClip();
        g.resetTransform();
        g.translate(0.0F, this.offset);
        g.setClip(0, (int)((container.getHeight() / 2) + this.offset), container.getWidth(), container.getHeight() / 2);
        if (this.background != null) {
            Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
            g.setColor(c);
        } 
        GL.glPushMatrix();
        this.prev.render(container, game, g);
        GL.glPopMatrix();
        g.clearClip();
        g.translate(0.0F, -this.offset);
    }
    
    public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {}
    
    public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {
        this.offset += delta * 1.0F;
        if (this.offset > (container.getHeight() / 2))
            this.finish = true; 
    }
}
