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

package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public abstract class PositionedSound implements ISound {
    protected final ResourceLocation positionedSoundLocation;
    
    protected float volume = 1.0F;
    
    protected float pitch = 1.0F;
    
    protected float xPosF;
    
    protected float yPosF;
    
    protected float zPosF;
    
    protected boolean repeat = false;
    
    protected int repeatDelay = 0;
    
    protected ISound.AttenuationType attenuationType = ISound.AttenuationType.LINEAR;
    
    protected PositionedSound(ResourceLocation soundResource) {
        this.positionedSoundLocation = soundResource;
    }
    
    public ResourceLocation getSoundLocation() {
        return this.positionedSoundLocation;
    }
    
    public boolean canRepeat() {
        return this.repeat;
    }
    
    public int getRepeatDelay() {
        return this.repeatDelay;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getXPosF() {
        return this.xPosF;
    }
    
    public float getYPosF() {
        return this.yPosF;
    }
    
    public float getZPosF() {
        return this.zPosF;
    }
    
    public ISound.AttenuationType getAttenuationType() {
        return this.attenuationType;
    }
}
