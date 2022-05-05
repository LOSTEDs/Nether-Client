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

public class TupleIntJsonSerializable {
    private int integerValue;
    
    private IJsonSerializable jsonSerializableValue;
    
    public int getIntegerValue() {
        return this.integerValue;
    }
    
    public void setIntegerValue(int integerValueIn) {
        this.integerValue = integerValueIn;
    }
    
    public <T extends IJsonSerializable> T getJsonSerializableValue() {
        return (T)this.jsonSerializableValue;
    }
    
    public void setJsonSerializableValue(IJsonSerializable jsonSerializableValueIn) {
        this.jsonSerializableValue = jsonSerializableValueIn;
    }
}
