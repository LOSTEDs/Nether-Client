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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorStatus extends HudMod {
    public ArmorStatus() {
        super("ArmorStatus", 617, 287);
    }
    
    public void draw() {
        for (int i = 0; i < Minecraft.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemStack = Minecraft.thePlayer.inventory.armorInventory[i];
            renderItemStack(i, itemStack);
        } 
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        renderItemStack(3, new ItemStack((Item)Items.diamond_helmet));
        renderItemStack(2, new ItemStack((Item)Items.diamond_chestplate));
        renderItemStack(1, new ItemStack((Item)Items.diamond_leggings));
        renderItemStack(0, new ItemStack((Item)Items.diamond_boots));
        super.renderDummy(mouseX, mouseY);
    }
    
    public void renderItemStack(int i, ItemStack is) {
        if (is == null)
            return; 
        GL11.glPushMatrix();
        int yAdd = -16 * i + 48;
        if (is.getItem().isDamageable()) {
            double damage = (is.getMaxDamage() - is.getItemDamage()) / is.getMaxDamage() * 100.0D;
            this.fr.drawStringWithShadow(String.format("%.2f%%", new Object[] { Double.valueOf(damage) }), (getX() + 20), (getY() + yAdd + 5), -1);
        } 
        RenderHelper.enableGUIStandardItemLighting();
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(is, getX(), getY() + yAdd);
        GL11.glPopMatrix();
    }
    
    public int getWidth() {
        return 64;
    }
    
    public int getHeight() {
        return 64;
    }
}
