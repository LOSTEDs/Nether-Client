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

package net.minecraft.world.gen.structure;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class MapGenStructureData extends WorldSavedData {
    private NBTTagCompound tagCompound = new NBTTagCompound();
    
    public MapGenStructureData(String name) {
        super(name);
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        this.tagCompound = nbt.getCompoundTag("Features");
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("Features", (NBTBase)this.tagCompound);
    }
    
    public void writeInstance(NBTTagCompound tagCompoundIn, int chunkX, int chunkZ) {
        this.tagCompound.setTag(formatChunkCoords(chunkX, chunkZ), (NBTBase)tagCompoundIn);
    }
    
    public static String formatChunkCoords(int chunkX, int chunkZ) {
        return "[" + chunkX + "," + chunkZ + "]";
    }
    
    public NBTTagCompound getTagCompound() {
        return this.tagCompound;
    }
}
