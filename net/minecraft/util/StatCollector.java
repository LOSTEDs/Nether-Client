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

public class StatCollector {
    private static StringTranslate localizedName = StringTranslate.getInstance();
    
    private static StringTranslate fallbackTranslator = new StringTranslate();
    
    public static String translateToLocal(String key) {
        return localizedName.translateKey(key);
    }
    
    public static String translateToLocalFormatted(String key, Object... format) {
        return localizedName.translateKeyFormat(key, format);
    }
    
    public static String translateToFallback(String key) {
        return fallbackTranslator.translateKey(key);
    }
    
    public static boolean canTranslate(String key) {
        return localizedName.isKeyTranslated(key);
    }
    
    public static long getLastTranslationUpdateTimeInMilliseconds() {
        return localizedName.getLastUpdateTimeInMilliseconds();
    }
}
