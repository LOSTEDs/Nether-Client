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

package org.apache.commons.io;

class ThreadMonitor implements Runnable {
    private final Thread thread;
    
    private final long timeout;
    
    public static Thread start(long timeout) {
        return start(Thread.currentThread(), timeout);
    }
    
    public static Thread start(Thread thread, long timeout) {
        Thread monitor = null;
        if (timeout > 0L) {
            ThreadMonitor timout = new ThreadMonitor(thread, timeout);
            monitor = new Thread(timout, ThreadMonitor.class.getSimpleName());
            monitor.setDaemon(true);
            monitor.start();
        } 
        return monitor;
    }
    
    public static void stop(Thread thread) {
        if (thread != null)
            thread.interrupt(); 
    }
    
    private ThreadMonitor(Thread thread, long timeout) {
        this.thread = thread;
        this.timeout = timeout;
    }
    
    public void run() {
        try {
            sleep(this.timeout);
            this.thread.interrupt();
        } catch (InterruptedException interruptedException) {}
    }
    
    private static void sleep(long ms) throws InterruptedException {
        long finishAt = System.currentTimeMillis() + ms;
        long remaining = ms;
        do {
            Thread.sleep(remaining);
            remaining = finishAt - System.currentTimeMillis();
        } while (remaining > 0L);
    }
}
