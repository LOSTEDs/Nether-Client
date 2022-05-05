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
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;

public class BPSMod extends HudMod {
    static Minecraft mc = Minecraft.getMinecraft();
    
    private int decimals;
    
    public BPSMod() {
        super("BPS Display", 0, 70);
    }
    
    public void draw() {
        float rat = (Minecraft.getMinecraft()).timer.getTicksPerSecond() * (Minecraft.getMinecraft()).timer.timerSpeed;
        double speed = Math.sqrt(Minecraft.thePlayer.motionX * Minecraft.thePlayer.motionX + Minecraft.thePlayer.motionZ * Minecraft.thePlayer.motionZ) * rat;
        mc.fontRendererObj.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "BPS" + Client.Color0 + " : " + Client.ColorW + getFormatter().format(speed) + Client.Color0 + " ]", getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "BPS" + Client.Color0 + " : " + Client.ColorW + "0.00" + Client.Color0 + " ]", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ BPS : 0.00 ]");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    private DecimalFormat getFormatter() {
        StringBuilder format = new StringBuilder("0.00");
        for (int i = 100; this.decimals > i; i++)
            format.append('0'); 
        return new DecimalFormat(format.toString());
    }
}
