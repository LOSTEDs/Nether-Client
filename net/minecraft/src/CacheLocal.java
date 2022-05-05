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

package net.minecraft.src;

public class CacheLocal {
    private int maxX = 18;
    
    private int maxY = 128;
    
    private int maxZ = 18;
    
    private int offsetX = 0;
    
    private int offsetY = 0;
    
    private int offsetZ = 0;
    
    private int[][][] cache = null;
    
    private int[] lastZs = null;
    
    private int lastDz = 0;
    
    public CacheLocal(int p_i26_1_, int p_i26_2_, int p_i26_3_) {
        this.maxX = p_i26_1_;
        this.maxY = p_i26_2_;
        this.maxZ = p_i26_3_;
        this.cache = new int[p_i26_1_][p_i26_2_][p_i26_3_];
        resetCache();
    }
    
    public void resetCache() {
        for (int i = 0; i < this.maxX; i++) {
            int[][] aint = this.cache[i];
            for (int j = 0; j < this.maxY; j++) {
                int[] aint1 = aint[j];
                for (int k = 0; k < this.maxZ; k++)
                    aint1[k] = -1; 
            } 
        } 
    }
    
    public void setOffset(int p_setOffset_1_, int p_setOffset_2_, int p_setOffset_3_) {
        this.offsetX = p_setOffset_1_;
        this.offsetY = p_setOffset_2_;
        this.offsetZ = p_setOffset_3_;
        resetCache();
    }
    
    public int get(int p_get_1_, int p_get_2_, int p_get_3_) {
        try {
            this.lastZs = this.cache[p_get_1_ - this.offsetX][p_get_2_ - this.offsetY];
            this.lastDz = p_get_3_ - this.offsetZ;
            return this.lastZs[this.lastDz];
        } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
            arrayindexoutofboundsexception.printStackTrace();
            return -1;
        } 
    }
    
    public void setLast(int p_setLast_1_) {
        try {
            this.lastZs[this.lastDz] = p_setLast_1_;
        } catch (Exception exception) {
            exception.printStackTrace();
        } 
    }
}