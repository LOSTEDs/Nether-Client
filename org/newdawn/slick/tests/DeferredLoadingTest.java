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

import java.io.IOException;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

public class DeferredLoadingTest extends BasicGame {
    private Music music;
    
    private Sound sound;
    
    private Image image;
    
    private Font font;
    
    private DeferredResource nextResource;
    
    private boolean started;
    
    public DeferredLoadingTest() {
        super("Deferred Loading Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        LoadingList.setDeferredLoading(true);
        new Sound("testdata/cbrown01.wav");
        new Sound("testdata/engine.wav");
        this.sound = new Sound("testdata/restart.ogg");
        new Music("testdata/testloop.ogg");
        this.music = new Music("testdata/SMB-X.XM");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.tga");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.png");
        new Image("testdata/dungeontiles.gif");
        new Image("testdata/logo.gif");
        this.image = new Image("testdata/logo.tga");
        new Image("testdata/logo.png");
        new Image("testdata/rocket.png");
        new Image("testdata/testpack.png");
        this.font = (Font)new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
    }
    
    public void render(GameContainer container, Graphics g) {
        if (this.nextResource != null)
            g.drawString("Loading: " + this.nextResource.getDescription(), 100.0F, 100.0F); 
        int total = LoadingList.get().getTotalResources();
        int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
        float bar = loaded / total;
        g.fillRect(100.0F, 150.0F, (loaded * 40), 20.0F);
        g.drawRect(100.0F, 150.0F, (total * 40), 20.0F);
        if (this.started) {
            this.image.draw(100.0F, 200.0F);
            this.font.drawString(100.0F, 500.0F, "LOADING COMPLETE");
        } 
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        if (this.nextResource != null) {
            try {
                this.nextResource.load();
                try {
                    Thread.sleep(50L);
                } catch (Exception e) {}
            } catch (IOException e) {
                throw new SlickException("Failed to load: " + this.nextResource.getDescription(), e);
            } 
            this.nextResource = null;
        } 
        if (LoadingList.get().getRemainingResources() > 0) {
            this.nextResource = LoadingList.get().getNext();
        } else if (!this.started) {
            this.started = true;
            this.music.loop();
            this.sound.play();
        } 
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new DeferredLoadingTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
    
    public void keyPressed(int key, char c) {}
}
