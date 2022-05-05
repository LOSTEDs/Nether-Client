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

package net.minecraft.entity.player;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public enum EnumPlayerModelParts {
    CAPE(0, "cape"),
    JACKET(1, "jacket"),
    LEFT_SLEEVE(2, "left_sleeve"),
    RIGHT_SLEEVE(3, "right_sleeve"),
    LEFT_PANTS_LEG(4, "left_pants_leg"),
    RIGHT_PANTS_LEG(5, "right_pants_leg"),
    HAT(6, "hat");
    
    private final int partId;
    
    private final int partMask;
    
    private final String partName;
    
    private final IChatComponent field_179339_k;
    
    EnumPlayerModelParts(int partIdIn, String partNameIn) {
        this.partId = partIdIn;
        this.partMask = 1 << partIdIn;
        this.partName = partNameIn;
        this.field_179339_k = (IChatComponent)new ChatComponentTranslation("options.modelPart." + partNameIn, new Object[0]);
    }
    
    public int getPartMask() {
        return this.partMask;
    }
    
    public int getPartId() {
        return this.partId;
    }
    
    public String getPartName() {
        return this.partName;
    }
    
    public IChatComponent func_179326_d() {
        return this.field_179339_k;
    }
}
