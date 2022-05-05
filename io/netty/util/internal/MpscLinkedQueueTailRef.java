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

abstract class MpscLinkedQueueTailRef<E> extends MpscLinkedQueuePad1<E> {
    private static final long serialVersionUID = 8717072462993327429L;
    
    private static final AtomicReferenceFieldUpdater<MpscLinkedQueueTailRef, MpscLinkedQueueNode> UPDATER;
    
    private volatile transient MpscLinkedQueueNode<E> tailRef;
    
    static {
        AtomicReferenceFieldUpdater<MpscLinkedQueueTailRef, MpscLinkedQueueNode> updater = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueTailRef.class, "tailRef");
        if (updater == null)
            updater = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueTailRef.class, MpscLinkedQueueNode.class, "tailRef"); 
        UPDATER = updater;
    }
    
    protected final MpscLinkedQueueNode<E> tailRef() {
        return this.tailRef;
    }
    
    protected final void setTailRef(MpscLinkedQueueNode<E> tailRef) {
        this.tailRef = tailRef;
    }
    
    protected final MpscLinkedQueueNode<E> getAndSetTailRef(MpscLinkedQueueNode<E> tailRef) {
        return UPDATER.getAndSet(this, tailRef);
    }
}
