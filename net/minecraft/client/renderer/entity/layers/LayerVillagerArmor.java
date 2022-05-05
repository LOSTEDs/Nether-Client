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

package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;

public class LayerVillagerArmor extends LayerBipedArmor {
    public LayerVillagerArmor(RendererLivingEntity<?> rendererIn) {
        super(rendererIn);
    }
    
    protected void initArmor() {
        this.field_177189_c = (ModelBiped)new ModelZombieVillager(0.5F, 0.0F, true);
        this.field_177186_d = (ModelBiped)new ModelZombieVillager(1.0F, 0.0F, true);
    }
}
