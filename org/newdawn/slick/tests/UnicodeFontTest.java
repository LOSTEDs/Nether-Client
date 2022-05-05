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

import java.awt.Color;
import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class UnicodeFontTest extends BasicGame {
    private UnicodeFont unicodeFont;
    
    public UnicodeFontTest() {
        super("Font Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        container.setShowFPS(false);
        this.unicodeFont = new UnicodeFont("c:/windows/fonts/arial.ttf", 48, false, false);
        this.unicodeFont.getEffects().add(new ColorEffect(Color.white));
        container.getGraphics().setBackground(Color.darkGray);
    }
    
    public void render(GameContainer container, Graphics g) {
        g.setColor(Color.white);
        String text = "This is UnicodeFont!\nIt rockz. Kerning: T,";
        this.unicodeFont.drawString(10.0F, 33.0F, text);
        g.setColor(Color.red);
        g.drawRect(10.0F, 33.0F, this.unicodeFont.getWidth(text), this.unicodeFont.getLineHeight());
        g.setColor(Color.blue);
        int yOffset = this.unicodeFont.getYOffset(text);
        g.drawRect(10.0F, (33 + yOffset), this.unicodeFont.getWidth(text), (this.unicodeFont.getHeight(text) - yOffset));
        this.unicodeFont.addGlyphs("~!@!#!#$%___--");
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        this.unicodeFont.loadGlyphs(1);
    }
    
    public static void main(String[] args) throws SlickException, IOException {
        Input.disableControllers();
        AppGameContainer container = new AppGameContainer((Game)new UnicodeFontTest());
        container.setDisplayMode(512, 600, false);
        container.setTargetFrameRate(20);
        container.start();
    }
}
