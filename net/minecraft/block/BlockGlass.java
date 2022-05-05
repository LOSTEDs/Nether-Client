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

package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockGlass extends BlockBreakable {
    public BlockGlass(Material materialIn, boolean ignoreSimilarity) {
        super(materialIn, ignoreSimilarity);
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public int quantityDropped(Random random) {
        return 0;
    }
    
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    public boolean isFullCube() {
        return false;
    }
    
    protected boolean canSilkHarvest() {
        return true;
    }
}
