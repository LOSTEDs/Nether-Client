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
import net.minecraft.client.Minecraft;

public class FPSMod extends HudMod {
    public FPSMod() {
        super("FPS Display", 0, 90);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ FPS ] 100");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    public void draw() {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "FPS" + Client.Color0 + " ] " + Client.ColorW + Minecraft.getDebugFPS(), getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "FPS" + Client.Color0 + " ] " + Client.ColorW + "100", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
