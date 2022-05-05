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

package org.newdawn.slick.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;

public interface GameState extends InputListener {
    int getID();
    
    void init(GameContainer paramGameContainer, StateBasedGame paramStateBasedGame) throws SlickException;
    
    void render(GameContainer paramGameContainer, StateBasedGame paramStateBasedGame, Graphics paramGraphics) throws SlickException;
    
    void update(GameContainer paramGameContainer, StateBasedGame paramStateBasedGame, int paramInt) throws SlickException;
    
    void enter(GameContainer paramGameContainer, StateBasedGame paramStateBasedGame) throws SlickException;
    
    void leave(GameContainer paramGameContainer, StateBasedGame paramStateBasedGame) throws SlickException;
}
