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

package net.minecraft.potion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.src.Config;
import net.minecraft.src.CustomColors;
import net.minecraft.util.IntegerCache;

public class PotionHelper {
    public static final String field_77924_a = null;
    
    public static final String sugarEffect = "-0+1-2-3&4-4+13";
    
    public static final String ghastTearEffect = "+0-1-2-3&4-4+13";
    
    public static final String spiderEyeEffect = "-0-1+2-3&4-4+13";
    
    public static final String fermentedSpiderEyeEffect = "-0+3-4+13";
    
    public static final String speckledMelonEffect = "+0-1+2-3&4-4+13";
    
    public static final String blazePowderEffect = "+0-1-2+3&4-4+13";
    
    public static final String magmaCreamEffect = "+0+1-2-3&4-4+13";
    
    public static final String redstoneEffect = "-5+6-7";
    
    public static final String glowstoneEffect = "+5-6-7";
    
    public static final String gunpowderEffect = "+14&13-13";
    
    public static final String goldenCarrotEffect = "-0+1+2-3+13&4-4";
    
    public static final String pufferfishEffect = "+0-1+2+3+13&4-4";
    
    public static final String rabbitFootEffect = "+0+1-2+3&4-4+13";
    
    private static final Map potionRequirements = Maps.newHashMap();
    
    private static final Map potionAmplifiers = Maps.newHashMap();
    
    private static final Map DATAVALUE_COLORS = Maps.newHashMap();
    
    private static final String[] potionPrefixes = new String[] { 
            "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", 
            "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", 
            "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", 
            "potion.prefix.gross", "potion.prefix.stinky" };
    
    private static final String __OBFID = "CL_00000078";
    
    public static boolean checkFlag(int p_77914_0_, int p_77914_1_) {
        return ((p_77914_0_ & 1 << p_77914_1_) != 0);
    }
    
    private static int isFlagSet(int p_77910_0_, int p_77910_1_) {
        return checkFlag(p_77910_0_, p_77910_1_) ? 1 : 0;
    }
    
    private static int isFlagUnset(int p_77916_0_, int p_77916_1_) {
        return checkFlag(p_77916_0_, p_77916_1_) ? 0 : 1;
    }
    
    public static int getPotionPrefixIndex(int dataValue) {
        return func_77908_a(dataValue, 5, 4, 3, 2, 1);
    }
    
    public static int calcPotionLiquidColor(Collection<PotionEffect> p_77911_0_) {
        int i = 3694022;
        if (p_77911_0_ != null && !p_77911_0_.isEmpty()) {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;
            for (PotionEffect potioneffect : p_77911_0_) {
                if (potioneffect.getIsShowParticles()) {
                    int j = Potion.potionTypes[potioneffect.getPotionID()].getLiquidColor();
                    if (Config.isCustomColors())
                        j = CustomColors.getPotionColor(potioneffect.getPotionID(), j); 
                    for (int k = 0; k <= potioneffect.getAmplifier(); k++) {
                        f += (j >> 16 & 0xFF) / 255.0F;
                        f1 += (j >> 8 & 0xFF) / 255.0F;
                        f2 += (j >> 0 & 0xFF) / 255.0F;
                        f3++;
                    } 
                } 
            } 
            if (f3 == 0.0F)
                return 0; 
            f = f / f3 * 255.0F;
            f1 = f1 / f3 * 255.0F;
            f2 = f2 / f3 * 255.0F;
            return (int)f << 16 | (int)f1 << 8 | (int)f2;
        } 
        if (Config.isCustomColors())
            i = CustomColors.getPotionColor(0, i); 
        return i;
    }
    
    public static boolean getAreAmbient(Collection<PotionEffect> potionEffects) {
        for (PotionEffect potioneffect : potionEffects) {
            if (!potioneffect.getIsAmbient())
                return false; 
        } 
        return true;
    }
    
    public static int getLiquidColor(int dataValue, boolean bypassCache) {
        Integer integer = IntegerCache.func_181756_a(dataValue);
        if (!bypassCache) {
            if (DATAVALUE_COLORS.containsKey(integer))
                return ((Integer)DATAVALUE_COLORS.get(integer)).intValue(); 
            int i = calcPotionLiquidColor(getPotionEffects(integer.intValue(), false));
            DATAVALUE_COLORS.put(integer, Integer.valueOf(i));
            return i;
        } 
        return calcPotionLiquidColor(getPotionEffects(integer.intValue(), true));
    }
    
