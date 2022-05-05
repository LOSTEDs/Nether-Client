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

public class AnimationFrame {
    private final int frameIndex;
    
    private final int frameTime;
    
    public AnimationFrame(int p_i1307_1_) {
        this(p_i1307_1_, -1);
    }
    
    public AnimationFrame(int p_i1308_1_, int p_i1308_2_) {
        this.frameIndex = p_i1308_1_;
        this.frameTime = p_i1308_2_;
    }
    
    public boolean hasNoTime() {
        return (this.frameTime == -1);
    }
    
    public int getFrameTime() {
        return this.frameTime;
    }
    
    public int getFrameIndex() {
        return this.frameIndex;
    }
}
