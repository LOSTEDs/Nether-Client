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

package net.minecraft.util;

import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions extends MovementInput {
    private final GameSettings gameSettings;
    
    public MovementInputFromOptions(GameSettings gameSettingsIn) {
        this.gameSettings = gameSettingsIn;
    }
    
    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;
        if (this.gameSettings.keyBindForward.isKeyDown())
            this.moveForward++; 
        if (this.gameSettings.keyBindBack.isKeyDown())
            this.moveForward--; 
        if (this.gameSettings.keyBindLeft.isKeyDown())
            this.moveStrafe++; 
        if (this.gameSettings.keyBindRight.isKeyDown())
            this.moveStrafe--; 
        this.jump = this.gameSettings.keyBindJump.isKeyDown();
        this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
        if (this.sneak) {
            this.moveStrafe = (float)(this.moveStrafe * 0.3D);
            this.moveForward = (float)(this.moveForward * 0.3D);
        } 
    }
}
