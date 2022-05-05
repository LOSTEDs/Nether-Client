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

package net.optifine.entity.model;

import net.minecraft.util.ResourceLocation;

public class CustomEntityRenderer {
    private String name = null;
    
    private String basePath = null;
    
    private ResourceLocation textureLocation = null;
    
    private CustomModelRenderer[] customModelRenderers = null;
    
    private float shadowSize = 0.0F;
    
    public CustomEntityRenderer(String name, String basePath, ResourceLocation textureLocation, CustomModelRenderer[] customModelRenderers, float shadowSize) {
        this.name = name;
        this.basePath = basePath;
        this.textureLocation = textureLocation;
        this.customModelRenderers = customModelRenderers;
        this.shadowSize = shadowSize;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getBasePath() {
        return this.basePath;
    }
    
    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }
    
    public CustomModelRenderer[] getCustomModelRenderers() {
        return this.customModelRenderers;
    }
    
    public float getShadowSize() {
        return this.shadowSize;
    }
}
