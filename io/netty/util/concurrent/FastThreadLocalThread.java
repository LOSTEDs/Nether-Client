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

import io.netty.util.internal.InternalThreadLocalMap;

public class FastThreadLocalThread extends Thread {
    private InternalThreadLocalMap threadLocalMap;
    
    public FastThreadLocalThread() {}
    
    public FastThreadLocalThread(Runnable target) {
        super(target);
    }
    
    public FastThreadLocalThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }
    
    public FastThreadLocalThread(String name) {
        super(name);
    }
    
    public FastThreadLocalThread(ThreadGroup group, String name) {
        super(group, name);
    }
    
    public FastThreadLocalThread(Runnable target, String name) {
        super(target, name);
    }
    
    public FastThreadLocalThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }
    
    public FastThreadLocalThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }
    
    public final InternalThreadLocalMap threadLocalMap() {
        return this.threadLocalMap;
    }
    
    public final void setThreadLocalMap(InternalThreadLocalMap threadLocalMap) {
        this.threadLocalMap = threadLocalMap;
    }
}
