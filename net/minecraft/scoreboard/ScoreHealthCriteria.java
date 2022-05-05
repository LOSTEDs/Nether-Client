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

package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
    public ScoreHealthCriteria(String name) {
        super(name);
    }
    
    public int func_96635_a(List<EntityPlayer> p_96635_1_) {
        float f = 0.0F;
        for (EntityPlayer entityplayer : p_96635_1_)
            f += entityplayer.getHealth() + entityplayer.getAbsorptionAmount(); 
        if (p_96635_1_.size() > 0)
            f /= p_96635_1_.size(); 
        return MathHelper.ceiling_float_int(f);
    }
    
    public boolean isReadOnly() {
        return true;
    }
    
    public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
        return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
    }
}
