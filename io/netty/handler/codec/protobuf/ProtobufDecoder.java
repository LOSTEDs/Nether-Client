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

package io.netty.handler.codec.protobuf;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

@Sharable
public class ProtobufDecoder extends MessageToMessageDecoder<ByteBuf> {
    private static final boolean HAS_PARSER;
    
    private final MessageLite prototype;
    
    private final ExtensionRegistry extensionRegistry;
    
    static {
        boolean hasParser = false;
        try {
            MessageLite.class.getDeclaredMethod("getParserForType", new Class[0]);
            hasParser = true;
        } catch (Throwable t) {}
        HAS_PARSER = hasParser;
    }
    
    public ProtobufDecoder(MessageLite prototype) {
        this(prototype, null);
    }
    
    public ProtobufDecoder(MessageLite prototype, ExtensionRegistry extensionRegistry) {
        if (prototype == null)
            throw new NullPointerException("prototype"); 
        this.prototype = prototype.getDefaultInstanceForType();
        this.extensionRegistry = extensionRegistry;
    }
    
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] array;
        int offset, length = msg.readableBytes();
        if (msg.hasArray()) {
            array = msg.array();
            offset = msg.arrayOffset() + msg.readerIndex();
        } else {
            array = new byte[length];
            msg.getBytes(msg.readerIndex(), array, 0, length);
            offset = 0;
        } 
        if (this.extensionRegistry == null) {
            if (HAS_PARSER) {
                out.add(this.prototype.getParserForType().parseFrom(array, offset, length));
            } else {
                out.add(this.prototype.newBuilderForType().mergeFrom(array, offset, length).build());
            } 
        } else if (HAS_PARSER) {
            out.add(this.prototype.getParserForType().parseFrom(array, offset, length, (ExtensionRegistryLite)this.extensionRegistry));
        } else {
            out.add(this.prototype.newBuilderForType().mergeFrom(array, offset, length, (ExtensionRegistryLite)this.extensionRegistry).build());
        } 
    }
}
