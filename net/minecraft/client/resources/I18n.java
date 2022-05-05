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

import java.util.Map;

public class I18n {
    private static Locale i18nLocale;
    
    private static final String __OBFID = "CL_00001094";
    
    static void setLocale(Locale i18nLocaleIn) {
        i18nLocale = i18nLocaleIn;
    }
    
    public static String format(String translateKey, Object... parameters) {
        return i18nLocale.formatMessage(translateKey, parameters);
    }
    
    public static Map getLocaleProperties() {
        return i18nLocale.properties;
    }
}
