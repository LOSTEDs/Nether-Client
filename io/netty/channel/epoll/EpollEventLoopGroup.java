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

package io.netty.channel.epoll;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ThreadFactory;

public final class EpollEventLoopGroup extends MultithreadEventLoopGroup {
    public EpollEventLoopGroup() {
        this(0);
    }
    
    public EpollEventLoopGroup(int nThreads) {
        this(nThreads, null);
    }
    
    public EpollEventLoopGroup(int nThreads, ThreadFactory threadFactory) {
        this(nThreads, threadFactory, 128);
    }
    
    public EpollEventLoopGroup(int nThreads, ThreadFactory threadFactory, int maxEventsAtOnce) {
        super(nThreads, threadFactory, new Object[] { Integer.valueOf(maxEventsAtOnce) });
    }
    
    public void setIoRatio(int ioRatio) {
        for (EventExecutor e : children())
            ((EpollEventLoop)e).setIoRatio(ioRatio); 
    }
    
    protected EventExecutor newChild(ThreadFactory threadFactory, Object... args) throws Exception {
        return (EventExecutor)new EpollEventLoop((EventLoopGroup)this, threadFactory, ((Integer)args[0]).intValue());
    }
}
