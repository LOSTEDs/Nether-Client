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

package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;

public final class Signal extends Error {
    private static final long serialVersionUID = -221145131122459977L;
    
    private static final ConcurrentMap<String, Boolean> map = PlatformDependent.newConcurrentHashMap();
    
    private final UniqueName uname;
    
    public static Signal valueOf(String name) {
        return new Signal(name);
    }
    
    @Deprecated
    public Signal(String name) {
        super(name);
        this.uname = new UniqueName(map, name, new Object[0]);
    }
    
    public void expect(Signal signal) {
        if (this != signal)
            throw new IllegalStateException("unexpected signal: " + signal); 
    }
    
    public Throwable initCause(Throwable cause) {
        return this;
    }
    
    public Throwable fillInStackTrace() {
        return this;
    }
    
    public String toString() {
        return this.uname.name();
    }
}
