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
import org.newdawn.slick.SlickException;

public class ImageCornerTest extends BasicGame {
    private Image image;
    
    private Image[] images;
    
    private int width;
    
    private int height;
    
    public ImageCornerTest() {
        super("Image Corner Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.image = new Image("testdata/logo.png");
        this.width = this.image.getWidth() / 3;
        this.height = this.image.getHeight() / 3;
        this.images = new Image[] { this.image.getSubImage(0, 0, this.width, this.height), this.image.getSubImage(this.width, 0, this.width, this.height), this.image.getSubImage(this.width * 2, 0, this.width, this.height), this.image.getSubImage(0, this.height, this.width, this.height), this.image.getSubImage(this.width, this.height, this.width, this.height), this.image.getSubImage(this.width * 2, this.height, this.width, this.height), this.image.getSubImage(0, this.height * 2, this.width, this.height), this.image.getSubImage(this.width, this.height * 2, this.width, this.height), this.image.getSubImage(this.width * 2, this.height * 2, this.width, this.height) };
        this.images[0].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[1].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[1].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[2].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[3].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[3].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[4].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[4].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[4].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[4].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[5].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[5].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[6].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[7].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[7].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
        this.images[8].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    public void render(GameContainer container, Graphics g) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++)
                this.images[x + y * 3].draw((100 + x * this.width), (100 + y * this.height)); 
        } 
    }
    
    public static void main(String[] argv) {
        boolean sharedContextTest = false;
        try {
            AppGameContainer container = new AppGameContainer((Game)new ImageCornerTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
}
