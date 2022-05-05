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

package io.netty.util.internal.chmv8;

public class ForkJoinWorkerThread extends Thread {
    final ForkJoinPool pool;
    
    final ForkJoinPool.WorkQueue workQueue;
    
    protected ForkJoinWorkerThread(ForkJoinPool pool) {
        super("aForkJoinWorkerThread");
        this.pool = pool;
        this.workQueue = pool.registerWorker(this);
    }
    
    public ForkJoinPool getPool() {
        return this.pool;
    }
    
    public int getPoolIndex() {
        return this.workQueue.poolIndex >>> 1;
    }
    
    protected void onStart() {}
    
    protected void onTermination(Throwable exception) {}
    
    public void run() {
        Throwable exception = null;
        try {
            onStart();
            this.pool.runWorker(this.workQueue);
        } catch (Throwable ex) {
            exception = ex;
        } finally {
            try {
                onTermination(exception);
            } catch (Throwable ex) {
                if (exception == null)
                    exception = ex; 
            } finally {
                this.pool.deregisterWorker(this, exception);
            } 
        } 
    }
}
