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

package net.minecraft.client.resources.data;

import net.minecraft.util.IChatComponent;

public class PackMetadataSection implements IMetadataSection {
    private final IChatComponent packDescription;
    
    private final int packFormat;
    
    public PackMetadataSection(IChatComponent p_i1034_1_, int p_i1034_2_) {
        this.packDescription = p_i1034_1_;
        this.packFormat = p_i1034_2_;
    }
    
    public IChatComponent getPackDescription() {
        return this.packDescription;
    }
    
    public int getPackFormat() {
        return this.packFormat;
    }
}
