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

package net.minecraft.world.border;

public interface IBorderListener {
    void onSizeChanged(WorldBorder paramWorldBorder, double paramDouble);
    
    void onTransitionStarted(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2, long paramLong);
    
    void onCenterChanged(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2);
    
    void onWarningTimeChanged(WorldBorder paramWorldBorder, int paramInt);
    
    void onWarningDistanceChanged(WorldBorder paramWorldBorder, int paramInt);
    
    void onDamageAmountChanged(WorldBorder paramWorldBorder, double paramDouble);
    
    void onDamageBufferChanged(WorldBorder paramWorldBorder, double paramDouble);
}
