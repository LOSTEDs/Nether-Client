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

import net.minecraft.util.EnumFacing;

public class QuadBounds {
    private float minX = Float.MAX_VALUE;
    
    private float minY = Float.MAX_VALUE;
    
    private float minZ = Float.MAX_VALUE;
    
    private float maxX = -3.4028235E38F;
    
    private float maxY = -3.4028235E38F;
    
    private float maxZ = -3.4028235E38F;
    
    public QuadBounds(int[] p_i86_1_) {
        int i = p_i86_1_.length / 4;
        for (int j = 0; j < 4; j++) {
            int k = j * i;
            float f = Float.intBitsToFloat(p_i86_1_[k + 0]);
            float f1 = Float.intBitsToFloat(p_i86_1_[k + 1]);
            float f2 = Float.intBitsToFloat(p_i86_1_[k + 2]);
            if (this.minX > f)
                this.minX = f; 
            if (this.minY > f1)
                this.minY = f1; 
            if (this.minZ > f2)
                this.minZ = f2; 
            if (this.maxX < f)
                this.maxX = f; 
            if (this.maxY < f1)
                this.maxY = f1; 
            if (this.maxZ < f2)
                this.maxZ = f2; 
        } 
    }
    
    public float getMinX() {
        return this.minX;
    }
    
    public float getMinY() {
        return this.minY;
    }
    
    public float getMinZ() {
        return this.minZ;
    }
    
    public float getMaxX() {
        return this.maxX;
    }
    
    public float getMaxY() {
        return this.maxY;
    }
    
    public float getMaxZ() {
        return this.maxZ;
    }
    
    public boolean isFaceQuad(EnumFacing p_isFaceQuad_1_) {
        float f;
        float f1;
        float f2;
        switch (p_isFaceQuad_1_) {
            case null:
                f = getMinY();
                f1 = getMaxY();
                f2 = 0.0F;
                break;
            case UP:
                f = getMinY();
                f1 = getMaxY();
                f2 = 1.0F;
                break;
            case NORTH:
                f = getMinZ();
                f1 = getMaxZ();
                f2 = 0.0F;
                break;
            case SOUTH:
                f = getMinZ();
                f1 = getMaxZ();
                f2 = 1.0F;
                break;
            case WEST:
                f = getMinX();
                f1 = getMaxX();
                f2 = 0.0F;
                break;
            case EAST:
                f = getMinX();
                f1 = getMaxX();
                f2 = 1.0F;
                break;
            default:
                return false;
        } 
        return (f == f2 && f1 == f2);
    }
    
    public boolean isFullQuad(EnumFacing p_isFullQuad_1_) {
        float f;
        float f1;
        float f2;
        float f3;
        switch (p_isFullQuad_1_) {
            case null:
            case UP:
                f = getMinX();
                f1 = getMaxX();
                f2 = getMinZ();
                f3 = getMaxZ();
                break;
            case NORTH:
            case SOUTH:
                f = getMinX();
                f1 = getMaxX();
                f2 = getMinY();
                f3 = getMaxY();
                break;
            case WEST:
            case EAST:
                f = getMinY();
                f1 = getMaxY();
                f2 = getMinZ();
                f3 = getMaxZ();
                break;
            default:
                return false;
        } 
        return (f == 0.0F && f1 == 1.0F && f2 == 0.0F && f3 == 1.0F);
    }
}
