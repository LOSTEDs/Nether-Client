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

package net.minecraft.block.properties;

import com.google.common.base.Objects;

public abstract class PropertyHelper<T extends Comparable<T>> implements IProperty<T> {
    private final Class<T> valueClass;
    
    private final String name;
    
    protected PropertyHelper(String name, Class<T> valueClass) {
        this.valueClass = valueClass;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Class<T> getValueClass() {
        return this.valueClass;
    }
    
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("clazz", this.valueClass).add("values", getAllowedValues()).toString();
    }
    
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_)
            return true; 
        if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
            PropertyHelper propertyhelper = (PropertyHelper)p_equals_1_;
            return (this.valueClass.equals(propertyhelper.valueClass) && this.name.equals(propertyhelper.name));
        } 
        return false;
    }
    
    public int hashCode() {
        return 31 * this.valueClass.hashCode() + this.name.hashCode();
    }
}
