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

public interface FileUpload extends HttpData {
    String getFilename();
    
    void setFilename(String paramString);
    
    void setContentType(String paramString);
    
    String getContentType();
    
    void setContentTransferEncoding(String paramString);
    
    String getContentTransferEncoding();
    
    FileUpload copy();
    
    FileUpload duplicate();
    
    FileUpload retain();
    
    FileUpload retain(int paramInt);
}
