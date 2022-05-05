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

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;

public class ItemLeaves extends ItemBlock {
    private final BlockLeaves leaves;
    
    public ItemLeaves(BlockLeaves block) {
        super((Block)block);
        this.leaves = block;
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public int getMetadata(int damage) {
        return damage | 0x4;
    }
    
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return this.leaves.getRenderColor(this.leaves.getStateFromMeta(stack.getMetadata()));
    }
    
    public String getUnlocalizedName(ItemStack stack) {
        return String.valueOf(getUnlocalizedName()) + "." + this.leaves.getWoodType(stack.getMetadata()).getUnlocalizedName();
    }
}
