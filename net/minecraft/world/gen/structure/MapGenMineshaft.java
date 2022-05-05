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

import java.util.Map;
import net.minecraft.util.MathHelper;

public class MapGenMineshaft extends MapGenStructure {
    private double field_82673_e = 0.004D;
    
    public MapGenMineshaft() {}
    
    public String getStructureName() {
        return "Mineshaft";
    }
    
    public MapGenMineshaft(Map<String, String> p_i2034_1_) {
        for (Map.Entry<String, String> entry : p_i2034_1_.entrySet()) {
            if (((String)entry.getKey()).equals("chance"))
                this.field_82673_e = MathHelper.parseDoubleWithDefault(entry.getValue(), this.field_82673_e); 
        } 
    }
    
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return (this.rand.nextDouble() < this.field_82673_e && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ)));
    }
    
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureMineshaftStart(this.worldObj, this.rand, chunkX, chunkZ);
    }
}
