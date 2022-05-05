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
import java.util.Collection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionsStatus extends HudMod {
    protected float zLevelFloat;
    
    public PotionsStatus() {
        super("PotionsStatus", 0, 135);
    }
    
    public void draw() {
        int offsetX = 21;
        int offsetY = 14;
        int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = Minecraft.thePlayer.getActivePotionEffects();
        if (!collection.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;
            if (collection.size() > 5)
                l = 132 / (collection.size() - 1); 
            for (PotionEffect potioneffect : Minecraft.thePlayer.getActivePotionEffects()) {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                if (potion.hasStatusIcon()) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int i3 = potion.getStatusIconIndex();
                    drawTexturedModalRect(getX() + 21 - 20, getY() + i2 - 14, 0 + i3 % 8 * 18, 198 + i3 / 8 * 18, 18, 18);
                } 
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.2", new Object[0]);
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.3", new Object[0]);
                } else if (potioneffect.getAmplifier() == 3) {
                    s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.4", new Object[0]);
                } 
                this.fr.drawString(s1, (getX() + 21), (getY() + i2 - 14), 16777215, true);
                String s2 = Potion.getDurationString(potioneffect);
                this.fr.drawString(s2, (getX() + 21), (getY() + i2 + 10 - 14), 8355711, true);
                i2 += l;
            } 
        } 
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        int offsetX = 21;
        int offsetY = 14;
        int i = 80;
        int i2 = 16;
        PotionEffect[] potionEffects = { new PotionEffect(Potion.moveSpeed.id, 1200, 0), new PotionEffect(Potion.damageBoost.id, 1200, 0), new PotionEffect(Potion.fireResistance.id, 1200, 0) };
        int l = 33;
        if (potionEffects.length > 5)
            l = 132 / (potionEffects.length - 1); 
        PotionEffect[] array;
        for (int length = (array = potionEffects).length, j = 0; j < length; j++) {
            PotionEffect potioneffect = array[j];
            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            if (potion.hasStatusIcon()) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                int i3 = potion.getStatusIconIndex();
                drawTexturedModalRect(getX() + 21 - 20, getY() + i2 - 14, 0 + i3 % 8 * 18, 198 + i3 / 8 * 18, 18, 18);
            } 
            String s1 = I18n.format(potion.getName(), new Object[0]);
            if (potioneffect.getAmplifier() == 1) {
                s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.2", new Object[0]);
            } else if (potioneffect.getAmplifier() == 2) {
                s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.3", new Object[0]);
            } else if (potioneffect.getAmplifier() == 3) {
                s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.4", new Object[0]);
            } 
            this.fr.drawString(s1, (getX() + 21), (getY() + i2 - 14), 16777215, true);
            String s2 = Potion.getDurationString(potioneffect);
            this.fr.drawString(s2, (getX() + 21), (getY() + i2 + 10 - 14), 8355711, true);
            i2 += l;
        } 
        super.renderDummy(mouseX, mouseY);
    }
    
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625F;
        float f2 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((x + 0), (y + height), this.zLevelFloat).tex(((textureX + 0) * 0.00390625F), ((textureY + height) * 0.00390625F)).endVertex();
        worldrenderer.pos((x + width), (y + height), this.zLevelFloat).tex(((textureX + width) * 0.00390625F), ((textureY + height) * 0.00390625F)).endVertex();
        worldrenderer.pos((x + width), (y + 0), this.zLevelFloat).tex(((textureX + width) * 0.00390625F), ((textureY + 0) * 0.00390625F)).endVertex();
        worldrenderer.pos((x + 0), (y + 0), this.zLevelFloat).tex(((textureX + 0) * 0.00390625F), ((textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
    
    public int getWidth() {
        return 100;
    }
    
    public int getHeight() {
        return 90;
    }
}
