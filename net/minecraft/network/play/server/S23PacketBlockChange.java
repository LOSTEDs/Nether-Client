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

package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S23PacketBlockChange implements Packet<INetHandlerPlayClient> {
    private BlockPos blockPosition;
    
    private IBlockState blockState;
    
    public S23PacketBlockChange() {}
    
    public S23PacketBlockChange(World worldIn, BlockPos blockPositionIn) {
        this.blockPosition = blockPositionIn;
        this.blockState = worldIn.getBlockState(blockPositionIn);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.blockPosition = buf.readBlockPos();
        this.blockState = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer());
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.blockPosition);
        buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.blockState));
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleBlockChange(this);
    }
    
    public IBlockState getBlockState() {
        return this.blockState;
    }
    
    public BlockPos getBlockPosition() {
        return this.blockPosition;
    }
}
