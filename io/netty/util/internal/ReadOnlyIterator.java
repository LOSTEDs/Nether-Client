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

import java.util.Iterator;

public final class ReadOnlyIterator<T> implements Iterator<T> {
    private final Iterator<? extends T> iterator;
    
    public ReadOnlyIterator(Iterator<? extends T> iterator) {
        if (iterator == null)
            throw new NullPointerException("iterator"); 
        this.iterator = iterator;
    }
    
    public boolean hasNext() {
        return this.iterator.hasNext();
    }
    
    public T next() {
        return this.iterator.next();
    }
    
    public void remove() {
        throw new UnsupportedOperationException("read-only");
    }
}
