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

public interface Transition {
    void update(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, int paramInt) throws SlickException;
    
    void preRender(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, Graphics paramGraphics) throws SlickException;
    
    void postRender(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, Graphics paramGraphics) throws SlickException;
    
    boolean isComplete();
    
    void init(GameState paramGameState1, GameState paramGameState2);
}
