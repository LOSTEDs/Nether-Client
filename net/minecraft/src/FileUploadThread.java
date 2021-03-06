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

import java.util.Map;

public class FileUploadThread extends Thread {
    private String urlString;
    
    private Map headers;
    
    private byte[] content;
    
    private IFileUploadListener listener;
    
    public FileUploadThread(String p_i49_1_, Map p_i49_2_, byte[] p_i49_3_, IFileUploadListener p_i49_4_) {
        this.urlString = p_i49_1_;
        this.headers = p_i49_2_;
        this.content = p_i49_3_;
        this.listener = p_i49_4_;
    }
    
    public void run() {
        try {
            HttpUtils.post(this.urlString, this.headers, this.content);
            this.listener.fileUploadFinished(this.urlString, this.content, null);
        } catch (Exception exception) {
            this.listener.fileUploadFinished(this.urlString, this.content, exception);
        } 
    }
    
    public String getUrlString() {
        return this.urlString;
    }
    
    public byte[] getContent() {
        return this.content;
    }
    
    public IFileUploadListener getListener() {
        return this.listener;
    }
}
