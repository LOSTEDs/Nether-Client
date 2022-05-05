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
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ItemViewer extends HudMod {
    public ItemViewer() {
        super("Item Viewer", 188, 333);
    }
    
    public int getWidth() {
        return 60;
    }
    
    public int getHeight() {
        return 17;
    }
    
    public void draw() {
        ItemStack item = Minecraft.thePlayer.getHeldItem();
        renderItemStack(2, item);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        renderItemStack(2, new ItemStack(Items.diamond_sword));
        super.renderDummy(mouseX, mouseY);
    }
    
    private void renderItemStack(int i, ItemStack is) {
        if (is == null)
            return; 
        GL11.glPushMatrix();
        int yAdd = 0;
        if (Minecraft.thePlayer != null && is != null) {
            if (is.getItem().isDamageable()) {
                double damage = (is.getMaxDamage() - is.getItemDamage()) / is.getMaxDamage() * 100.0D;
                this.fr.drawStringWithShadow(String.format("%.2f%%", new Object[] { Double.valueOf(damage) }), (getX() + 20), (getY() + 0 + 5), -1);
            } 
            if (is.isStackable() && (Minecraft.thePlayer.getHeldItem()).stackSize != 1)
                this.fr.drawStringWithShadow(Integer.toString((Minecraft.thePlayer.getHeldItem()).stackSize), (getX() + 20), (getY() + 0 + 5), -1); 
            RenderHelper.enableGUIStandardItemLighting();
            this.mc.getRenderItem().renderItemIntoGUI(is, getX(), getY() + 0);
            GL11.glPopMatrix();
        } 
    }
}
