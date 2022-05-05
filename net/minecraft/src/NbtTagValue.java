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

import java.util.Arrays;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue {
    private String[] parents = null;
    
    private String name = null;
    
    private boolean negative = false;
    
    private int type = 0;
    
    private String value = null;
    
    private int valueFormat = 0;
    
    private static final int TYPE_TEXT = 0;
    
    private static final int TYPE_PATTERN = 1;
    
    private static final int TYPE_IPATTERN = 2;
    
    private static final int TYPE_REGEX = 3;
    
    private static final int TYPE_IREGEX = 4;
    
    private static final String PREFIX_PATTERN = "pattern:";
    
    private static final String PREFIX_IPATTERN = "ipattern:";
    
    private static final String PREFIX_REGEX = "regex:";
    
    private static final String PREFIX_IREGEX = "iregex:";
    
    private static final int FORMAT_DEFAULT = 0;
    
    private static final int FORMAT_HEX_COLOR = 1;
    
    private static final String PREFIX_HEX_COLOR = "#";
    
    private static final Pattern PATTERN_HEX_COLOR = Pattern.compile("^#[0-9a-f]{6}+$");
    
    public NbtTagValue(String p_i78_1_, String p_i78_2_) {
        String[] astring = Config.tokenize(p_i78_1_, ".");
        this.parents = Arrays.<String>copyOfRange(astring, 0, astring.length - 1);
        this.name = astring[astring.length - 1];
        if (p_i78_2_.startsWith("!")) {
            this.negative = true;
            p_i78_2_ = p_i78_2_.substring(1);
        } 
        if (p_i78_2_.startsWith("pattern:")) {
            this.type = 1;
            p_i78_2_ = p_i78_2_.substring("pattern:".length());
        } else if (p_i78_2_.startsWith("ipattern:")) {
            this.type = 2;
            p_i78_2_ = p_i78_2_.substring("ipattern:".length()).toLowerCase();
        } else if (p_i78_2_.startsWith("regex:")) {
            this.type = 3;
            p_i78_2_ = p_i78_2_.substring("regex:".length());
        } else if (p_i78_2_.startsWith("iregex:")) {
            this.type = 4;
            p_i78_2_ = p_i78_2_.substring("iregex:".length()).toLowerCase();
        } else {
            this.type = 0;
        } 
        p_i78_2_ = StringEscapeUtils.unescapeJava(p_i78_2_);
        if (this.type == 0 && PATTERN_HEX_COLOR.matcher(p_i78_2_).matches())
            this.valueFormat = 1; 
        this.value = p_i78_2_;
    }
    
    public boolean matches(NBTTagCompound p_matches_1_) {
        return this.negative ? (!matchesCompound(p_matches_1_)) : matchesCompound(p_matches_1_);
    }
    
    public boolean matchesCompound(NBTTagCompound p_matchesCompound_1_) {
        if (p_matchesCompound_1_ == null)
            return false; 
        NBTTagCompound nBTTagCompound = p_matchesCompound_1_;
        for (int i = 0; i < this.parents.length; i++) {
            String s = this.parents[i];
            nBTBase = getChildTag((NBTBase)nBTTagCompound, s);
            if (nBTBase == null)
                return false; 
        } 
        if (this.name.equals("*"))
            return matchesAnyChild(nBTBase); 
        NBTBase nBTBase = getChildTag(nBTBase, this.name);
        if (nBTBase == null)
            return false; 
        if (matchesBase(nBTBase))
            return true; 
        return false;
    }
    
    private boolean matchesAnyChild(NBTBase p_matchesAnyChild_1_) {
        if (p_matchesAnyChild_1_ instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound)p_matchesAnyChild_1_;
            for (String s : nbttagcompound.getKeySet()) {
                NBTBase nbtbase = nbttagcompound.getTag(s);
                if (matchesBase(nbtbase))
                    return true; 
            } 
        } 
        if (p_matchesAnyChild_1_ instanceof NBTTagList) {
            NBTTagList nbttaglist = (NBTTagList)p_matchesAnyChild_1_;
            int i = nbttaglist.tagCount();
            for (int j = 0; j < i; j++) {
                NBTBase nbtbase1 = nbttaglist.get(j);
                if (matchesBase(nbtbase1))
                    return true; 
            } 
        } 
        return false;
    }
    
    private static NBTBase getChildTag(NBTBase p_getChildTag_0_, String p_getChildTag_1_) {
        if (p_getChildTag_0_ instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound)p_getChildTag_0_;
            return nbttagcompound.getTag(p_getChildTag_1_);
        } 
        if (p_getChildTag_0_ instanceof NBTTagList) {
            NBTTagList nbttaglist = (NBTTagList)p_getChildTag_0_;
            if (p_getChildTag_1_.equals("count"))
                return (NBTBase)new NBTTagInt(nbttaglist.tagCount()); 
            int i = Config.parseInt(p_getChildTag_1_, -1);
            return (i < 0) ? null : nbttaglist.get(i);
        } 
        return null;
    }
    
    public boolean matchesBase(NBTBase p_matchesBase_1_) {
        if (p_matchesBase_1_ == null)
            return false; 
        String s = getNbtString(p_matchesBase_1_, this.valueFormat);
        return matchesValue(s);
    }
    
    public boolean matchesValue(String p_matchesValue_1_) {
        if (p_matchesValue_1_ == null)
            return false; 
        switch (this.type) {
            case 0:
                return p_matchesValue_1_.equals(this.value);
            case 1:
                return matchesPattern(p_matchesValue_1_, this.value);
            case 2:
                return matchesPattern(p_matchesValue_1_.toLowerCase(), this.value);
            case 3:
                return matchesRegex(p_matchesValue_1_, this.value);
            case 4:
                return matchesRegex(p_matchesValue_1_.toLowerCase(), this.value);
        } 
        throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
    }
    
    private boolean matchesPattern(String p_matchesPattern_1_, String p_matchesPattern_2_) {
        return StrUtils.equalsMask(p_matchesPattern_1_, p_matchesPattern_2_, '*', '?');
    }
    
    private boolean matchesRegex(String p_matchesRegex_1_, String p_matchesRegex_2_) {
        return p_matchesRegex_1_.matches(p_matchesRegex_2_);
    }
    
    private static String getNbtString(NBTBase p_getNbtString_0_, int p_getNbtString_1_) {
        if (p_getNbtString_0_ == null)
            return null; 
        if (p_getNbtString_0_ instanceof NBTTagString) {
            NBTTagString nbttagstring = (NBTTagString)p_getNbtString_0_;
            return nbttagstring.getString();
        } 
        if (p_getNbtString_0_ instanceof NBTTagInt) {
            NBTTagInt nbttagint = (NBTTagInt)p_getNbtString_0_;
            return (p_getNbtString_1_ == 1) ? ("#" + StrUtils.fillLeft(Integer.toHexString(nbttagint.getInt()), 6, '0')) : Integer.toString(nbttagint.getInt());
        } 
        if (p_getNbtString_0_ instanceof NBTTagByte) {
            NBTTagByte nbttagbyte = (NBTTagByte)p_getNbtString_0_;
            return Byte.toString(nbttagbyte.getByte());
        } 
        if (p_getNbtString_0_ instanceof NBTTagShort) {
            NBTTagShort nbttagshort = (NBTTagShort)p_getNbtString_0_;
            return Short.toString(nbttagshort.getShort());
        } 
        if (p_getNbtString_0_ instanceof NBTTagLong) {
            NBTTagLong nbttaglong = (NBTTagLong)p_getNbtString_0_;
            return Long.toString(nbttaglong.getLong());
        } 
        if (p_getNbtString_0_ instanceof NBTTagFloat) {
            NBTTagFloat nbttagfloat = (NBTTagFloat)p_getNbtString_0_;
            return Float.toString(nbttagfloat.getFloat());
        } 
        if (p_getNbtString_0_ instanceof NBTTagDouble) {
            NBTTagDouble nbttagdouble = (NBTTagDouble)p_getNbtString_0_;
            return Double.toString(nbttagdouble.getDouble());
        } 
        return p_getNbtString_0_.toString();
    }
    
    public String toString() {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < this.parents.length; i++) {
            String s = this.parents[i];
            if (i > 0)
                stringbuffer.append("."); 
            stringbuffer.append(s);
        } 
        if (stringbuffer.length() > 0)
            stringbuffer.append("."); 
        stringbuffer.append(this.name);
        stringbuffer.append(" = ");
        stringbuffer.append(this.value);
        return stringbuffer.toString();
    }
}
