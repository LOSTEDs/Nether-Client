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

package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
    private ItemCameraTransforms cameraTransforms;
    
    public BuiltInModel(ItemCameraTransforms p_i46086_1_) {
        this.cameraTransforms = p_i46086_1_;
    }
    
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return null;
    }
    
    public List<BakedQuad> getGeneralQuads() {
        return null;
    }
    
    public boolean isAmbientOcclusion() {
        return false;
    }
    
    public boolean isGui3d() {
        return true;
    }
    
    public boolean isBuiltInRenderer() {
        return true;
    }
    
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }
    
    public ItemCameraTransforms getItemCameraTransforms() {
        return this.cameraTransforms;
    }
}
