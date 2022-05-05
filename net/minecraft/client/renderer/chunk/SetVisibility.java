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

package net.minecraft.client.renderer.chunk;

import java.util.BitSet;
import java.util.Set;
import net.minecraft.util.EnumFacing;

public class SetVisibility {
    private static final int COUNT_FACES = (EnumFacing.values()).length;
    
    private final BitSet bitSet = new BitSet(COUNT_FACES * COUNT_FACES);
    
    public void setManyVisible(Set<EnumFacing> p_178620_1_) {
        for (EnumFacing enumfacing : p_178620_1_) {
            for (EnumFacing enumfacing1 : p_178620_1_)
                setVisible(enumfacing, enumfacing1, true); 
        } 
    }
    
    public void setVisible(EnumFacing facing, EnumFacing facing2, boolean p_178619_3_) {
        this.bitSet.set(facing.ordinal() + facing2.ordinal() * COUNT_FACES, p_178619_3_);
        this.bitSet.set(facing2.ordinal() + facing.ordinal() * COUNT_FACES, p_178619_3_);
    }
    
    public void setAllVisible(boolean visible) {
        this.bitSet.set(0, this.bitSet.size(), visible);
    }
    
    public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
        return this.bitSet.get(facing.ordinal() + facing2.ordinal() * COUNT_FACES);
    }
    
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(' ');
        byte b;
        int i;
        EnumFacing[] arrayOfEnumFacing;
        for (i = (arrayOfEnumFacing = EnumFacing.values()).length, b = 0; b < i; ) {
            EnumFacing enumfacing = arrayOfEnumFacing[b];
            stringbuilder.append(' ').append(enumfacing.toString().toUpperCase().charAt(0));
            b++;
        } 
        stringbuilder.append('\n');
        for (i = (arrayOfEnumFacing = EnumFacing.values()).length, b = 0; b < i; ) {
            EnumFacing enumfacing2 = arrayOfEnumFacing[b];
            stringbuilder.append(enumfacing2.toString().toUpperCase().charAt(0));
            byte b1;
            int j;
            EnumFacing[] arrayOfEnumFacing1;
            for (j = (arrayOfEnumFacing1 = EnumFacing.values()).length, b1 = 0; b1 < j; ) {
                EnumFacing enumfacing1 = arrayOfEnumFacing1[b1];
                if (enumfacing2 == enumfacing1) {
                    stringbuilder.append("  ");
                } else {
                    boolean flag = isVisible(enumfacing2, enumfacing1);
                    stringbuilder.append(' ').append(flag ? 89 : 110);
                } 
                b1++;
            } 
            stringbuilder.append('\n');
            b++;
        } 
        return stringbuilder.toString();
    }
}
