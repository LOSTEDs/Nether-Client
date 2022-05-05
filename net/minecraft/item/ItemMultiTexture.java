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

import com.google.common.base.Function;
import net.minecraft.block.Block;

public class ItemMultiTexture extends ItemBlock {
    protected final Block theBlock;
    
    protected final Function<ItemStack, String> nameFunction;
    
    public ItemMultiTexture(Block block, Block block2, Function<ItemStack, String> nameFunction) {
        super(block);
        this.theBlock = block2;
        this.nameFunction = nameFunction;
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public ItemMultiTexture(Block block, Block block2, String[] namesByMeta) {
        this(block, block2, new Function<ItemStack, String>(namesByMeta) {
                    public String apply(ItemStack p_apply_1_) {
                        int i = p_apply_1_.getMetadata();
                        if (i < 0 || i >= namesByMeta.length)
                            i = 0; 
                        return namesByMeta[i];
                    }
                });
    }
    
    public int getMetadata(int damage) {
        return damage;
    }
    
    public String getUnlocalizedName(ItemStack stack) {
        return String.valueOf(getUnlocalizedName()) + "." + (String)this.nameFunction.apply(stack);
    }
}
