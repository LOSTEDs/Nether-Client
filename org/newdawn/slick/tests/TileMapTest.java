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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class TileMapTest extends BasicGame {
    private TiledMap map;
    
    private String mapName;
    
    private String monsterDifficulty;
    
    private String nonExistingMapProperty;
    
    private String nonExistingLayerProperty;
    
    private int updateCounter = 0;
    
    private static int UPDATE_TIME = 1000;
    
    private int originalTileID = 0;
    
    public TileMapTest() {
        super("Tile Map Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.map = new TiledMap("testdata/testmap.tmx", "testdata");
        this.mapName = this.map.getMapProperty("name", "Unknown map name");
        this.monsterDifficulty = this.map.getLayerProperty(0, "monsters", "easy peasy");
        this.nonExistingMapProperty = this.map.getMapProperty("zaphod", "Undefined map property");
        this.nonExistingLayerProperty = this.map.getLayerProperty(1, "beeblebrox", "Undefined layer property");
        this.originalTileID = this.map.getTileId(10, 10, 0);
    }
    
    public void render(GameContainer container, Graphics g) {
        this.map.render(10, 10, 4, 4, 15, 15);
        g.scale(0.35F, 0.35F);
        this.map.render(1400, 0);
        g.resetTransform();
        g.drawString("map name: " + this.mapName, 10.0F, 500.0F);
        g.drawString("monster difficulty: " + this.monsterDifficulty, 10.0F, 550.0F);
        g.drawString("non existing map property: " + this.nonExistingMapProperty, 10.0F, 525.0F);
        g.drawString("non existing layer property: " + this.nonExistingLayerProperty, 10.0F, 575.0F);
    }
    
    public void update(GameContainer container, int delta) {
        this.updateCounter += delta;
        if (this.updateCounter > UPDATE_TIME) {
            this.updateCounter -= UPDATE_TIME;
            int currentTileID = this.map.getTileId(10, 10, 0);
            if (currentTileID != this.originalTileID) {
                this.map.setTileId(10, 10, 0, this.originalTileID);
            } else {
                this.map.setTileId(10, 10, 0, 1);
            } 
        } 
    }
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
    }
    
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer((Game)new TileMapTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
}
