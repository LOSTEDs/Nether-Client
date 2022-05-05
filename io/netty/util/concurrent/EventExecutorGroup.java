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

package io.netty.util.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public interface EventExecutorGroup extends ScheduledExecutorService, Iterable<EventExecutor> {
    boolean isShuttingDown();
    
    Future<?> shutdownGracefully();
    
    Future<?> shutdownGracefully(long paramLong1, long paramLong2, TimeUnit paramTimeUnit);
    
    Future<?> terminationFuture();
    
    @Deprecated
    void shutdown();
    
    @Deprecated
    List<Runnable> shutdownNow();
    
    EventExecutor next();
    
    Iterator<EventExecutor> iterator();
    
    Future<?> submit(Runnable paramRunnable);
    
    <T> Future<T> submit(Runnable paramRunnable, T paramT);
    
    <T> Future<T> submit(Callable<T> paramCallable);
    
    ScheduledFuture<?> schedule(Runnable paramRunnable, long paramLong, TimeUnit paramTimeUnit);
    
    <V> ScheduledFuture<V> schedule(Callable<V> paramCallable, long paramLong, TimeUnit paramTimeUnit);
    
    ScheduledFuture<?> scheduleAtFixedRate(Runnable paramRunnable, long paramLong1, long paramLong2, TimeUnit paramTimeUnit);
    
    ScheduledFuture<?> scheduleWithFixedDelay(Runnable paramRunnable, long paramLong1, long paramLong2, TimeUnit paramTimeUnit);
}
