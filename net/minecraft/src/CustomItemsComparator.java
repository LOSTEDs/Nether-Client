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

package net.minecraft.src;

import java.util.Comparator;

public class CustomItemsComparator implements Comparator {
    public int compare(Object p_compare_1_, Object p_compare_2_) {
        CustomItemProperties customitemproperties = (CustomItemProperties)p_compare_1_;
        CustomItemProperties customitemproperties1 = (CustomItemProperties)p_compare_2_;
        return (customitemproperties.weight != customitemproperties1.weight) ? (customitemproperties1.weight - customitemproperties.weight) : (!Config.equals(customitemproperties.basePath, customitemproperties1.basePath) ? customitemproperties.basePath.compareTo(customitemproperties1.basePath) : customitemproperties.name.compareTo(customitemproperties1.name));
    }
}
