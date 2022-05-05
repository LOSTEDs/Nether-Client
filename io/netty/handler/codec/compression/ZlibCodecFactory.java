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

package io.netty.handler.codec.compression;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public final class ZlibCodecFactory {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ZlibCodecFactory.class);
    
    private static final boolean noJdkZlibDecoder = SystemPropertyUtil.getBoolean("io.netty.noJdkZlibDecoder", true);
    
    static {
        logger.debug("-Dio.netty.noJdkZlibDecoder: {}", Boolean.valueOf(noJdkZlibDecoder));
    }
    
    public static ZlibEncoder newZlibEncoder(int compressionLevel) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(compressionLevel); 
        return new JdkZlibEncoder(compressionLevel);
    }
    
    public static ZlibEncoder newZlibEncoder(ZlibWrapper wrapper) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(wrapper); 
        return new JdkZlibEncoder(wrapper);
    }
    
    public static ZlibEncoder newZlibEncoder(ZlibWrapper wrapper, int compressionLevel) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(wrapper, compressionLevel); 
        return new JdkZlibEncoder(wrapper, compressionLevel);
    }
    
    public static ZlibEncoder newZlibEncoder(ZlibWrapper wrapper, int compressionLevel, int windowBits, int memLevel) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(wrapper, compressionLevel, windowBits, memLevel); 
        return new JdkZlibEncoder(wrapper, compressionLevel);
    }
    
    public static ZlibEncoder newZlibEncoder(byte[] dictionary) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(dictionary); 
        return new JdkZlibEncoder(dictionary);
    }
    
    public static ZlibEncoder newZlibEncoder(int compressionLevel, byte[] dictionary) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(compressionLevel, dictionary); 
        return new JdkZlibEncoder(compressionLevel, dictionary);
    }
    
    public static ZlibEncoder newZlibEncoder(int compressionLevel, int windowBits, int memLevel, byte[] dictionary) {
        if (PlatformDependent.javaVersion() < 7)
            return new JZlibEncoder(compressionLevel, windowBits, memLevel, dictionary); 
        return new JdkZlibEncoder(compressionLevel, dictionary);
    }
    
    public static ZlibDecoder newZlibDecoder() {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder)
            return new JZlibDecoder(); 
        return new JdkZlibDecoder();
    }
    
    public static ZlibDecoder newZlibDecoder(ZlibWrapper wrapper) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder)
            return new JZlibDecoder(wrapper); 
        return new JdkZlibDecoder(wrapper);
    }
    
    public static ZlibDecoder newZlibDecoder(byte[] dictionary) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder)
            return new JZlibDecoder(dictionary); 
        return new JdkZlibDecoder(dictionary);
    }
}
