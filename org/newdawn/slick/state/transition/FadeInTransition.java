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
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class FadeInTransition implements Transition {
    private Color color;
    
    private int fadeTime = 500;
    
    public FadeInTransition() {
        this(Color.black, 500);
    }
    
    public FadeInTransition(Color color) {
        this(color, 500);
    }
    
    public FadeInTransition(Color color, int fadeTime) {
        this.color = new Color(color);
        this.color.a = 1.0F;
        this.fadeTime = fadeTime;
    }
    
    public boolean isComplete() {
        return (this.color.a <= 0.0F);
    }
    
    public void postRender(StateBasedGame game, GameContainer container, Graphics g) {
        Color old = g.getColor();
        g.setColor(this.color);
        g.fillRect(0.0F, 0.0F, (container.getWidth() * 2), (container.getHeight() * 2));
        g.setColor(old);
    }
    
    public void update(StateBasedGame game, GameContainer container, int delta) {
        this.color.a -= delta * 1.0F / this.fadeTime;
        if (this.color.a < 0.0F)
            this.color.a = 0.0F; 
    }
    
    public void preRender(StateBasedGame game, GameContainer container, Graphics g) {}
    
    public void init(GameState firstState, GameState secondState) {}
}
