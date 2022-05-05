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

package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTBase.NBTPrimitive {
    private long data;
    
    NBTTagLong() {}
    
    public NBTTagLong(long data) {
        this.data = data;
    }
    
    void write(DataOutput output) throws IOException {
        output.writeLong(this.data);
    }
    
    void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
        sizeTracker.read(128L);
        this.data = input.readLong();
    }
    
    public byte getId() {
        return 4;
    }
    
    public String toString() {
        return this.data + "L";
    }
    
    public NBTBase copy() {
        return new NBTTagLong(this.data);
    }
    
    public boolean equals(Object p_equals_1_) {
        if (super.equals(p_equals_1_)) {
            NBTTagLong nbttaglong = (NBTTagLong)p_equals_1_;
            return (this.data == nbttaglong.data);
        } 
        return false;
    }
    
    public int hashCode() {
        return super.hashCode() ^ (int)(this.data ^ this.data >>> 32L);
    }
    
    public long getLong() {
        return this.data;
    }
    
    public int getInt() {
        return (int)(this.data & 0xFFFFFFFFFFFFFFFFL);
    }
    
    public short getShort() {
        return (short)(int)(this.data & 0xFFFFL);
    }
    
    public byte getByte() {
        return (byte)(int)(this.data & 0xFFL);
    }
    
    public double getDouble() {
        return this.data;
    }
    
    public float getFloat() {
        return (float)this.data;
    }
}
