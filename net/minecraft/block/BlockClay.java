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
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockClay extends Block {
    public BlockClay() {
        super(Material.clay);
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.clay_ball;
    }
    
    public int quantityDropped(Random random) {
        return 4;
    }
}
