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

package io.netty.handler.codec.http.multipart;

import java.io.IOException;

public interface Attribute extends HttpData {
    String getValue() throws IOException;
    
    void setValue(String paramString) throws IOException;
    
    Attribute copy();
    
    Attribute duplicate();
    
    Attribute retain();
    
    Attribute retain(int paramInt);
}
