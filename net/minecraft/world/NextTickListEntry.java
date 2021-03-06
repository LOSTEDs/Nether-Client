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

package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class NextTickListEntry implements Comparable<NextTickListEntry> {
    private static long nextTickEntryID;
    
    private final Block block;
    
    public final BlockPos position;
    
    public long scheduledTime;
    
    public int priority;
    
    private long tickEntryID;
    
    public NextTickListEntry(BlockPos p_i45745_1_, Block p_i45745_2_) {
        this.tickEntryID = nextTickEntryID++;
        this.position = p_i45745_1_;
        this.block = p_i45745_2_;
    }
    
    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof NextTickListEntry))
            return false; 
        NextTickListEntry nextticklistentry = (NextTickListEntry)p_equals_1_;
        return (this.position.equals(nextticklistentry.position) && Block.isEqualTo(this.block, nextticklistentry.block));
    }
    
    public int hashCode() {
        return this.position.hashCode();
    }
    
    public NextTickListEntry setScheduledTime(long p_77176_1_) {
        this.scheduledTime = p_77176_1_;
        return this;
    }
    
    public void setPriority(int p_82753_1_) {
        this.priority = p_82753_1_;
    }
    
    public int compareTo(NextTickListEntry p_compareTo_1_) {
        return (this.scheduledTime < p_compareTo_1_.scheduledTime) ? -1 : ((this.scheduledTime > p_compareTo_1_.scheduledTime) ? 1 : ((this.priority != p_compareTo_1_.priority) ? (this.priority - p_compareTo_1_.priority) : ((this.tickEntryID < p_compareTo_1_.tickEntryID) ? -1 : ((this.tickEntryID > p_compareTo_1_.tickEntryID) ? 1 : 0))));
    }
    
    public String toString() {
        return String.valueOf(Block.getIdFromBlock(this.block)) + ": " + this.position + ", " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
    }
    
    public Block getBlock() {
        return this.block;
    }
}
