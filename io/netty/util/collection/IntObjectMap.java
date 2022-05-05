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

package io.netty.util.collection;

public interface IntObjectMap<V> {
    V get(int paramInt);
    
    V put(int paramInt, V paramV);
    
    void putAll(IntObjectMap<V> paramIntObjectMap);
    
    V remove(int paramInt);
    
    int size();
    
    boolean isEmpty();
    
    void clear();
    
    boolean containsKey(int paramInt);
    
    boolean containsValue(V paramV);
    
    Iterable<Entry<V>> entries();
    
    int[] keys();
    
    V[] values(Class<V> paramClass);
    
    public static interface Entry<V> {
        int key();
        
        V value();
        
        void setValue(V param1V);
    }
}
