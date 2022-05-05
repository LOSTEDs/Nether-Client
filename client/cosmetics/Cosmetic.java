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

package client.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public abstract class Cosmetic extends ModelBiped implements LayerRenderer<AbstractClientPlayer> {
    ModelBiped playerModel;
    
    public Cosmetic(RenderPlayer player) {
        this.playerModel = (ModelBiped)player.getMainModel();
    }
    
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if (player.hasPlayerInfo() && !player.isInvisible())
            render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale); 
    }
    
    public boolean shouldCombineTextures() {
        return false;
    }
    
    private static float Sigmoid(double value) {
        return 1.0F / (1.0F + (float)Math.exp(-value));
    }
    
    public abstract void render(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7);
}
