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

public class PingMod extends HudMod {
    public PingMod() {
        super("Ping Display", 0, 80);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ Ping ] 10ms");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    public void draw() {
        if (this.mc.getNetHandler() != null && Minecraft.thePlayer != null && this.mc.getNetHandler().getPlayerInfo(Minecraft.thePlayer.getUniqueID()) != null)
            this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Ping" + Client.Color0 + " ] " + Client.ColorW + this.mc.getNetHandler().getPlayerInfo(Minecraft.thePlayer.getUniqueID()).getResponseTime() + "ms", getX(), getY(), -1); 
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Ping" + Client.Color0 + " ] " + Client.ColorW + "10ms", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
