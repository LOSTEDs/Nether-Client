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
import net.minecraft.entity.EntityLivingBase;

public class TargetHUD extends HudMod {
    EntityLivingBase target;
    
    public TargetHUD() {
        super("Target HUD", 0, 110);
    }
    
    public void draw() {
        renderTargetHud();
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow("TargetHUD ", (getX() + 2), (getY() + 2), Client.TargetHUD);
        this.fr.drawStringWithShadow("20 §c❤", (getX() + 2), (getY() + 2 + this.fr.FONT_HEIGHT), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("TargetHUD ");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT * 2 + 4;
    }
    
    private void renderTargetHud() {
        if (!(this.mc.pointedEntity instanceof net.minecraft.entity.item.EntityItemFrame)) {
            this.target = (EntityLivingBase)this.mc.pointedEntity;
            if (this.target != null) {
                this.fr.drawStringWithShadow(this.target.getName(), (getX() + 2), (getY() + 2), Client.TargetHUD);
                this.fr.drawStringWithShadow(String.valueOf(String.valueOf((int)this.target.getHealth())) + "§c ❤", (getX() + 2), (getY() + 2 + this.fr.FONT_HEIGHT), -1);
            } 
        } 
    }
}