    public static String getPotionPrefix(int dataValue) {
        int i = getPotionPrefixIndex(dataValue);
        return potionPrefixes[i];
    }
    
    private static int func_77904_a(boolean p_77904_0_, boolean p_77904_1_, boolean p_77904_2_, int p_77904_3_, int p_77904_4_, int p_77904_5_, int p_77904_6_) {
        int i = 0;
        if (p_77904_0_) {
            i = isFlagUnset(p_77904_6_, p_77904_4_);
        } else if (p_77904_3_ != -1) {
            if (p_77904_3_ == 0 && countSetFlags(p_77904_6_) == p_77904_4_) {
                i = 1;
            } else if (p_77904_3_ == 1 && countSetFlags(p_77904_6_) > p_77904_4_) {
                i = 1;
            } else if (p_77904_3_ == 2 && countSetFlags(p_77904_6_) < p_77904_4_) {
                i = 1;
            } 
        } else {
            i = isFlagSet(p_77904_6_, p_77904_4_);
        } 
        if (p_77904_1_)
            i *= p_77904_5_; 
        if (p_77904_2_)
            i *= -1; 
        return i;
    }
    
    private static int countSetFlags(int p_77907_0_) {
        for (int i = 0; p_77907_0_ > 0; i++)
            p_77907_0_ &= p_77907_0_ - 1; 
        return i;
    }
    
    private static int parsePotionEffects(String p_77912_0_, int p_77912_1_, int p_77912_2_, int p_77912_3_) {
        if (p_77912_1_ < p_77912_0_.length() && p_77912_2_ >= 0 && p_77912_1_ < p_77912_2_) {
            int i = p_77912_0_.indexOf('|', p_77912_1_);
            if (i >= 0 && i < p_77912_2_) {
                int l1 = parsePotionEffects(p_77912_0_, p_77912_1_, i - 1, p_77912_3_);
                if (l1 > 0)
                    return l1; 
                int i2 = parsePotionEffects(p_77912_0_, i + 1, p_77912_2_, p_77912_3_);
                return (i2 > 0) ? i2 : 0;
            } 
            int j = p_77912_0_.indexOf('&', p_77912_1_);
            if (j >= 0 && j < p_77912_2_) {
                int k = parsePotionEffects(p_77912_0_, p_77912_1_, j - 1, p_77912_3_);
                if (k <= 0)
                    return 0; 
                int j2 = parsePotionEffects(p_77912_0_, j + 1, p_77912_2_, p_77912_3_);
                return (j2 <= 0) ? 0 : ((k > j2) ? k : j2);
            } 
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            byte b0 = -1;
            int l = 0;
            int i1 = 0;
            int j1 = 0;
            for (int k1 = p_77912_1_; k1 < p_77912_2_; k1++) {
                char c0 = p_77912_0_.charAt(k1);
                if (c0 >= '0' && c0 <= '9') {
                    if (flag) {
                        i1 = c0 - 48;
                        flag1 = true;
                    } else {
                        l *= 10;
                        l += c0 - 48;
                        flag2 = true;
                    } 
                } else if (c0 == '*') {
                    flag = true;
                } else if (c0 == '!') {
                    if (flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, b0, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        b0 = -1;
                    } 
                    flag3 = true;
                } else if (c0 == '-') {
                    if (flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, b0, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        b0 = -1;
                    } 
                    flag4 = true;
                } else if (c0 != '=' && c0 != '<' && c0 != '>') {
                    if (c0 == '+' && flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, b0, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        b0 = -1;
                    } 
                } else {
                    if (flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, b0, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        b0 = -1;
                    } 
                    if (c0 == '=') {
                        b0 = 0;
                    } else if (c0 == '<') {
                        b0 = 2;
                    } else if (c0 == '>') {
                        b0 = 1;
                    } 
                } 
            } 
            if (flag2)
                j1 += func_77904_a(flag3, flag1, flag4, b0, l, i1, p_77912_3_); 
            return j1;
        } 
        return 0;
    }
    
