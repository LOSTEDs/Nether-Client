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

public class MemoryUsage extends HudMod {
    public MemoryUsage() {
        super("MemoryUsage", 0, 40);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ Memory ] 100%");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    public void draw() {
        Runtime runtime = Runtime.getRuntime();
        String str = String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Memory" + Client.Color0 + " ] " + Client.ColorW + ((runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory()) + "% ";
        this.fr.drawStringWithShadow(str, getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Memory" + Client.Color0 + " ] " + Client.ColorW + "100%", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
