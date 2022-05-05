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

public class NBTTagByte extends NBTBase.NBTPrimitive {
    private byte data;
    
    NBTTagByte() {}
    
    public NBTTagByte(byte data) {
        this.data = data;
    }
    
    void write(DataOutput output) throws IOException {
        output.writeByte(this.data);
    }
    
    void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
        sizeTracker.read(72L);
        this.data = input.readByte();
    }
    
    public byte getId() {
        return 1;
    }
    
    public String toString() {
        return this.data + "b";
    }
    
    public NBTBase copy() {
        return new NBTTagByte(this.data);
    }
    
    public boolean equals(Object p_equals_1_) {
        if (super.equals(p_equals_1_)) {
            NBTTagByte nbttagbyte = (NBTTagByte)p_equals_1_;
            return (this.data == nbttagbyte.data);
        } 
        return false;
    }
    
    public int hashCode() {
        return super.hashCode() ^ this.data;
    }
    
    public long getLong() {
        return this.data;
    }
    
    public int getInt() {
        return this.data;
    }
    
    public short getShort() {
        return (short)this.data;
    }
    
    public byte getByte() {
        return this.data;
    }
    
    public double getDouble() {
        return this.data;
    }
    
    public float getFloat() {
        return this.data;
    }
}
