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

package net.minecraft.src;

public class HttpPipelineRequest {
    private HttpRequest httpRequest = null;
    
    private HttpListener httpListener = null;
    
    private boolean closed = false;
    
    public HttpPipelineRequest(HttpRequest p_i65_1_, HttpListener p_i65_2_) {
        this.httpRequest = p_i65_1_;
        this.httpListener = p_i65_2_;
    }
    
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }
    
    public HttpListener getHttpListener() {
        return this.httpListener;
    }
    
    public boolean isClosed() {
        return this.closed;
    }
    
    public void setClosed(boolean p_setClosed_1_) {
        this.closed = p_setClosed_1_;
    }
}
