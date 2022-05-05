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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class CrossStateTransition implements Transition {
    private GameState secondState;
    
    public CrossStateTransition(GameState secondState) {
        this.secondState = secondState;
    }
    
    public abstract boolean isComplete();
    
    public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
        preRenderSecondState(game, container, g);
        this.secondState.render(container, game, g);
        postRenderSecondState(game, container, g);
    }
    
    public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
        preRenderFirstState(game, container, g);
    }
    
    public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {}
    
    public void preRenderFirstState(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {}
    
    public void preRenderSecondState(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {}
    
    public void postRenderSecondState(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {}
}
