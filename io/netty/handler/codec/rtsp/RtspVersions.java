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

package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpVersion;

public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);
    
    public static HttpVersion valueOf(String text) {
        if (text == null)
            throw new NullPointerException("text"); 
        text = text.trim().toUpperCase();
        if ("RTSP/1.0".equals(text))
            return RTSP_1_0; 
        return new HttpVersion(text, true);
    }
}
