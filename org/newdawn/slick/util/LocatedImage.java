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

package org.newdawn.slick.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class LocatedImage {
    private Image image;
    
    private int x;
    
    private int y;
    
    private Color filter = Color.white;
    
    private float width;
    
    private float height;
    
    public LocatedImage(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setHeight(float height) {
        this.height = height;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
    
    public void setColor(Color c) {
        this.filter = c;
    }
    
    public Color getColor() {
        return this.filter;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void draw() {
        this.image.draw(this.x, this.y, this.width, this.height, this.filter);
    }
}
