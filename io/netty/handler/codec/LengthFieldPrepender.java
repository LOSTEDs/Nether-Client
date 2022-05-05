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

package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class LengthFieldPrepender extends MessageToByteEncoder<ByteBuf> {
    private final int lengthFieldLength;
    
    private final boolean lengthIncludesLengthFieldLength;
    
    private final int lengthAdjustment;
    
    public LengthFieldPrepender(int lengthFieldLength) {
        this(lengthFieldLength, false);
    }
    
    public LengthFieldPrepender(int lengthFieldLength, boolean lengthIncludesLengthFieldLength) {
        this(lengthFieldLength, 0, lengthIncludesLengthFieldLength);
    }
    
    public LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment) {
        this(lengthFieldLength, lengthAdjustment, false);
    }
    
    public LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment, boolean lengthIncludesLengthFieldLength) {
        if (lengthFieldLength != 1 && lengthFieldLength != 2 && lengthFieldLength != 3 && lengthFieldLength != 4 && lengthFieldLength != 8)
            throw new IllegalArgumentException("lengthFieldLength must be either 1, 2, 3, 4, or 8: " + lengthFieldLength); 
        this.lengthFieldLength = lengthFieldLength;
        this.lengthIncludesLengthFieldLength = lengthIncludesLengthFieldLength;
        this.lengthAdjustment = lengthAdjustment;
    }
    
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int length = msg.readableBytes() + this.lengthAdjustment;
        if (this.lengthIncludesLengthFieldLength)
            length += this.lengthFieldLength; 
        if (length < 0)
            throw new IllegalArgumentException("Adjusted frame length (" + length + ") is less than zero"); 
        switch (this.lengthFieldLength) {
            case 1:
                if (length >= 256)
                    throw new IllegalArgumentException("length does not fit into a byte: " + length); 
                out.writeByte((byte)length);
                break;
            case 2:
                if (length >= 65536)
                    throw new IllegalArgumentException("length does not fit into a short integer: " + length); 
                out.writeShort((short)length);
                break;
            case 3:
                if (length >= 16777216)
                    throw new IllegalArgumentException("length does not fit into a medium integer: " + length); 
                out.writeMedium(length);
                break;
            case 4:
                out.writeInt(length);
                break;
            case 8:
                out.writeLong(length);
                break;
            default:
                throw new Error("should not reach here");
        } 
        out.writeBytes(msg, msg.readerIndex(), msg.readableBytes());
    }
}
