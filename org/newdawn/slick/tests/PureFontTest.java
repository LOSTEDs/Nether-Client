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

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PureFontTest extends BasicGame {
    private Font font;
    
    private Image image;
    
    private static AppGameContainer container;
    
    public PureFontTest() {
        super("Hiero Font Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.image = new Image("testdata/sky.jpg");
        this.font = (Font)new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
    }
    
    public void render(GameContainer container, Graphics g) {
        this.image.draw(0.0F, 0.0F, 800.0F, 600.0F);
        this.font.drawString(100.0F, 32.0F, "On top of old smokey, all");
        this.font.drawString(100.0F, 80.0F, "covered with sand..");
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
    }
    
    public static void main(String[] argv) {
        try {
            container = new AppGameContainer((Game)new PureFontTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
