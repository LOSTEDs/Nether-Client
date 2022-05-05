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

import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class TrueTypeFontPerformanceTest extends BasicGame {
    private Font awtFont;
    
    private TrueTypeFont font;
    
    private String text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
    
    private ArrayList lines = new ArrayList();
    
    private boolean visible = true;
    
    public TrueTypeFontPerformanceTest() {
        super("Font Performance Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.awtFont = new Font("Verdana", 0, 16);
        this.font = new TrueTypeFont(this.awtFont, false);
        for (int j = 0; j < 2; j++) {
            int lineLen = 90;
            int i;
            for (i = 0; i < this.text.length(); i += lineLen) {
                if (i + lineLen > this.text.length())
                    lineLen = this.text.length() - i; 
                this.lines.add(this.text.substring(i, i + lineLen));
            } 
            this.lines.add("");
        } 
    }
    
    public void render(GameContainer container, Graphics g) {
        g.setFont((Font)this.font);
        if (this.visible)
            for (int i = 0; i < this.lines.size(); i++)
                this.font.drawString(10.0F, (50 + i * 20), this.lines.get(i), (i > 10) ? Color.red : Color.green);  
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
        if (key == 57)
            this.visible = !this.visible; 
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new TrueTypeFontPerformanceTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
