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

package org.newdawn.slick;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.newdawn.slick.opengl.Texture;

public class SpriteSheet extends Image {
    private int tw;
    
    private int th;
    
    private int margin = 0;
    
    private Image[][] subImages;
    
    private int spacing;
    
    private Image target;
    
    public SpriteSheet(URL ref, int tw, int th) throws SlickException, IOException {
        this(new Image(ref.openStream(), ref.toString(), false), tw, th);
    }
    
    public SpriteSheet(Image image, int tw, int th) {
        super(image);
        this.target = image;
        this.tw = tw;
        this.th = th;
        initImpl();
    }
    
    public SpriteSheet(Image image, int tw, int th, int spacing, int margin) {
        super(image);
        this.target = image;
        this.tw = tw;
        this.th = th;
        this.spacing = spacing;
        this.margin = margin;
        initImpl();
    }
    
    public SpriteSheet(Image image, int tw, int th, int spacing) {
        this(image, tw, th, spacing, 0);
    }
    
    public SpriteSheet(String ref, int tw, int th, int spacing) throws SlickException {
        this(ref, tw, th, (Color)null, spacing);
    }
    
    public SpriteSheet(String ref, int tw, int th) throws SlickException {
        this(ref, tw, th, (Color)null);
    }
    
    public SpriteSheet(String ref, int tw, int th, Color col) throws SlickException {
        this(ref, tw, th, col, 0);
    }
    
    public SpriteSheet(String ref, int tw, int th, Color col, int spacing) throws SlickException {
        super(ref, false, 2, col);
        this.target = this;
        this.tw = tw;
        this.th = th;
        this.spacing = spacing;
    }
    
    public SpriteSheet(String name, InputStream ref, int tw, int th) throws SlickException {
        super(ref, name, false);
        this.target = this;
        this.tw = tw;
        this.th = th;
    }
    
    protected void initImpl() {
        if (this.subImages != null)
            return; 
        int tilesAcross = (getWidth() - this.margin * 2 - this.tw) / (this.tw + this.spacing) + 1;
        int tilesDown = (getHeight() - this.margin * 2 - this.th) / (this.th + this.spacing) + 1;
        if ((getHeight() - this.th) % (this.th + this.spacing) != 0)
            tilesDown++; 
        this.subImages = new Image[tilesAcross][tilesDown];
        for (int x = 0; x < tilesAcross; x++) {
            for (int y = 0; y < tilesDown; y++)
                this.subImages[x][y] = getSprite(x, y); 
        } 
    }
    
    public Image getSubImage(int x, int y) {
        init();
        if (x < 0 || x >= this.subImages.length)
            throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y); 
        if (y < 0 || y >= (this.subImages[0]).length)
            throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y); 
        return this.subImages[x][y];
    }
    
    public Image getSprite(int x, int y) {
        this.target.init();
        initImpl();
        if (x < 0 || x >= this.subImages.length)
            throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y); 
        if (y < 0 || y >= (this.subImages[0]).length)
            throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y); 
        return this.target.getSubImage(x * (this.tw + this.spacing) + this.margin, y * (this.th + this.spacing) + this.margin, this.tw, this.th);
    }
    
    public int getHorizontalCount() {
        this.target.init();
        initImpl();
        return this.subImages.length;
    }
    
    public int getVerticalCount() {
        this.target.init();
        initImpl();
        return (this.subImages[0]).length;
    }
    
    public void renderInUse(int x, int y, int sx, int sy) {
        this.subImages[sx][sy].drawEmbedded(x, y, this.tw, this.th);
    }
    
    public void endUse() {
        if (this.target == this) {
            super.endUse();
            return;
        } 
        this.target.endUse();
    }
    
    public void startUse() {
        if (this.target == this) {
            super.startUse();
            return;
        } 
        this.target.startUse();
    }
    
    public void setTexture(Texture texture) {
        if (this.target == this) {
            super.setTexture(texture);
            return;
        } 
        this.target.setTexture(texture);
    }
}
