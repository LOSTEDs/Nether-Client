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

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public abstract class MpscLinkedQueueNode<T> {
    private static final AtomicReferenceFieldUpdater<MpscLinkedQueueNode, MpscLinkedQueueNode> nextUpdater;
    
    private volatile MpscLinkedQueueNode<T> next;
    
    static {
        AtomicReferenceFieldUpdater<MpscLinkedQueueNode, MpscLinkedQueueNode> u = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueNode.class, "next");
        if (u == null)
            u = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueNode.class, MpscLinkedQueueNode.class, "next"); 
        nextUpdater = u;
    }
    
    final MpscLinkedQueueNode<T> next() {
        return this.next;
    }
    
    final void setNext(MpscLinkedQueueNode<T> newNext) {
        nextUpdater.lazySet(this, newNext);
    }
    
    protected T clearMaybe() {
        return value();
    }
    
    void unlink() {
        setNext(null);
    }
    
    public abstract T value();
}
