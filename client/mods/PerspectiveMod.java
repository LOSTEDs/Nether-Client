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
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public class PerspectiveMod extends HudMod {
    public static float cameraYaw = 0.0F;
    
    public static float cameraPitch = 0.0F;
    
    public static int previousePrespective = 0;
    
    public static boolean perspectiveToggled = false;
    
    public static boolean returnOnRelease = true;
    
    public PerspectiveMod() {
        super("Perspective", 100000, 100000);
    }
    
    public int getWidth() {
        return 0;
    }
    
    public int getHeight() {
        return 0;
    }
    
    public static float getCameraYaw() {
        Minecraft mc = Minecraft.getMinecraft();
        return perspectiveToggled ? cameraYaw : Minecraft.thePlayer.rotationYaw;
    }
    
    public static float getCameraPitch() {
        Minecraft mc = Minecraft.getMinecraft();
        return perspectiveToggled ? cameraPitch : Minecraft.thePlayer.rotationPitch;
    }
    
    public static boolean overriderMouse() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.inGameHasFocus && Display.isActive()) {
            if (!perspectiveToggled)
                return true; 
            mc.mouseHelper.mouseXYChange();
            float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
            float f2 = f1 * f1 * f1 * 8.0F;
            float f3 = mc.mouseHelper.deltaX * f2;
            float f4 = mc.mouseHelper.deltaY * f2;
            cameraYaw += f3 * 0.15F;
            cameraPitch += f4 * 0.15F;
            if (cameraPitch > 90.0F)
                cameraPitch = 90.0F; 
            if (cameraPitch < -90.0F)
                cameraPitch = -90.0F; 
        } 
        return false;
    }
}
