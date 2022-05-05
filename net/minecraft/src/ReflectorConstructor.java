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

import java.lang.reflect.Constructor;

public class ReflectorConstructor {
    private ReflectorClass reflectorClass = null;
    
    private Class[] parameterTypes = null;
    
    private boolean checked = false;
    
    private Constructor targetConstructor = null;
    
    public ReflectorConstructor(ReflectorClass p_i95_1_, Class[] p_i95_2_) {
        this.reflectorClass = p_i95_1_;
        this.parameterTypes = p_i95_2_;
        Constructor constructor = getTargetConstructor();
    }
    
    public Constructor getTargetConstructor() {
        if (this.checked)
            return this.targetConstructor; 
        this.checked = true;
        Class oclass = this.reflectorClass.getTargetClass();
        if (oclass == null)
            return null; 
        try {
            this.targetConstructor = findConstructor(oclass, this.parameterTypes);
            if (this.targetConstructor == null)
                Config.dbg("(Reflector) Constructor not present: " + oclass.getName() + ", params: " + Config.arrayToString((Object[])this.parameterTypes)); 
            if (this.targetConstructor != null)
                this.targetConstructor.setAccessible(true); 
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } 
        return this.targetConstructor;
    }
    
    private static Constructor findConstructor(Class p_findConstructor_0_, Class[] p_findConstructor_1_) {
        Constructor[] aconstructor = (Constructor[])p_findConstructor_0_.getDeclaredConstructors();
        for (int i = 0; i < aconstructor.length; i++) {
            Constructor constructor = aconstructor[i];
            Class[] aclass = constructor.getParameterTypes();
            if (Reflector.matchesTypes(p_findConstructor_1_, aclass))
                return constructor; 
        } 
        return null;
    }
    
    public boolean exists() {
        return this.checked ? ((this.targetConstructor != null)) : ((getTargetConstructor() != null));
    }
    
    public void deactivate() {
        this.checked = true;
        this.targetConstructor = null;
    }
}
