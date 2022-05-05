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

public class ItemCloth extends ItemBlock {
    public ItemCloth(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    public int getMetadata(int damage) {
        return damage;
    }
    
    public String getUnlocalizedName(ItemStack stack) {
        return String.valueOf(getUnlocalizedName()) + "." + EnumDyeColor.byMetadata(stack.getMetadata()).getUnlocalizedName();
    }
}
