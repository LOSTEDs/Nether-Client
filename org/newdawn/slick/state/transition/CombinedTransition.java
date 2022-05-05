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

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class CombinedTransition implements Transition {
    private ArrayList transitions = new ArrayList();
    
    public void addTransition(Transition t) {
        this.transitions.add(t);
    }
    
    public boolean isComplete() {
        for (int i = 0; i < this.transitions.size(); i++) {
            if (!((Transition)this.transitions.get(i)).isComplete())
                return false; 
        } 
        return true;
    }
    
    public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
        for (int i = this.transitions.size() - 1; i >= 0; i--)
            ((Transition)this.transitions.get(i)).postRender(game, container, g); 
    }
    
    public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
        for (int i = 0; i < this.transitions.size(); i++)
            ((Transition)this.transitions.get(i)).postRender(game, container, g); 
    }
    
    public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {
        for (int i = 0; i < this.transitions.size(); i++) {
            Transition t = this.transitions.get(i);
            if (!t.isComplete())
                t.update(game, container, delta); 
        } 
    }
    
    public void init(GameState firstState, GameState secondState) {
        for (int i = this.transitions.size() - 1; i >= 0; i--)
            ((Transition)this.transitions.get(i)).init(firstState, secondState); 
    }
}
