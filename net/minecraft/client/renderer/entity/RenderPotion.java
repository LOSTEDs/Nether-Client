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

package net.minecraft.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RenderPotion extends RenderSnowball<EntityPotion> {
    public RenderPotion(RenderManager renderManagerIn, RenderItem itemRendererIn) {
        super(renderManagerIn, (Item)Items.potionitem, itemRendererIn);
    }
    
    public ItemStack func_177082_d(EntityPotion entityIn) {
        return new ItemStack(this.field_177084_a, 1, entityIn.getPotionDamage());
    }
}
