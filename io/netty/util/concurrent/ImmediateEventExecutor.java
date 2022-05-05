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

import java.util.concurrent.TimeUnit;

public final class ImmediateEventExecutor extends AbstractEventExecutor {
    public static final ImmediateEventExecutor INSTANCE = new ImmediateEventExecutor();
    
    private final Future<?> terminationFuture = new FailedFuture(GlobalEventExecutor.INSTANCE, new UnsupportedOperationException());
    
    public EventExecutorGroup parent() {
        return null;
    }
    
    public boolean inEventLoop() {
        return true;
    }
    
    public boolean inEventLoop(Thread thread) {
        return true;
    }
    
    public Future<?> shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
        return terminationFuture();
    }
    
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }
    
    @Deprecated
    public void shutdown() {}
    
    public boolean isShuttingDown() {
        return false;
    }
    
    public boolean isShutdown() {
        return false;
    }
    
    public boolean isTerminated() {
        return false;
    }
    
    public boolean awaitTermination(long timeout, TimeUnit unit) {
        return false;
    }
    
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException("command"); 
        command.run();
    }
    
    public <V> Promise<V> newPromise() {
        return new ImmediatePromise<V>(this);
    }
    
    public <V> ProgressivePromise<V> newProgressivePromise() {
        return new ImmediateProgressivePromise<V>(this);
    }
    
    static class ImmediatePromise<V> extends DefaultPromise<V> {
        ImmediatePromise(EventExecutor executor) {
            super(executor);
        }
        
        protected void checkDeadLock() {}
    }
    
    static class ImmediateProgressivePromise<V> extends DefaultProgressivePromise<V> {
        ImmediateProgressivePromise(EventExecutor executor) {
            super(executor);
        }
        
        protected void checkDeadLock() {}
    }
}
