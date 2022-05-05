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

package shadersmod.client;

import net.minecraft.src.Lang;

public class PropertyDefaultTrueFalse extends Property {
    public static final String[] PROPERTY_VALUES = new String[] { "default", "true", "false" };
    
    public static final String[] USER_VALUES = new String[] { "Default", "ON", "OFF" };
    
    public PropertyDefaultTrueFalse(String propertyName, String userName, int defaultValue) {
        super(propertyName, PROPERTY_VALUES, userName, USER_VALUES, defaultValue);
    }
    
    public String getUserValue() {
        return isDefault() ? Lang.getDefault() : (isTrue() ? Lang.getOn() : (isFalse() ? Lang.getOff() : super.getUserValue()));
    }
    
    public boolean isDefault() {
        return (getValue() == 0);
    }
    
    public boolean isTrue() {
        return (getValue() == 1);
    }
    
    public boolean isFalse() {
        return (getValue() == 2);
    }
}
