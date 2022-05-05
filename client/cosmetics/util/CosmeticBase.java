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

package client.cosmetics.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer> {
    protected final RenderPlayer renderPlayer;
    
    public CosmeticBase(RenderPlayer player) {
        this.renderPlayer = player;
    }
    
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if (player.hasPlayerInfo() && !player.isInvisible())
            render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale); 
    }
    
    public boolean shouldCombineTextures() {
        return false;
    }
    
    public abstract void render(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7);
}
