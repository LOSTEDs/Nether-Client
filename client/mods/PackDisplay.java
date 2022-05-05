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
import net.minecraft.src.Config;

public class PackDisplay extends HudMod {
    public PackDisplay() {
        super("PackDisplay", 0, 60);
    }
    
    public void draw() {
        String pack = "";
        if (!Config.getResourcePackNames().equalsIgnoreCase("default")) {
            pack = Config.getResourcePackNames().split(",")[(Config.getResourcePacks()).length - 1];
        } else {
            pack = "default";
        } 
        if (!Config.getResourcePackNames().equals("default")) {
            this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Pack" + Client.Color0 + " : " + Client.ColorW + pack + Client.Color0 + " ]", getX(), getY(), -1);
        } else {
            this.fr.drawStringWithShadow("", getX(), getY(), -1);
        } 
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Pack" + Client.Color0 + " : " + Client.ColorW + "Default" + Client.Color0 + " ]", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ Pack : Default ]");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}
