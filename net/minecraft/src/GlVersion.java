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

public class GlVersion {
    private int major;
    
    private int minor;
    
    private int release;
    
    private String suffix;
    
    public GlVersion(int p_i50_1_, int p_i50_2_) {
        this(p_i50_1_, p_i50_2_, 0);
    }
    
    public GlVersion(int p_i51_1_, int p_i51_2_, int p_i51_3_) {
        this(p_i51_1_, p_i51_2_, p_i51_3_, null);
    }
    
    public GlVersion(int p_i52_1_, int p_i52_2_, int p_i52_3_, String p_i52_4_) {
        this.major = p_i52_1_;
        this.minor = p_i52_2_;
        this.release = p_i52_3_;
        this.suffix = p_i52_4_;
    }
    
    public int getMajor() {
        return this.major;
    }
    
    public int getMinor() {
        return this.minor;
    }
    
    public int getRelease() {
        return this.release;
    }
    
    public int toInt() {
        return (this.minor > 9) ? (this.major * 100 + this.minor) : ((this.release > 9) ? (this.major * 100 + this.minor * 10 + 9) : (this.major * 100 + this.minor * 10 + this.release));
    }
    
    public String toString() {
        return (this.suffix == null) ? (this.major + "." + this.minor + "." + this.release) : (this.major + "." + this.minor + "." + this.release + this.suffix);
    }
}
