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

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.src.IntegerCache;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class VisGraph {
    private static final int field_178616_a = (int)Math.pow(16.0D, 0.0D);
    
    private static final int field_178614_b = (int)Math.pow(16.0D, 1.0D);
    
    private static final int field_178615_c = (int)Math.pow(16.0D, 2.0D);
    
    private final BitSet field_178612_d = new BitSet(4096);
    
    private static final int[] field_178613_e = new int[1352];
    
    private int field_178611_f = 4096;
    
    private static final String __OBFID = "CL_00002450";
    
    public void func_178606_a(BlockPos pos) {
        this.field_178612_d.set(getIndex(pos), true);
        this.field_178611_f--;
    }
    
    private static int getIndex(BlockPos pos) {
        return getIndex(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF);
    }
    
    private static int getIndex(int x, int y, int z) {
        return x << 0 | y << 8 | z << 4;
    }
    
    public SetVisibility computeVisibility() {
        SetVisibility setvisibility = new SetVisibility();
        if (4096 - this.field_178611_f < 256) {
            setvisibility.setAllVisible(true);
        } else if (this.field_178611_f == 0) {
            setvisibility.setAllVisible(false);
        } else {
            byte b;
            int i;
            int[] arrayOfInt;
            for (i = (arrayOfInt = field_178613_e).length, b = 0; b < i; ) {
                int j = arrayOfInt[b];
                if (!this.field_178612_d.get(j))
                    setvisibility.setManyVisible(func_178604_a(j)); 
                b++;
            } 
        } 
        return setvisibility;
    }
    
    public Set func_178609_b(BlockPos pos) {
        return func_178604_a(getIndex(pos));
    }
    
    private Set func_178604_a(int p_178604_1_) {
        EnumSet<EnumFacing> enumset = EnumSet.noneOf(EnumFacing.class);
        ArrayDeque<Integer> arraydeque = new ArrayDeque(384);
        arraydeque.add(IntegerCache.valueOf(p_178604_1_));
        this.field_178612_d.set(p_178604_1_, true);
        while (!arraydeque.isEmpty()) {
            int i = ((Integer)arraydeque.poll()).intValue();
            func_178610_a(i, enumset);
            byte b;
            int j;
            EnumFacing[] arrayOfEnumFacing;
            for (j = (arrayOfEnumFacing = EnumFacing.VALUES).length, b = 0; b < j; ) {
                EnumFacing enumfacing = arrayOfEnumFacing[b];
                int k = func_178603_a(i, enumfacing);
                if (k >= 0 && !this.field_178612_d.get(k)) {
                    this.field_178612_d.set(k, true);
                    arraydeque.add(IntegerCache.valueOf(k));
                } 
                b++;
            } 
        } 
        return enumset;
    }
    
    private void func_178610_a(int p_178610_1_, Set<EnumFacing> p_178610_2_) {
        int i = p_178610_1_ >> 0 & 0xF;
        if (i == 0) {
            p_178610_2_.add(EnumFacing.WEST);
        } else if (i == 15) {
            p_178610_2_.add(EnumFacing.EAST);
        } 
        int j = p_178610_1_ >> 8 & 0xF;
        if (j == 0) {
            p_178610_2_.add(EnumFacing.DOWN);
        } else if (j == 15) {
            p_178610_2_.add(EnumFacing.UP);
        } 
        int k = p_178610_1_ >> 4 & 0xF;
        if (k == 0) {
            p_178610_2_.add(EnumFacing.NORTH);
        } else if (k == 15) {
            p_178610_2_.add(EnumFacing.SOUTH);
        } 
    }
    
    private int func_178603_a(int p_178603_1_, EnumFacing p_178603_2_) {
        switch (VisGraph$1.field_178617_a[p_178603_2_.ordinal()]) {
            case 1:
                if ((p_178603_1_ >> 8 & 0xF) == 0)
                    return -1; 
                return p_178603_1_ - field_178615_c;
            case 2:
                if ((p_178603_1_ >> 8 & 0xF) == 15)
                    return -1; 
                return p_178603_1_ + field_178615_c;
            case 3:
                if ((p_178603_1_ >> 4 & 0xF) == 0)
                    return -1; 
                return p_178603_1_ - field_178614_b;
            case 4:
                if ((p_178603_1_ >> 4 & 0xF) == 15)
                    return -1; 
                return p_178603_1_ + field_178614_b;
            case 5:
                if ((p_178603_1_ >> 0 & 0xF) == 0)
                    return -1; 
                return p_178603_1_ - field_178616_a;
            case 6:
                if ((p_178603_1_ >> 0 & 0xF) == 15)
                    return -1; 
                return p_178603_1_ + field_178616_a;
        } 
        return -1;
    }
    
    static {
        boolean flag = false;
        boolean flag1 = true;
        int i = 0;
        for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
                for (int l = 0; l < 16; l++) {
                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15)
                        field_178613_e[i++] = getIndex(j, k, l); 
                } 
            } 
        } 
    }
    
    static final class VisGraph$1 {
        static final int[] field_178617_a = new int[(EnumFacing.values()).length];
        
        private static final String __OBFID = "CL_00002449";
        
        static {
            try {
                field_178617_a[EnumFacing.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178617_a[EnumFacing.UP.ordinal()] = 2;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178617_a[EnumFacing.NORTH.ordinal()] = 3;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178617_a[EnumFacing.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178617_a[EnumFacing.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178617_a[EnumFacing.EAST.ordinal()] = 6;
            } catch (NoSuchFieldError noSuchFieldError) {}
        }
    }
}
