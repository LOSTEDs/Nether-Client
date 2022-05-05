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

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapPopulator {
    public static <K, V> Map<K, V> createMap(Iterable<K> keys, Iterable<V> values) {
        return populateMap(keys, values, Maps.newLinkedHashMap());
    }
    
    public static <K, V> Map<K, V> populateMap(Iterable<K> keys, Iterable<V> values, Map<K, V> map) {
        Iterator<V> iterator = values.iterator();
        for (K k : keys)
            map.put(k, iterator.next()); 
        if (iterator.hasNext())
            throw new NoSuchElementException(); 
        return map;
    }
}
