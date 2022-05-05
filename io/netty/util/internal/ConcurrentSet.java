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

package io.netty.util.internal;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

public final class ConcurrentSet<E> extends AbstractSet<E> implements Serializable {
    private static final long serialVersionUID = -6761513279741915432L;
    
    private final ConcurrentMap<E, Boolean> map = PlatformDependent.newConcurrentHashMap();
    
    public int size() {
        return this.map.size();
    }
    
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }
    
    public boolean add(E o) {
        return (this.map.putIfAbsent(o, Boolean.TRUE) == null);
    }
    
    public boolean remove(Object o) {
        return (this.map.remove(o) != null);
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }
}
