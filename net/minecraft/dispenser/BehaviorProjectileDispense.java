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

package net.minecraft.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BehaviorProjectileDispense extends BehaviorDefaultDispenseItem {
    public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        World world = source.getWorld();
        IPosition iposition = BlockDispenser.getDispensePosition(source);
        EnumFacing enumfacing = BlockDispenser.getFacing(source.getBlockMetadata());
        IProjectile iprojectile = getProjectileEntity(world, iposition);
        iprojectile.setThrowableHeading(enumfacing.getFrontOffsetX(), (enumfacing.getFrontOffsetY() + 0.1F), enumfacing.getFrontOffsetZ(), func_82500_b(), func_82498_a());
        world.spawnEntityInWorld((Entity)iprojectile);
        stack.splitStack(1);
        return stack;
    }
    
    protected void playDispenseSound(IBlockSource source) {
        source.getWorld().playAuxSFX(1002, source.getBlockPos(), 0);
    }
    
    protected abstract IProjectile getProjectileEntity(World paramWorld, IPosition paramIPosition);
    
    protected float func_82498_a() {
        return 6.0F;
    }
    
    protected float func_82500_b() {
        return 1.1F;
    }
}
