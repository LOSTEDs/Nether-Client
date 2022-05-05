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

package net.minecraft.client.stream;

import net.minecraft.entity.EntityLivingBase;

public class MetadataCombat extends Metadata {
    public MetadataCombat(EntityLivingBase p_i46067_1_, EntityLivingBase p_i46067_2_) {
        super("player_combat");
        func_152808_a("player", p_i46067_1_.getName());
        if (p_i46067_2_ != null)
            func_152808_a("primary_opponent", p_i46067_2_.getName()); 
        if (p_i46067_2_ != null) {
            func_152807_a("Combat between " + p_i46067_1_.getName() + " and " + p_i46067_2_.getName());
        } else {
            func_152807_a("Combat between " + p_i46067_1_.getName() + " and others");
        } 
    }
}
