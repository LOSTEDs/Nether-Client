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

import java.lang.reflect.Field;

public class FieldLocatorName implements IFieldLocator {
    private ReflectorClass reflectorClass = null;
    
    private String targetFieldName = null;
    
    public FieldLocatorName(ReflectorClass p_i44_1_, String p_i44_2_) {
        this.reflectorClass = p_i44_1_;
        this.targetFieldName = p_i44_2_;
    }
    
    public Field getField() {
        Class oclass = this.reflectorClass.getTargetClass();
        if (oclass == null)
            return null; 
        try {
            Field field = getDeclaredField(oclass, this.targetFieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException var3) {
            Config.log("(Reflector) Field not present: " + oclass.getName() + "." + this.targetFieldName);
            return null;
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
            return null;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        } 
    }
    
    private Field getDeclaredField(Class<Object> p_getDeclaredField_1_, String p_getDeclaredField_2_) throws NoSuchFieldException {
        Field[] afield = p_getDeclaredField_1_.getDeclaredFields();
        for (int i = 0; i < afield.length; i++) {
            Field field = afield[i];
            if (field.getName().equals(p_getDeclaredField_2_))
                return field; 
        } 
        if (p_getDeclaredField_1_ == Object.class)
            throw new NoSuchFieldException(p_getDeclaredField_2_); 
        return getDeclaredField(p_getDeclaredField_1_.getSuperclass(), p_getDeclaredField_2_);
    }
}
