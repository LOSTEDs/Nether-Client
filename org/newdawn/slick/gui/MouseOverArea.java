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

package org.newdawn.slick.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class MouseOverArea extends AbstractComponent {
    private static final int NORMAL = 1;
    
    private static final int MOUSE_DOWN = 2;
    
    private static final int MOUSE_OVER = 3;
    
    private Image normalImage;
    
    private Image mouseOverImage;
    
    private Image mouseDownImage;
    
    private Color normalColor = Color.white;
    
    private Color mouseOverColor = Color.white;
    
    private Color mouseDownColor = Color.white;
    
    private Sound mouseOverSound;
    
    private Sound mouseDownSound;
    
    private Shape area;
    
    private Image currentImage;
    
    private Color currentColor;
    
    private boolean over;
    
    private boolean mouseDown;
    
    private int state = 1;
    
    private boolean mouseUp;
    
    public MouseOverArea(GUIContext container, Image image, int x, int y, ComponentListener listener) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
        addListener(listener);
    }
    
    public MouseOverArea(GUIContext container, Image image, int x, int y) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
    }
    
    public MouseOverArea(GUIContext container, Image image, int x, int y, int width, int height, ComponentListener listener) {
        this(container, image, x, y, width, height);
        addListener(listener);
    }
    
    public MouseOverArea(GUIContext container, Image image, int x, int y, int width, int height) {
        this(container, image, (Shape)new Rectangle(x, y, width, height));
    }
    
    public MouseOverArea(GUIContext container, Image image, Shape shape) {
        super(container);
        this.area = shape;
        this.normalImage = image;
        this.currentImage = image;
        this.mouseOverImage = image;
        this.mouseDownImage = image;
        this.currentColor = this.normalColor;
        this.state = 1;
        Input input = container.getInput();
        this.over = this.area.contains(input.getMouseX(), input.getMouseY());
        this.mouseDown = input.isMouseButtonDown(0);
        updateImage();
    }
    
    public void setLocation(float x, float y) {
        if (this.area != null) {
            this.area.setX(x);
            this.area.setY(y);
        } 
    }
    
    public void setX(float x) {
        this.area.setX(x);
    }
    
    public void setY(float y) {
        this.area.setY(y);
    }
    
    public int getX() {
        return (int)this.area.getX();
    }
    
    public int getY() {
        return (int)this.area.getY();
    }
    
    public void setNormalColor(Color color) {
        this.normalColor = color;
    }
    
    public void setMouseOverColor(Color color) {
        this.mouseOverColor = color;
    }
    
    public void setMouseDownColor(Color color) {
        this.mouseDownColor = color;
    }
    
    public void setNormalImage(Image image) {
        this.normalImage = image;
    }
    
    public void setMouseOverImage(Image image) {
        this.mouseOverImage = image;
    }
    
    public void setMouseDownImage(Image image) {
        this.mouseDownImage = image;
    }
    
    public void render(GUIContext container, Graphics g) {
        if (this.currentImage != null) {
            int xp = (int)(this.area.getX() + ((getWidth() - this.currentImage.getWidth()) / 2));
            int yp = (int)(this.area.getY() + ((getHeight() - this.currentImage.getHeight()) / 2));
            this.currentImage.draw(xp, yp, this.currentColor);
        } else {
            g.setColor(this.currentColor);
            g.fill(this.area);
        } 
        updateImage();
    }
    
    private void updateImage() {
        if (!this.over) {
            this.currentImage = this.normalImage;
            this.currentColor = this.normalColor;
            this.state = 1;
            this.mouseUp = false;
        } else {
            if (this.mouseDown) {
                if (this.state != 2 && this.mouseUp) {
                    if (this.mouseDownSound != null)
                        this.mouseDownSound.play(); 
                    this.currentImage = this.mouseDownImage;
                    this.currentColor = this.mouseDownColor;
                    this.state = 2;
                    notifyListeners();
                    this.mouseUp = false;
                } 
                return;
            } 
            this.mouseUp = true;
            if (this.state != 3) {
                if (this.mouseOverSound != null)
                    this.mouseOverSound.play(); 
                this.currentImage = this.mouseOverImage;
                this.currentColor = this.mouseOverColor;
                this.state = 3;
            } 
        } 
        this.mouseDown = false;
        this.state = 1;
    }
    
    public void setMouseOverSound(Sound sound) {
        this.mouseOverSound = sound;
    }
    
    public void setMouseDownSound(Sound sound) {
        this.mouseDownSound = sound;
    }
    
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        this.over = this.area.contains(newx, newy);
    }
    
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        mouseMoved(oldx, oldy, newx, newy);
    }
    
    public void mousePressed(int button, int mx, int my) {
        this.over = this.area.contains(mx, my);
        if (button == 0)
            this.mouseDown = true; 
    }
    
    public void mouseReleased(int button, int mx, int my) {
        this.over = this.area.contains(mx, my);
        if (button == 0)
            this.mouseDown = false; 
    }
    
    public int getHeight() {
        return (int)(this.area.getMaxY() - this.area.getY());
    }
    
    public int getWidth() {
        return (int)(this.area.getMaxX() - this.area.getX());
    }
    
    public boolean isMouseOver() {
        return this.over;
    }
    
    public void setLocation(int x, int y) {
        setLocation(x, y);
    }
}
