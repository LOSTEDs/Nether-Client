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

import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class CanvasSizeTest extends BasicGame {
    public CanvasSizeTest() {
        super("Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        System.out.println(container.getWidth() + ", " + container.getHeight());
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {}
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public static void main(String[] args) {
        try {
            CanvasGameContainer container = new CanvasGameContainer((Game)new CanvasSizeTest());
            container.setSize(640, 480);
            Frame frame = new Frame("Test");
            frame.setLayout(new GridLayout(1, 2));
            frame.add((Component)container);
            frame.pack();
            frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
            frame.setVisible(true);
            container.start();
        } catch (Exception e) {
            Log.error(e);
        } 
    }
}
