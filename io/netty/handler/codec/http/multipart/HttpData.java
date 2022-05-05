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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface HttpData extends InterfaceHttpData, ByteBufHolder {
    void setContent(ByteBuf paramByteBuf) throws IOException;
    
    void addContent(ByteBuf paramByteBuf, boolean paramBoolean) throws IOException;
    
    void setContent(File paramFile) throws IOException;
    
    void setContent(InputStream paramInputStream) throws IOException;
    
    boolean isCompleted();
    
    long length();
    
    void delete();
    
    byte[] get() throws IOException;
    
    ByteBuf getByteBuf() throws IOException;
    
    ByteBuf getChunk(int paramInt) throws IOException;
    
    String getString() throws IOException;
    
    String getString(Charset paramCharset) throws IOException;
    
    void setCharset(Charset paramCharset);
    
    Charset getCharset();
    
    boolean renameTo(File paramFile) throws IOException;
    
    boolean isInMemory();
    
    File getFile() throws IOException;
    
    HttpData copy();
    
    HttpData duplicate();
    
    HttpData retain();
    
    HttpData retain(int paramInt);
}
