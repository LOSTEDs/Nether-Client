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

public class ServerIPMod extends HudMod {
    public ServerIPMod() {
        super("ServerIP Mod", 0, 340);
    }
    
    public void draw() {
        String address = "Singleplayer";
        if (Minecraft.getMinecraft().getCurrentServerData() != null)
            address = (Minecraft.getMinecraft().getCurrentServerData()).serverIP; 
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "IP" + Client.Color0 + " : " + Client.ColorW + address + Client.Color0 + " ]", getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "IP" + Client.Color0 + " : " + Client.ColorW + "hypixel.net" + Client.Color0 + " ]", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ IP : hypixel.net ]");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}
