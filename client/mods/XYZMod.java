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

public class XYZMod extends HudMod {
    public XYZMod() {
        super("XYZ Display", 0, 0);
    }
    
    public void draw() {
        this.fr.drawStringWithShadow(getXYZString(), getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "XYZ" + Client.Color0 + " ] " + Client.ColorW + "100 / 50 / 100", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public String getXYZString() {
        return String.format(
                String.valueOf(Client.Color0) + "[ " + Client.ColorM + "XYZ" + Client.Color0 + " ] " + Client.ColorW + (int)Minecraft.thePlayer.posX + " / " + (int)Minecraft.thePlayer.posY + " / " + (int)Minecraft.thePlayer.posZ, new Object[0]);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ XYZ ] 100 / 50 / 100");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}
