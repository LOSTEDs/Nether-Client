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
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AntiAliasTest extends BasicGame {
    public AntiAliasTest() {
        super("AntiAlias Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        container.getGraphics().setBackground(Color.green);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        g.setColor(Color.red);
        g.drawOval(100.0F, 100.0F, 100.0F, 100.0F);
        g.fillOval(300.0F, 100.0F, 100.0F, 100.0F);
        g.setAntiAlias(false);
        g.setColor(Color.red);
        g.drawOval(100.0F, 300.0F, 100.0F, 100.0F);
        g.fillOval(300.0F, 300.0F, 100.0F, 100.0F);
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new AntiAliasTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
