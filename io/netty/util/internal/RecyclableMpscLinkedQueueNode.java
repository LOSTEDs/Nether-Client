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

import io.netty.util.Recycler;

public abstract class RecyclableMpscLinkedQueueNode<T> extends MpscLinkedQueueNode<T> {
    private final Recycler.Handle handle;
    
    protected RecyclableMpscLinkedQueueNode(Recycler.Handle handle) {
        if (handle == null)
            throw new NullPointerException("handle"); 
        this.handle = handle;
    }
    
    final void unlink() {
        super.unlink();
        recycle(this.handle);
    }
    
    protected abstract void recycle(Recycler.Handle paramHandle);
}
