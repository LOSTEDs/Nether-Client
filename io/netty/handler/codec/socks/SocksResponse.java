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

package io.netty.handler.codec.socks;

public abstract class SocksResponse extends SocksMessage {
    private final SocksResponseType responseType;
    
    protected SocksResponse(SocksResponseType responseType) {
        super(SocksMessageType.RESPONSE);
        if (responseType == null)
            throw new NullPointerException("responseType"); 
        this.responseType = responseType;
    }
    
    public SocksResponseType responseType() {
        return this.responseType;
    }
}