    public static List getPotionEffects(int p_77917_0_, boolean p_77917_1_) {
        ArrayList<PotionEffect> arraylist = null;
        byte b;
        int i;
        Potion[] arrayOfPotion;
        for (i = (arrayOfPotion = Potion.potionTypes).length, b = 0; b < i; ) {
            Potion potion = arrayOfPotion[b];
            if (potion != null && (!potion.isUsable() || p_77917_1_)) {
                String s = (String)potionRequirements.get(Integer.valueOf(potion.getId()));
                if (s != null) {
                    int j = parsePotionEffects(s, 0, s.length(), p_77917_0_);
                    if (j > 0) {
                        int k = 0;
                        String s1 = (String)potionAmplifiers.get(Integer.valueOf(potion.getId()));
                        if (s1 != null) {
                            k = parsePotionEffects(s1, 0, s1.length(), p_77917_0_);
                            if (k < 0)
                                k = 0; 
                        } 
                        if (potion.isInstant()) {
                            j = 1;
                        } else {
                            j = 1200 * (j * 3 + (j - 1) * 2);
                            j >>= k;
                            j = (int)Math.round(j * potion.getEffectiveness());
                            if ((p_77917_0_ & 0x4000) != 0)
                                j = (int)Math.round(j * 0.75D + 0.5D); 
                        } 
                        if (arraylist == null)
                            arraylist = Lists.newArrayList(); 
                        PotionEffect potioneffect = new PotionEffect(potion.getId(), j, k);
                        if ((p_77917_0_ & 0x4000) != 0)
                            potioneffect.setSplashPotion(true); 
                        arraylist.add(potioneffect);
                    } 
                } 
            } 
            b++;
        } 
        return arraylist;
    }
    
    private static int brewBitOperations(int p_77906_0_, int p_77906_1_, boolean p_77906_2_, boolean p_77906_3_, boolean p_77906_4_) {
        if (p_77906_4_) {
            if (!checkFlag(p_77906_0_, p_77906_1_))
                return 0; 
        } else if (p_77906_2_) {
            p_77906_0_ &= 1 << p_77906_1_ ^ 0xFFFFFFFF;
        } else if (p_77906_3_) {
            if ((p_77906_0_ & 1 << p_77906_1_) == 0) {
                p_77906_0_ |= 1 << p_77906_1_;
            } else {
                p_77906_0_ &= 1 << p_77906_1_ ^ 0xFFFFFFFF;
            } 
        } else {
            p_77906_0_ |= 1 << p_77906_1_;
        } 
        return p_77906_0_;
    }
    
    public static int applyIngredient(int p_77913_0_, String p_77913_1_) {
        byte b0 = 0;
        int i = p_77913_1_.length();
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int j = 0;
        for (int k = b0; k < i; k++) {
            char c0 = p_77913_1_.charAt(k);
            if (c0 >= '0' && c0 <= '9') {
                j *= 10;
                j += c0 - 48;
                flag = true;
            } else if (c0 == '!') {
                if (flag) {
                    p_77913_0_ = brewBitOperations(p_77913_0_, j, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    j = 0;
                } 
                flag1 = true;
            } else if (c0 == '-') {
                if (flag) {
                    p_77913_0_ = brewBitOperations(p_77913_0_, j, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    j = 0;
                } 
                flag2 = true;
            } else if (c0 == '+') {
                if (flag) {
                    p_77913_0_ = brewBitOperations(p_77913_0_, j, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    j = 0;
                } 
            } else if (c0 == '&') {
                if (flag) {
                    p_77913_0_ = brewBitOperations(p_77913_0_, j, flag2, flag1, flag3);
                    flag3 = false;
                    flag1 = false;
                    flag2 = false;
                    flag = false;
                    j = 0;
                } 
                flag3 = true;
            } 
        } 
        if (flag)
            p_77913_0_ = brewBitOperations(p_77913_0_, j, flag2, flag1, flag3); 
        return p_77913_0_ & 0x7FFF;
    }
    
    public static int func_77908_a(int p_77908_0_, int p_77908_1_, int p_77908_2_, int p_77908_3_, int p_77908_4_, int p_77908_5_) {
        return (checkFlag(p_77908_0_, p_77908_1_) ? 16 : 0) | (checkFlag(p_77908_0_, p_77908_2_) ? 8 : 0) | (checkFlag(p_77908_0_, p_77908_3_) ? 4 : 0) | (checkFlag(p_77908_0_, p_77908_4_) ? 2 : 0) | (checkFlag(p_77908_0_, p_77908_5_) ? 1 : 0);
    }
    
    public static void clearPotionColorCache() {
        DATAVALUE_COLORS.clear();
    }
    
    static {
        potionRequirements.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
        potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3");
        potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3");
        potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & 3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.waterBreathing.getId()), "0 & !1 & 2 & 3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & 3 & 3+6");
        potionAmplifiers.put(Integer.valueOf(Potion.moveSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.damageBoost.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.regeneration.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.harm.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.heal.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.resistance.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.poison.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.jump.getId()), "5");
    }
}
