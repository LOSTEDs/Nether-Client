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

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class ObjectIntIdentityMap implements IObjectIntIterable {
    private final IdentityHashMap identityMap = new IdentityHashMap<>(512);
    
    private final List objectList = Lists.newArrayList();
    
    private static final String __OBFID = "CL_00001203";
    
    public void put(Object key, int value) {
        this.identityMap.put(key, Integer.valueOf(value));
        while (this.objectList.size() <= value)
            this.objectList.add(null); 
        this.objectList.set(value, key);
    }
    
    public int get(Object key) {
        Integer integer = (Integer)this.identityMap.get(key);
        return (integer == null) ? -1 : integer.intValue();
    }
    
    public final Object getByValue(int value) {
        return (value >= 0 && value < this.objectList.size()) ? this.objectList.get(value) : null;
    }
    
    public Iterator iterator() {
        return (Iterator)Iterators.filter(this.objectList.iterator(), Predicates.notNull());
    }
    
    public List getObjectList() {
        return this.objectList;
    }
}
