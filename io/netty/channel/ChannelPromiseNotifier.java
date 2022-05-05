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

public final class ChannelPromiseNotifier implements ChannelFutureListener {
    private final ChannelPromise[] promises;
    
    public ChannelPromiseNotifier(ChannelPromise... promises) {
        if (promises == null)
            throw new NullPointerException("promises"); 
        for (ChannelPromise promise : promises) {
            if (promise == null)
                throw new IllegalArgumentException("promises contains null ChannelPromise"); 
        } 
        this.promises = (ChannelPromise[])promises.clone();
    }
    
    public void operationComplete(ChannelFuture cf) throws Exception {
        if (cf.isSuccess()) {
            for (ChannelPromise p : this.promises)
                p.setSuccess(); 
            return;
        } 
        Throwable cause = cf.cause();
        for (ChannelPromise p : this.promises)
            p.setFailure(cause); 
    }
}
