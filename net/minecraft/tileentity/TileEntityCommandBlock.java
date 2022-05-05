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

import io.netty.buffer.ByteBuf;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntityCommandBlock extends TileEntity {
    private final CommandBlockLogic commandBlockLogic = new CommandBlockLogic() {
            public BlockPos getPosition() {
                return TileEntityCommandBlock.this.pos;
            }
            
            public Vec3 getPositionVector() {
                return new Vec3(TileEntityCommandBlock.this.pos.getX() + 0.5D, TileEntityCommandBlock.this.pos.getY() + 0.5D, TileEntityCommandBlock.this.pos.getZ() + 0.5D);
            }
            
            public World getEntityWorld() {
                return TileEntityCommandBlock.this.getWorld();
            }
            
            public void setCommand(String command) {
                super.setCommand(command);
                TileEntityCommandBlock.this.markDirty();
            }
            
            public void updateCommand() {
                TileEntityCommandBlock.this.getWorld().markBlockForUpdate(TileEntityCommandBlock.this.pos);
            }
            
            public int func_145751_f() {
                return 0;
            }
            
            public void func_145757_a(ByteBuf p_145757_1_) {
                p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getX());
                p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getY());
                p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getZ());
            }
            
            public Entity getCommandSenderEntity() {
                return null;
            }
        };
    
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.commandBlockLogic.writeDataToNBT(compound);
    }
    
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.commandBlockLogic.readDataFromNBT(compound);
    }
    
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return (Packet)new S35PacketUpdateTileEntity(this.pos, 2, nbttagcompound);
    }
    
    public boolean func_183000_F() {
        return true;
    }
    
    public CommandBlockLogic getCommandBlockLogic() {
        return this.commandBlockLogic;
    }
    
    public CommandResultStats getCommandResultStats() {
        return this.commandBlockLogic.getCommandResultStats();
    }
}
