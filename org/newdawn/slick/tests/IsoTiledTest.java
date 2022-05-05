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

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Bootstrap;

public class IsoTiledTest extends BasicGame {
    private TiledMap tilemap;
    
    public IsoTiledTest() {
        super("Isometric Tiled Map Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.tilemap = new TiledMap("testdata/isoexample.tmx", "testdata/");
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        this.tilemap.render(350, 150);
    }
    
    public static void main(String[] argv) {
        Bootstrap.runAsApplication((Game)new IsoTiledTest(), 800, 600, false);
    }
}
