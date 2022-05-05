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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectorMethod {
    private ReflectorClass reflectorClass;
    
    private String targetMethodName;
    
    private Class[] targetMethodParameterTypes;
    
    private boolean checked;
    
    private Method targetMethod;
    
    public ReflectorMethod(ReflectorClass p_i104_1_, String p_i104_2_) {
        this(p_i104_1_, p_i104_2_, null, false);
    }
    
    public ReflectorMethod(ReflectorClass p_i105_1_, String p_i105_2_, Class[] p_i105_3_) {
        this(p_i105_1_, p_i105_2_, p_i105_3_, false);
    }
    
    public ReflectorMethod(ReflectorClass p_i106_1_, String p_i106_2_, Class[] p_i106_3_, boolean p_i106_4_) {
        this.reflectorClass = null;
        this.targetMethodName = null;
        this.targetMethodParameterTypes = null;
        this.checked = false;
        this.targetMethod = null;
        this.reflectorClass = p_i106_1_;
        this.targetMethodName = p_i106_2_;
        this.targetMethodParameterTypes = p_i106_3_;
        if (!p_i106_4_)
            Method method = getTargetMethod(); 
    }
    
    public Method getTargetMethod() {
        if (this.checked)
            return this.targetMethod; 
        this.checked = true;
        Class oclass = this.reflectorClass.getTargetClass();
        if (oclass == null)
            return null; 
        try {
            if (this.targetMethodParameterTypes == null) {
                Method[] amethod = getMethods(oclass, this.targetMethodName);
                if (amethod.length <= 0) {
                    Config.log("(Reflector) Method not present: " + oclass.getName() + "." + this.targetMethodName);
                    return null;
                } 
                if (amethod.length > 1) {
                    Config.warn("(Reflector) More than one method found: " + oclass.getName() + "." + this.targetMethodName);
                    for (int i = 0; i < amethod.length; i++) {
                        Method method = amethod[i];
                        Config.warn("(Reflector)  - " + method);
                    } 
                    return null;
                } 
                this.targetMethod = amethod[0];
            } else {
                this.targetMethod = getMethod(oclass, this.targetMethodName, this.targetMethodParameterTypes);
            } 
            if (this.targetMethod == null) {
                Config.log("(Reflector) Method not present: " + oclass.getName() + "." + this.targetMethodName);
                return null;
            } 
            this.targetMethod.setAccessible(true);
            return this.targetMethod;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        } 
    }
    
    public boolean exists() {
        return this.checked ? ((this.targetMethod != null)) : ((getTargetMethod() != null));
    }
    
    public Class getReturnType() {
        Method method = getTargetMethod();
        return (method == null) ? null : method.getReturnType();
    }
    
    public void deactivate() {
        this.checked = true;
        this.targetMethod = null;
    }
    
    public static Method getMethod(Class p_getMethod_0_, String p_getMethod_1_, Class[] p_getMethod_2_) {
        Method[] amethod = p_getMethod_0_.getDeclaredMethods();
        for (int i = 0; i < amethod.length; i++) {
            Method method = amethod[i];
            if (method.getName().equals(p_getMethod_1_)) {
                Class[] aclass = method.getParameterTypes();
                if (Reflector.matchesTypes(p_getMethod_2_, aclass))
                    return method; 
            } 
        } 
        return null;
    }
    
    public static Method[] getMethods(Class p_getMethods_0_, String p_getMethods_1_) {
        List<Method> list = new ArrayList();
        Method[] amethod = p_getMethods_0_.getDeclaredMethods();
        for (int i = 0; i < amethod.length; i++) {
            Method method = amethod[i];
            if (method.getName().equals(p_getMethods_1_))
                list.add(method); 
        } 
        Method[] amethod1 = list.<Method>toArray(new Method[list.size()]);
        return amethod1;
    }
}