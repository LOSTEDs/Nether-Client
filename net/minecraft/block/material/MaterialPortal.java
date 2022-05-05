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

package net.minecraft.block.material;

public class MaterialPortal extends Material {
    public MaterialPortal(MapColor color) {
        super(color);
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public boolean blocksLight() {
        return false;
    }
    
    public boolean blocksMovement() {
        return false;
    }
}
