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

package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;

public interface SpdyFrameDecoderDelegate {
    void readDataFrame(int paramInt, boolean paramBoolean, ByteBuf paramByteBuf);
    
    void readSynStreamFrame(int paramInt1, int paramInt2, byte paramByte, boolean paramBoolean1, boolean paramBoolean2);
    
    void readSynReplyFrame(int paramInt, boolean paramBoolean);
    
    void readRstStreamFrame(int paramInt1, int paramInt2);
    
    void readSettingsFrame(boolean paramBoolean);
    
    void readSetting(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
    
    void readSettingsEnd();
    
    void readPingFrame(int paramInt);
    
    void readGoAwayFrame(int paramInt1, int paramInt2);
    
    void readHeadersFrame(int paramInt, boolean paramBoolean);
    
    void readWindowUpdateFrame(int paramInt1, int paramInt2);
    
    void readHeaderBlock(ByteBuf paramByteBuf);
    
    void readHeaderBlockEnd();
    
    void readFrameError(String paramString);
}
