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

package client.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventManager {
    public static void register(Object o) {
        byte b;
        int i;
        Method[] arrayOfMethod;
        for (i = (arrayOfMethod = o.getClass().getDeclaredMethods()).length, b = 0; b < i; ) {
            Method method = arrayOfMethod[b];
            if (!isMethodBad(method))
                register(method, o); 
            b++;
        } 
    }
    
    public static void register(Object o, Class<? extends Event> clazz) {
        byte b;
        int i;
        Method[] arrayOfMethod;
        for (i = (arrayOfMethod = o.getClass().getDeclaredMethods()).length, b = 0; b < i; ) {
            Method method = arrayOfMethod[b];
            if (!isMethodBad(method, clazz))
                register(method, o); 
            b++;
        } 
    }
    
    private static void register(Method method, Object o) {
        Class<?> clazz = method.getParameterTypes()[0];
        Data methodData = new Data(o, method, ((EventTarget)method.<EventTarget>getAnnotation(EventTarget.class)).value());
        if (!methodData.target.isAccessible())
            methodData.target.setAccessible(true); 
        if (REGISTRY_MAP.containsKey(clazz)) {
            if (!((ArrayHelper<Data>)REGISTRY_MAP.get(clazz)).contains(methodData)) {
                ((ArrayHelper<Data>)REGISTRY_MAP.get(clazz)).add(methodData);
                sortListValue((Class)clazz);
            } 
        } else {
            REGISTRY_MAP.put(clazz, new ArrayHelper<Data>(methodData) {
                    
                    });
        } 
    }
    
    public static void unregister(Object o) {
        for (ArrayHelper<Data> flexibalArray : REGISTRY_MAP.values()) {
            for (Data methodData : flexibalArray) {
                if (methodData.source.equals(o))
                    flexibalArray.remove(methodData); 
            } 
        } 
        cleanMap(true);
    }
    
    public static void unregister(Object o, Class<? extends Event> clazz) {
        if (REGISTRY_MAP.containsKey(clazz)) {
            for (Data methodData : REGISTRY_MAP.get(clazz)) {
                if (methodData.source.equals(o))
                    ((ArrayHelper<Data>)REGISTRY_MAP.get(clazz)).remove(methodData); 
            } 
            cleanMap(true);
        } 
    }
    
    public static void cleanMap(boolean b) {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!b || ((ArrayHelper)((Map.Entry)iterator.next()).getValue()).isEmpty())
                iterator.remove(); 
        } 
    }
    
    public static void removeEnty(Class<? extends Event> clazz) {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (((Class)((Map.Entry)iterator.next()).getKey()).equals(clazz)) {
                iterator.remove();
                break;
            } 
        } 
    }
    
    private static void sortListValue(Class<? extends Event> clazz) {
        ArrayHelper<Data> flexibleArray = new ArrayHelper<>();
        byte b;
        int i;
        byte[] arrayOfByte;
        for (i = (arrayOfByte = Priority.VALUE_ARRAY).length, b = 0; b < i; ) {
            byte b1 = arrayOfByte[b];
            for (Data methodData : REGISTRY_MAP.get(clazz)) {
                if (methodData.priority == b1)
                    flexibleArray.add(methodData); 
            } 
            b++;
        } 
        REGISTRY_MAP.put(clazz, flexibleArray);
    }
    
    private static boolean isMethodBad(Method method) {
        return !((method.getParameterTypes()).length == 1 && method.isAnnotationPresent((Class)EventTarget.class));
    }
    
    private static boolean isMethodBad(Method method, Class<? extends Event> clazz) {
        return !(!isMethodBad(method) && !method.getParameterTypes()[0].equals(clazz));
    }
    
    public static ArrayHelper<Data> get(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }
    
    public static void shutdown() {
        REGISTRY_MAP.clear();
    }
    
    private static final Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP = new HashMap<>();
}
