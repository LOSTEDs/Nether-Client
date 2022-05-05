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

package client.hud;

import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class DraggableComponent {
    private int x;
    
    private int y;
    
    private int width;
    
    private int height;
    
    private int color;
    
    private int lastX;
    
    private int lastY;
    
    private boolean dragging;
    
    public DraggableComponent(int x, int y, int width, int height, int color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public int getxPosition() {
        return this.x;
    }
    
    public int getyPosition() {
        return this.y;
    }
    
    public void setxPosition(int x) {
        this.x = x;
    }
    
    public void setyPosition(int y) {
        this.y = y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public void draw(int mouseX, int mouseY) {
        draggingFix(mouseX, mouseY);
        Gui.drawRect(getxPosition(), getyPosition(), getxPosition() + getWidth(), getyPosition() + getHeight(), getColor());
        boolean mouseOverX = (mouseX >= getxPosition() && mouseX <= getxPosition() + getWidth());
        boolean mouseOverY = (mouseY >= getyPosition() && mouseY <= getyPosition() + getHeight());
        if (mouseOverX && mouseOverY && 
            Mouse.isButtonDown(0) && 
            !this.dragging) {
            this.lastX = this.x - mouseX;
            this.lastY = this.y - mouseY;
            this.dragging = true;
        } 
    }
    
    private void draggingFix(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            if (!Mouse.isButtonDown(0))
                this.dragging = false; 
        } 
    }
}
