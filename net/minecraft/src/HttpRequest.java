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

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {
    private String host = null;
    
    private int port = 0;
    
    private Proxy proxy = Proxy.NO_PROXY;
    
    private String method = null;
    
    private String file = null;
    
    private String http = null;
    
    private Map<String, String> headers = new LinkedHashMap<>();
    
    private byte[] body = null;
    
    private int redirects = 0;
    
    public static final String METHOD_GET = "GET";
    
    public static final String METHOD_HEAD = "HEAD";
    
    public static final String METHOD_POST = "POST";
    
    public static final String HTTP_1_0 = "HTTP/1.0";
    
    public static final String HTTP_1_1 = "HTTP/1.1";
    
    public HttpRequest(String p_i67_1_, int p_i67_2_, Proxy p_i67_3_, String p_i67_4_, String p_i67_5_, String p_i67_6_, Map<String, String> p_i67_7_, byte[] p_i67_8_) {
        this.host = p_i67_1_;
        this.port = p_i67_2_;
        this.proxy = p_i67_3_;
        this.method = p_i67_4_;
        this.file = p_i67_5_;
        this.http = p_i67_6_;
        this.headers = p_i67_7_;
        this.body = p_i67_8_;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public String getFile() {
        return this.file;
    }
    
    public String getHttp() {
        return this.http;
    }
    
    public Map<String, String> getHeaders() {
        return this.headers;
    }
    
    public byte[] getBody() {
        return this.body;
    }
    
    public int getRedirects() {
        return this.redirects;
    }
    
    public void setRedirects(int p_setRedirects_1_) {
        this.redirects = p_setRedirects_1_;
    }
    
    public Proxy getProxy() {
        return this.proxy;
    }
}
