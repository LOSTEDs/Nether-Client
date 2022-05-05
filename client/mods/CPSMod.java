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

package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.lwjgl.input.Mouse;

public class CPSMod extends HudMod {
    public List<Long> clicks = new ArrayList<>();
    
    public boolean wasPressed;
    
    public long lastPressed;
    
    private List<Long> clicks2 = new ArrayList<>();
    
    private boolean wasPressed2;
    
    private long lastPressed2;
    
    public CPSMod() {
        super("CPS Display", 0, 100);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ CPS ] 0 | 0");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    public void draw() {
        boolean pressed = Mouse.isButtonDown(0);
        boolean rpressed = Mouse.isButtonDown(1);
        if (pressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = pressed;
            if (pressed)
                this.clicks.add(Long.valueOf(this.lastPressed)); 
        } 
        if (rpressed != this.wasPressed2) {
            this.lastPressed2 = System.currentTimeMillis() + 10L;
            this.wasPressed2 = rpressed;
            if (rpressed)
                this.clicks2.add(Long.valueOf(this.lastPressed2)); 
        } 
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "CPS" + Client.Color0 + " ] " + Client.ColorW + getCPS() + " | " + getCPS2(), getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "CPS" + Client.Color0 + " ] " + Client.ColorW + "0 | 0", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getCPS() {
        final long time = System.currentTimeMillis();
        this.clicks.removeIf(new Predicate<Long>() {
                    public boolean test(Long aLong) {
                        return (aLong.longValue() + 1000L < time);
                    }
                });
        return this.clicks.size();
    }
    
    public int getCPS2() {
        final long time2 = System.currentTimeMillis();
        this.clicks2.removeIf(new Predicate<Long>() {
                    public boolean test(Long aLong2) {
                        return (aLong2.longValue() + 1000L < time2);
                    }
                });
        return this.clicks2.size();
    }
}
