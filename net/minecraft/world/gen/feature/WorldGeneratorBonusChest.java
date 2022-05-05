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

package net.minecraft.world.gen.feature;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldGeneratorBonusChest extends WorldGenerator {
    private final List<WeightedRandomChestContent> chestItems;
    
    private final int itemsToGenerateInBonusChest;
    
    public WorldGeneratorBonusChest(List<WeightedRandomChestContent> p_i45634_1_, int p_i45634_2_) {
        this.chestItems = p_i45634_1_;
        this.itemsToGenerateInBonusChest = p_i45634_2_;
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Block block;
        while (((block = worldIn.getBlockState(position).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && position.getY() > 1)
            position = position.down(); 
        if (position.getY() < 1)
            return false; 
        position = position.up();
        for (int i = 0; i < 4; i++) {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
            if (worldIn.isAirBlock(blockpos) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, blockpos.down())) {
                worldIn.setBlockState(blockpos, Blocks.chest.getDefaultState(), 2);
                TileEntity tileentity = worldIn.getTileEntity(blockpos);
                if (tileentity instanceof net.minecraft.tileentity.TileEntityChest)
                    WeightedRandomChestContent.generateChestContents(rand, this.chestItems, (IInventory)tileentity, this.itemsToGenerateInBonusChest); 
                BlockPos blockpos1 = blockpos.east();
                BlockPos blockpos2 = blockpos.west();
                BlockPos blockpos3 = blockpos.north();
                BlockPos blockpos4 = blockpos.south();
                if (worldIn.isAirBlock(blockpos2) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, blockpos2.down()))
                    worldIn.setBlockState(blockpos2, Blocks.torch.getDefaultState(), 2); 
                if (worldIn.isAirBlock(blockpos1) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, blockpos1.down()))
                    worldIn.setBlockState(blockpos1, Blocks.torch.getDefaultState(), 2); 
                if (worldIn.isAirBlock(blockpos3) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, blockpos3.down()))
                    worldIn.setBlockState(blockpos3, Blocks.torch.getDefaultState(), 2); 
                if (worldIn.isAirBlock(blockpos4) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, blockpos4.down()))
                    worldIn.setBlockState(blockpos4, Blocks.torch.getDefaultState(), 2); 
                return true;
            } 
        } 
        return false;
    }
}
