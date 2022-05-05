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

public class ItemColored extends ItemBlock {
    private final Block coloredBlock;
    
    private String[] subtypeNames;
    
    public ItemColored(Block block, boolean hasSubtypes) {
        super(block);
        this.coloredBlock = block;
        if (hasSubtypes) {
            setMaxDamage(0);
            setHasSubtypes(true);
        } 
    }
    
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return this.coloredBlock.getRenderColor(this.coloredBlock.getStateFromMeta(stack.getMetadata()));
    }
    
    public int getMetadata(int damage) {
        return damage;
    }
    
    public ItemColored setSubtypeNames(String[] names) {
        this.subtypeNames = names;
        return this;
    }
    
    public String getUnlocalizedName(ItemStack stack) {
        if (this.subtypeNames == null)
            return super.getUnlocalizedName(stack); 
        int i = stack.getMetadata();
        return (i >= 0 && i < this.subtypeNames.length) ? (String.valueOf(super.getUnlocalizedName(stack)) + "." + this.subtypeNames[i]) : super.getUnlocalizedName(stack);
    }
}
