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
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class PolygonTest extends BasicGame {
    private Polygon poly;
    
    private boolean in;
    
    private float y;
    
    public PolygonTest() {
        super("Polygon Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.poly = new Polygon();
        this.poly.addPoint(300.0F, 100.0F);
        this.poly.addPoint(320.0F, 200.0F);
        this.poly.addPoint(350.0F, 210.0F);
        this.poly.addPoint(280.0F, 250.0F);
        this.poly.addPoint(300.0F, 200.0F);
        this.poly.addPoint(240.0F, 150.0F);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        this.in = this.poly.contains(container.getInput().getMouseX(), container.getInput().getMouseY());
        this.poly.setCenterY(0.0F);
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        if (this.in) {
            g.setColor(Color.red);
            g.fill((Shape)this.poly);
        } 
        g.setColor(Color.yellow);
        g.fillOval(this.poly.getCenterX() - 3.0F, this.poly.getCenterY() - 3.0F, 6.0F, 6.0F);
        g.setColor(Color.white);
        g.draw((Shape)this.poly);
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new PolygonTest(), 640, 480, false);
            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
