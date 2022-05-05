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

package client.cosmetics.util;

import net.minecraft.util.ResourceLocation;

public class AnimatedResourceLocation {
    private String location;
    
    protected int frames;
    
    protected int fpt;
    
    protected int currentTick;
    
    protected int currentFrame;
    
    protected ResourceLocation[] textures;
    
    public AnimatedResourceLocation(String location, int frames, int fpt) {
        this.currentTick = 0;
        this.currentFrame = 0;
        this.location = location;
        this.frames = frames;
        this.fpt = fpt;
        this.textures = new ResourceLocation[frames];
        for (int i = 0; i < frames; i++)
            this.textures[i] = new ResourceLocation(String.valueOf(String.valueOf(location)) + "/" + i + ".png"); 
    }
    
    public void update() {
        if (this.currentTick > this.fpt) {
            this.currentTick = 0;
            this.currentFrame++;
            if (this.currentFrame > this.frames - 1)
                this.currentFrame = 0; 
        } 
        this.currentTick++;
    }
    
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
    
    public int getFrames() {
        return this.frames;
    }
    
    public ResourceLocation getTexture() {
        return this.textures[this.currentFrame];
    }
    
    public int getCurrentFrame() {
        return this.currentFrame;
    }
}
