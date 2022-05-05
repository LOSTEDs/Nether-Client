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

public class TransformTest2 extends BasicGame {
    private float scale = 1.0F;
    
    private boolean scaleUp;
    
    private boolean scaleDown;
    
    private float camX = 320.0F;
    
    private float camY = 240.0F;
    
    private boolean moveLeft;
    
    private boolean moveUp;
    
    private boolean moveRight;
    
    private boolean moveDown;
    
    public TransformTest2() {
        super("Transform Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        container.setTargetFrameRate(100);
    }
    
    public void render(GameContainer contiainer, Graphics g) {
        g.translate(320.0F, 240.0F);
        g.translate(-this.camX * this.scale, -this.camY * this.scale);
        g.scale(this.scale, this.scale);
        g.setColor(Color.red);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++)
                g.fillRect((-500 + x * 100), (-500 + y * 100), 80.0F, 80.0F); 
        } 
        g.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
        g.fillRect(-320.0F, -240.0F, 640.0F, 480.0F);
        g.setColor(Color.white);
        g.drawRect(-320.0F, -240.0F, 640.0F, 480.0F);
    }
    
    public void update(GameContainer container, int delta) {
        if (this.scaleUp)
            this.scale += delta * 0.001F; 
        if (this.scaleDown)
            this.scale -= delta * 0.001F; 
        float moveSpeed = delta * 0.4F * 1.0F / this.scale;
        if (this.moveLeft)
            this.camX -= moveSpeed; 
        if (this.moveUp)
            this.camY -= moveSpeed; 
        if (this.moveRight)
            this.camX += moveSpeed; 
        if (this.moveDown)
            this.camY += moveSpeed; 
    }
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
        if (key == 16)
            this.scaleUp = true; 
        if (key == 30)
            this.scaleDown = true; 
        if (key == 203)
            this.moveLeft = true; 
        if (key == 200)
            this.moveUp = true; 
        if (key == 205)
            this.moveRight = true; 
        if (key == 208)
            this.moveDown = true; 
    }
    
    public void keyReleased(int key, char c) {
        if (key == 16)
            this.scaleUp = false; 
        if (key == 30)
            this.scaleDown = false; 
        if (key == 203)
            this.moveLeft = false; 
        if (key == 200)
            this.moveUp = false; 
        if (key == 205)
            this.moveRight = false; 
        if (key == 208)
            this.moveDown = false; 
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new TransformTest2());
            container.setDisplayMode(640, 480, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
