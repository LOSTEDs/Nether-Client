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

package io.netty.channel.local;

import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ThreadFactory;

public class LocalEventLoopGroup extends MultithreadEventLoopGroup {
    public LocalEventLoopGroup() {
        this(0);
    }
    
    public LocalEventLoopGroup(int nThreads) {
        this(nThreads, null);
    }
    
    public LocalEventLoopGroup(int nThreads, ThreadFactory threadFactory) {
        super(nThreads, threadFactory, new Object[0]);
    }
    
    protected EventExecutor newChild(ThreadFactory threadFactory, Object... args) throws Exception {
        return (EventExecutor)new LocalEventLoop(this, threadFactory);
    }
}
