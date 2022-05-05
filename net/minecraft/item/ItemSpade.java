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

package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ItemSpade extends ItemTool {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet((Object[])new Block[] { Blocks.clay, Blocks.dirt, Blocks.farmland, (Block)Blocks.grass, Blocks.gravel, (Block)Blocks.mycelium, (Block)Blocks.sand, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand });
    
    public ItemSpade(Item.ToolMaterial material) {
        super(1.0F, material, EFFECTIVE_ON);
    }
    
    public boolean canHarvestBlock(Block blockIn) {
        return (blockIn == Blocks.snow_layer) ? true : ((blockIn == Blocks.snow));
    }
}
