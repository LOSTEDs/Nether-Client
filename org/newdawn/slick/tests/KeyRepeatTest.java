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

package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class KeyRepeatTest extends BasicGame {
    private int count;
    
    private Input input;
    
    public KeyRepeatTest() {
        super("KeyRepeatTest");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.input = container.getInput();
        this.input.enableKeyRepeat(300, 100);
    }
    
    public void render(GameContainer container, Graphics g) {
        g.drawString("Key Press Count: " + this.count, 100.0F, 100.0F);
        g.drawString("Press Space to Toggle Key Repeat", 100.0F, 150.0F);
        g.drawString("Key Repeat Enabled: " + this.input.isKeyRepeatEnabled(), 100.0F, 200.0F);
    }
    
    public void update(GameContainer container, int delta) {}
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new KeyRepeatTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
    
    public void keyPressed(int key, char c) {
        this.count++;
        if (key == 57)
            if (this.input.isKeyRepeatEnabled()) {
                this.input.disableKeyRepeat();
            } else {
                this.input.enableKeyRepeat(300, 100);
            }  
    }
}
