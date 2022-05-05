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

import client.hud.impl.HudMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArrowCounter extends HudMod {
    public ArrowCounter() {
        super("ArrowCounter", 433, 327);
    }
    
    public void draw() {
        if (getRemainingArrows() < 1) {
            this.fr.drawStringWithShadow((new StringBuilder(String.valueOf(getRemainingArrows()))).toString(), (getX() + 8), (getY() + 15), Color.RED.getRGB());
        } else {
            this.fr.drawStringWithShadow((new StringBuilder(String.valueOf(getRemainingArrows()))).toString(), (getX() + 8), (getY() + 15), Color.WHITE.getRGB());
        } 
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), getX() + 3, getY() - 1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        if (getRemainingArrows() < 1) {
            this.fr.drawStringWithShadow((new StringBuilder(String.valueOf(getRemainingArrows()))).toString(), (getX() + 8), (getY() + 15), Color.RED.getRGB());
        } else {
            this.fr.drawStringWithShadow((new StringBuilder(String.valueOf(getRemainingArrows()))).toString(), (getX() + 8), (getY() + 15), Color.WHITE.getRGB());
        } 
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), getX() + 3, getY() - 1);
        super.renderDummy(mouseX, mouseY);
    }
    
    private int getRemainingArrows() {
        int i = 0;
        ItemStack[] mainInventory;
        for (int length = (mainInventory = Minecraft.thePlayer.inventory.mainInventory).length, j = 0; j < length; j++) {
            ItemStack itemstack = mainInventory[j];
            if (itemstack != null && itemstack.getItem().equals(Items.arrow))
                i += itemstack.stackSize; 
        } 
        return i;
    }
    
    public int getWidth() {
        return 23;
    }
    
    public int getHeight() {
        return 23;
    }
}
