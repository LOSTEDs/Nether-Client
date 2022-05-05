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

package net.minecraft.client.resources;

import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class DefaultPlayerSkin {
    private static final ResourceLocation TEXTURE_STEVE = new ResourceLocation("textures/entity/steve.png");
    
    private static final ResourceLocation TEXTURE_ALEX = new ResourceLocation("textures/entity/alex.png");
    
    public static ResourceLocation getDefaultSkinLegacy() {
        return TEXTURE_STEVE;
    }
    
    public static ResourceLocation getDefaultSkin(UUID playerUUID) {
        return isSlimSkin(playerUUID) ? TEXTURE_ALEX : TEXTURE_STEVE;
    }
    
    public static String getSkinType(UUID playerUUID) {
        return isSlimSkin(playerUUID) ? "slim" : "default";
    }
    
    private static boolean isSlimSkin(UUID playerUUID) {
        return ((playerUUID.hashCode() & 0x1) == 1);
    }
}
