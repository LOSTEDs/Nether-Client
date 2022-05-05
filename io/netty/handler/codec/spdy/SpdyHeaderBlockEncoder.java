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
import io.netty.util.internal.PlatformDependent;

abstract class SpdyHeaderBlockEncoder {
    static SpdyHeaderBlockEncoder newInstance(SpdyVersion version, int compressionLevel, int windowBits, int memLevel) {
        if (PlatformDependent.javaVersion() >= 7)
            return new SpdyHeaderBlockZlibEncoder(version, compressionLevel); 
        return new SpdyHeaderBlockJZlibEncoder(version, compressionLevel, windowBits, memLevel);
    }
    
    abstract ByteBuf encode(SpdyHeadersFrame paramSpdyHeadersFrame) throws Exception;
    
    abstract void end();
}
