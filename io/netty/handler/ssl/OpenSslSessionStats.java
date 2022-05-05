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

package io.netty.handler.ssl;

import org.apache.tomcat.jni.SSLContext;

public final class OpenSslSessionStats {
    private final long context;
    
    OpenSslSessionStats(long context) {
        this.context = context;
    }
    
    public long number() {
        return SSLContext.sessionNumber(this.context);
    }
    
    public long connect() {
        return SSLContext.sessionConnect(this.context);
    }
    
    public long connectGood() {
        return SSLContext.sessionConnectGood(this.context);
    }
    
    public long connectRenegotiate() {
        return SSLContext.sessionConnectRenegotiate(this.context);
    }
    
    public long accept() {
        return SSLContext.sessionAccept(this.context);
    }
    
    public long acceptGood() {
        return SSLContext.sessionAcceptGood(this.context);
    }
    
    public long acceptRenegotiate() {
        return SSLContext.sessionAcceptRenegotiate(this.context);
    }
    
    public long hits() {
        return SSLContext.sessionHits(this.context);
    }
    
    public long cbHits() {
        return SSLContext.sessionCbHits(this.context);
    }
    
    public long misses() {
        return SSLContext.sessionMisses(this.context);
    }
    
    public long timeouts() {
        return SSLContext.sessionTimeouts(this.context);
    }
    
    public long cacheFull() {
        return SSLContext.sessionCacheFull(this.context);
    }
}
