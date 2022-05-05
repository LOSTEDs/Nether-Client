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

package net.minecraft.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityMobSpawner extends TileEntity implements ITickable {
    private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
            public void func_98267_a(int id) {
                TileEntityMobSpawner.this.worldObj.addBlockEvent(TileEntityMobSpawner.this.pos, Blocks.mob_spawner, id, 0);
            }
            
            public World getSpawnerWorld() {
                return TileEntityMobSpawner.this.worldObj;
            }
            
            public BlockPos getSpawnerPosition() {
                return TileEntityMobSpawner.this.pos;
            }
            
            public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_) {
                super.setRandomEntity(p_98277_1_);
                if (getSpawnerWorld() != null)
                    getSpawnerWorld().markBlockForUpdate(TileEntityMobSpawner.this.pos); 
            }
        };
    
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.spawnerLogic.readFromNBT(compound);
    }
    
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.spawnerLogic.writeToNBT(compound);
    }
    
    public void update() {
        this.spawnerLogic.updateSpawner();
    }
    
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        nbttagcompound.removeTag("SpawnPotentials");
        return (Packet)new S35PacketUpdateTileEntity(this.pos, 1, nbttagcompound);
    }
    
    public boolean receiveClientEvent(int id, int type) {
        return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
    }
    
    public boolean func_183000_F() {
        return true;
    }
    
    public MobSpawnerBaseLogic getSpawnerBaseLogic() {
        return this.spawnerLogic;
    }
}
