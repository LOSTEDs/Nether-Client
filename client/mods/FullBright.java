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

package client.mods;

import client.hud.impl.HudMod;

public class FullBright extends HudMod {
    public FullBright() {
        super("Full Bright", 100000, 100000);
    }
    
    public int getWidth() {
        return 0;
    }
    
    public int getHeight() {
        return 0;
    }
    
    public void onEnable() {
        this.mc.gameSettings.gammaSetting = 100.0F;
    }
    
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = 1.0F;
    }
}
