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

package io.netty.handler.codec.http.websocketx;

public enum WebSocketVersion {
    UNKNOWN, V00, V07, V08, V13;
    
    public String toHttpHeaderValue() {
        if (this == V00)
            return "0"; 
        if (this == V07)
            return "7"; 
        if (this == V08)
            return "8"; 
        if (this == V13)
            return "13"; 
        throw new IllegalStateException("Unknown web socket version: " + this);
    }
}
