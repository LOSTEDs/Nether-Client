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
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

public class ImageBufferTest extends BasicGame {
    private Image image;
    
    public ImageBufferTest() {
        super("Image Buffer Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        ImageBuffer buffer = new ImageBuffer(320, 200);
        for (int x = 0; x < 320; x++) {
            for (int y = 0; y < 200; y++) {
                if (y == 20) {
                    buffer.setRGBA(x, y, 255, 255, 255, 255);
                } else {
                    buffer.setRGBA(x, y, x, y, 0, 255);
                } 
            } 
        } 
        this.image = buffer.getImage();
    }
    
    public void render(GameContainer container, Graphics g) {
        this.image.draw(50.0F, 50.0F);
    }
    
    public void update(GameContainer container, int delta) {}
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new ImageBufferTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
