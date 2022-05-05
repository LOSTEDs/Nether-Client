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

package io.netty.channel;

import io.netty.util.concurrent.Future;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ChannelPromiseAggregator implements ChannelFutureListener {
    private final ChannelPromise aggregatePromise;
    
    private Set<ChannelPromise> pendingPromises;
    
    public ChannelPromiseAggregator(ChannelPromise aggregatePromise) {
        if (aggregatePromise == null)
            throw new NullPointerException("aggregatePromise"); 
        this.aggregatePromise = aggregatePromise;
    }
    
    public ChannelPromiseAggregator add(ChannelPromise... promises) {
        if (promises == null)
            throw new NullPointerException("promises"); 
        if (promises.length == 0)
            return this; 
        synchronized (this) {
            if (this.pendingPromises == null) {
                int size;
                if (promises.length > 1) {
                    size = promises.length;
                } else {
                    size = 2;
                } 
                this.pendingPromises = new LinkedHashSet<ChannelPromise>(size);
            } 
            for (ChannelPromise p : promises) {
                if (p != null) {
                    this.pendingPromises.add(p);
                    p.addListener(this);
                } 
            } 
        } 
        return this;
    }
    
    public synchronized void operationComplete(ChannelFuture future) throws Exception {
        if (this.pendingPromises == null) {
            this.aggregatePromise.setSuccess();
        } else {
            this.pendingPromises.remove(future);
            if (!future.isSuccess()) {
                this.aggregatePromise.setFailure(future.cause());
                for (ChannelPromise pendingFuture : this.pendingPromises)
                    pendingFuture.setFailure(future.cause()); 
            } else if (this.pendingPromises.isEmpty()) {
                this.aggregatePromise.setSuccess();
            } 
        } 
    }
}
