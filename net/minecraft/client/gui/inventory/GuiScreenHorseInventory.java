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

package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiScreenHorseInventory extends GuiContainer {
    private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
    
    private IInventory playerInventory;
    
    private IInventory horseInventory;
    
    private EntityHorse horseEntity;
    
    private float mousePosx;
    
    private float mousePosY;
    
    public GuiScreenHorseInventory(IInventory playerInv, IInventory horseInv, EntityHorse horse) {
        super((Container)new ContainerHorseInventory(playerInv, horseInv, horse, (EntityPlayer)Minecraft.thePlayer));
        this.playerInventory = playerInv;
        this.horseInventory = horseInv;
        this.horseEntity = horse;
        this.allowUserInput = false;
    }
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(this.horseInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(horseGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        if (this.horseEntity.isChested())
            drawTexturedModalRect(i + 79, j + 17, 0, this.ySize, 90, 54); 
        if (this.horseEntity.canWearArmor())
            drawTexturedModalRect(i + 7, j + 35, 0, this.ySize + 54, 18, 18); 
        GuiInventory.drawEntityOnScreen(i + 51, j + 60, 17, (i + 51) - this.mousePosx, (j + 75 - 50) - this.mousePosY, (EntityLivingBase)this.horseEntity);
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mousePosx = mouseX;
        this.mousePosY = mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
