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

import net.minecraft.src.Config;

public class PropertyDefaultFastFancyOff extends Property {
    public static final String[] PROPERTY_VALUES = new String[] { "default", "fast", "fancy", "off" };
    
    public static final String[] USER_VALUES = new String[] { "Default", "Fast", "Fancy", "OFF" };
    
    public PropertyDefaultFastFancyOff(String propertyName, String userName, int defaultValue) {
        super(propertyName, PROPERTY_VALUES, userName, USER_VALUES, defaultValue);
    }
    
    public boolean isDefault() {
        return (getValue() == 0);
    }
    
    public boolean isFast() {
        return (getValue() == 1);
    }
    
    public boolean isFancy() {
        return (getValue() == 2);
    }
    
    public boolean isOff() {
        return (getValue() == 3);
    }
    
    public boolean setPropertyValue(String propVal) {
        if (Config.equals(propVal, "none"))
            propVal = "off"; 
        return super.setPropertyValue(propVal);
    }
}
