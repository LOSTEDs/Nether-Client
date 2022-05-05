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

package io.netty.buffer;

public interface ByteBufAllocator {
    public static final ByteBufAllocator DEFAULT = ByteBufUtil.DEFAULT_ALLOCATOR;
    
    ByteBuf buffer();
    
    ByteBuf buffer(int paramInt);
    
    ByteBuf buffer(int paramInt1, int paramInt2);
    
    ByteBuf ioBuffer();
    
    ByteBuf ioBuffer(int paramInt);
    
    ByteBuf ioBuffer(int paramInt1, int paramInt2);
    
    ByteBuf heapBuffer();
    
    ByteBuf heapBuffer(int paramInt);
    
    ByteBuf heapBuffer(int paramInt1, int paramInt2);
    
    ByteBuf directBuffer();
    
    ByteBuf directBuffer(int paramInt);
    
    ByteBuf directBuffer(int paramInt1, int paramInt2);
    
    CompositeByteBuf compositeBuffer();
    
    CompositeByteBuf compositeBuffer(int paramInt);
    
    CompositeByteBuf compositeHeapBuffer();
    
    CompositeByteBuf compositeHeapBuffer(int paramInt);
    
    CompositeByteBuf compositeDirectBuffer();
    
    CompositeByteBuf compositeDirectBuffer(int paramInt);
    
    boolean isDirectBufferPooled();
}
