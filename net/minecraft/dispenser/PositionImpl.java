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

package net.minecraft.dispenser;

public class PositionImpl implements IPosition {
    protected final double x;
    
    protected final double y;
    
    protected final double z;
    
    public PositionImpl(double xCoord, double yCoord, double zCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
}
