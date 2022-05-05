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

public class MetadataPlayerDeath extends Metadata {
    public MetadataPlayerDeath(EntityLivingBase p_i46066_1_, EntityLivingBase p_i46066_2_) {
        super("player_death");
        if (p_i46066_1_ != null)
            func_152808_a("player", p_i46066_1_.getName()); 
        if (p_i46066_2_ != null)
            func_152808_a("killer", p_i46066_2_.getName()); 
    }
}
