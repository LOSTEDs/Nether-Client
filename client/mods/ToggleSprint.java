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

public class ToggleSprint extends HudMod {
    public boolean flyBoost = false;
    
    public float flyBoostFactor = 1.0F;
    
    public int keyHoldTicks = 7;
    
    public boolean shiftToggled = false;
    
    public ToggleSprint() {
        super("ToggleSprint", 465, 340);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[Sprinting (Key Toggled)]");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
    
    public void draw() {
        String textToRender = Minecraft.thePlayer.movementInput.getDisplayText();
        this.fr.drawStringWithShadow(textToRender, getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[" + Client.ColorM + "Sprinting" + Client.ColorW + " (Key Toggled)" + Client.Color0 + "]", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
