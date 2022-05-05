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
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class MpscLinkedQueueHeadRef<E> extends MpscLinkedQueuePad0<E> implements Serializable {
    private static final long serialVersionUID = 8467054865577874285L;
    
    private static final AtomicReferenceFieldUpdater<MpscLinkedQueueHeadRef, MpscLinkedQueueNode> UPDATER;
    
    private volatile transient MpscLinkedQueueNode<E> headRef;
    
    static {
        AtomicReferenceFieldUpdater<MpscLinkedQueueHeadRef, MpscLinkedQueueNode> updater = PlatformDependent.newAtomicReferenceFieldUpdater(MpscLinkedQueueHeadRef.class, "headRef");
        if (updater == null)
            updater = AtomicReferenceFieldUpdater.newUpdater(MpscLinkedQueueHeadRef.class, MpscLinkedQueueNode.class, "headRef"); 
        UPDATER = updater;
    }
    
    protected final MpscLinkedQueueNode<E> headRef() {
        return this.headRef;
    }
    
    protected final void setHeadRef(MpscLinkedQueueNode<E> headRef) {
        this.headRef = headRef;
    }
    
    protected final void lazySetHeadRef(MpscLinkedQueueNode<E> headRef) {
        UPDATER.lazySet(this, headRef);
    }
}
