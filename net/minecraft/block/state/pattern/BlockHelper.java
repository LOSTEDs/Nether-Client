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

package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockHelper implements Predicate<IBlockState> {
    private final Block block;
    
    private BlockHelper(Block blockType) {
        this.block = blockType;
    }
    
    public static BlockHelper forBlock(Block blockType) {
        return new BlockHelper(blockType);
    }
    
    public boolean apply(IBlockState p_apply_1_) {
        return (p_apply_1_ != null && p_apply_1_.getBlock() == this.block);
    }
}
