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

public class FontMetadataSection implements IMetadataSection {
    private final float[] charWidths;
    
    private final float[] charLefts;
    
    private final float[] charSpacings;
    
    public FontMetadataSection(float[] p_i1310_1_, float[] p_i1310_2_, float[] p_i1310_3_) {
        this.charWidths = p_i1310_1_;
        this.charLefts = p_i1310_2_;
        this.charSpacings = p_i1310_3_;
    }
}
