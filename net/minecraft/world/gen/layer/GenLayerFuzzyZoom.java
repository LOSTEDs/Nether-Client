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

package net.minecraft.world.gen.layer;

public class GenLayerFuzzyZoom extends GenLayerZoom {
    public GenLayerFuzzyZoom(long p_i2123_1_, GenLayer p_i2123_3_) {
        super(p_i2123_1_, p_i2123_3_);
    }
    
    protected int selectModeOrRandom(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
        return selectRandom(new int[] { p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_ });
    }
}
