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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class JdkZlibDecoder extends ZlibDecoder {
    private static final int FHCRC = 2;
    
    private static final int FEXTRA = 4;
    
    private static final int FNAME = 8;
    
    private static final int FCOMMENT = 16;
    
    private static final int FRESERVED = 224;
    
    private Inflater inflater;
    
    private final byte[] dictionary;
    
    private final CRC32 crc;
    
    private enum GzipState {
        HEADER_START, HEADER_END, FLG_READ, XLEN_READ, SKIP_FNAME, SKIP_COMMENT, PROCESS_FHCRC, FOOTER_START;
    }
    
    private GzipState gzipState = GzipState.HEADER_START;
    
    private int flags = -1;
    
    private int xlen = -1;
    
    private volatile boolean finished;
    
    private boolean decideZlibOrNone;
    
    public JdkZlibDecoder() {
        this(ZlibWrapper.ZLIB, null);
    }
    
    public JdkZlibDecoder(byte[] dictionary) {
        this(ZlibWrapper.ZLIB, dictionary);
    }
    
    public JdkZlibDecoder(ZlibWrapper wrapper) {
        this(wrapper, null);
    }
    
    private JdkZlibDecoder(ZlibWrapper wrapper, byte[] dictionary) {
        if (wrapper == null)
            throw new NullPointerException("wrapper"); 
        switch (wrapper) {
            case FOOTER_START:
                this.inflater = new Inflater(true);
                this.crc = new CRC32();
                break;
            case HEADER_START:
                this.inflater = new Inflater(true);
                this.crc = null;
                break;
            case FLG_READ:
                this.inflater = new Inflater();
                this.crc = null;
                break;
            case XLEN_READ:
                this.decideZlibOrNone = true;
                this.crc = null;
                break;
            default:
                throw new IllegalArgumentException("Only GZIP or ZLIB is supported, but you used " + wrapper);
        } 
        this.dictionary = dictionary;
    }
    
    public boolean isClosed() {
        return this.finished;
    }
    
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (this.finished) {
            in.skipBytes(in.readableBytes());
            return;
        } 
        if (!in.isReadable())
            return; 
        if (this.decideZlibOrNone) {
            if (in.readableBytes() < 2)
                return; 
            boolean nowrap = !looksLikeZlib(in.getShort(0));
            this.inflater = new Inflater(nowrap);
            this.decideZlibOrNone = false;
        } 
        if (this.crc != null) {
            switch (this.gzipState) {
                case FOOTER_START:
                    if (readGZIPFooter(in))
                        this.finished = true; 
                    return;
            } 
            if (this.gzipState != GzipState.HEADER_END && 
                !readGZIPHeader(in))
                return; 
        } 
        int readableBytes = in.readableBytes();
        if (in.hasArray()) {
            this.inflater.setInput(in.array(), in.arrayOffset() + in.readerIndex(), in.readableBytes());
        } else {
            byte[] array = new byte[in.readableBytes()];
            in.getBytes(in.readerIndex(), array);
            this.inflater.setInput(array);
        } 
        int maxOutputLength = this.inflater.getRemaining() << 1;
        ByteBuf decompressed = ctx.alloc().heapBuffer(maxOutputLength);
        try {
            boolean readFooter = false;
            byte[] outArray = decompressed.array();
            while (!this.inflater.needsInput()) {
                int writerIndex = decompressed.writerIndex();
                int outIndex = decompressed.arrayOffset() + writerIndex;
                int length = decompressed.writableBytes();
                if (length == 0) {
                    out.add(decompressed);
                    decompressed = ctx.alloc().heapBuffer(maxOutputLength);
                    outArray = decompressed.array();
                    continue;
                } 
                int outputLength = this.inflater.inflate(outArray, outIndex, length);
                if (outputLength > 0) {
                    decompressed.writerIndex(writerIndex + outputLength);
                    if (this.crc != null)
                        this.crc.update(outArray, outIndex, outputLength); 
                } else if (this.inflater.needsDictionary()) {
                    if (this.dictionary == null)
                        throw new DecompressionException("decompression failure, unable to set dictionary as non was specified"); 
                    this.inflater.setDictionary(this.dictionary);
                } 
                if (this.inflater.finished()) {
                    if (this.crc == null) {
                        this.finished = true;
                        break;
                    } 
                    readFooter = true;
                    break;
                } 
            } 
            in.skipBytes(readableBytes - this.inflater.getRemaining());
            if (readFooter) {
                this.gzipState = GzipState.FOOTER_START;
                if (readGZIPFooter(in))
                    this.finished = true; 
            } 
        } catch (DataFormatException e) {
            throw new DecompressionException("decompression failure", e);
        } finally {
            if (decompressed.isReadable()) {
                out.add(decompressed);
            } else {
                decompressed.release();
            } 
        } 
    }
    
    protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved0(ctx);
        if (this.inflater != null)
            this.inflater.end(); 
    }
    
    private boolean readGZIPHeader(ByteBuf in) {
        int magic0;
        int magic1;
        int method;
        switch (this.gzipState) {
            case HEADER_START:
                if (in.readableBytes() < 10)
                    return false; 
                magic0 = in.readByte();
                magic1 = in.readByte();
                if (magic0 != 31)
                    throw new DecompressionException("Input is not in the GZIP format"); 
                this.crc.update(magic0);
                this.crc.update(magic1);
                method = in.readUnsignedByte();
                if (method != 8)
                    throw new DecompressionException("Unsupported compression method " + method + " in the GZIP header"); 
                this.crc.update(method);
                this.flags = in.readUnsignedByte();
                this.crc.update(this.flags);
                if ((this.flags & 0xE0) != 0)
                    throw new DecompressionException("Reserved flags are set in the GZIP header"); 
                this.crc.update(in.readByte());
                this.crc.update(in.readByte());
                this.crc.update(in.readByte());
                this.crc.update(in.readByte());
                this.crc.update(in.readUnsignedByte());
                this.crc.update(in.readUnsignedByte());
                this.gzipState = GzipState.FLG_READ;
            case FLG_READ:
                if ((this.flags & 0x4) != 0) {
                    if (in.readableBytes() < 2)
                        return false; 
                    int xlen1 = in.readUnsignedByte();
                    int xlen2 = in.readUnsignedByte();
                    this.crc.update(xlen1);
                    this.crc.update(xlen2);
                    this.xlen |= xlen1 << 8 | xlen2;
                } 
                this.gzipState = GzipState.XLEN_READ;
            case XLEN_READ:
                if (this.xlen != -1) {
                    if (in.readableBytes() < this.xlen)
                        return false; 
                    byte[] xtra = new byte[this.xlen];
                    in.readBytes(xtra);
                    this.crc.update(xtra);
                } 
                this.gzipState = GzipState.SKIP_FNAME;
            case SKIP_FNAME:
                if ((this.flags & 0x8) != 0) {
                    if (!in.isReadable())
                        return false; 
                    do {
                        int b = in.readUnsignedByte();
                        this.crc.update(b);
                        if (b == 0)
                            break; 
                    } while (in.isReadable());
                } 
                this.gzipState = GzipState.SKIP_COMMENT;
            case SKIP_COMMENT:
                if ((this.flags & 0x10) != 0) {
                    if (!in.isReadable())
                        return false; 
                    do {
                        int b = in.readUnsignedByte();
                        this.crc.update(b);
                        if (b == 0)
                            break; 
                    } while (in.isReadable());
                } 
                this.gzipState = GzipState.PROCESS_FHCRC;
            case PROCESS_FHCRC:
                if ((this.flags & 0x2) != 0) {
                    if (in.readableBytes() < 4)
                        return false; 
                    verifyCrc(in);
                } 
                this.crc.reset();
                this.gzipState = GzipState.HEADER_END;
            case HEADER_END:
                return true;
        } 
        throw new IllegalStateException();
    }
    
    private boolean readGZIPFooter(ByteBuf buf) {
        if (buf.readableBytes() < 8)
            return false; 
        verifyCrc(buf);
        int dataLength = 0;
        for (int i = 0; i < 4; i++)
            dataLength |= buf.readUnsignedByte() << i * 8; 
        int readLength = this.inflater.getTotalOut();
        if (dataLength != readLength)
            throw new DecompressionException("Number of bytes mismatch. Expected: " + dataLength + ", Got: " + readLength); 
        return true;
    }
    
    private void verifyCrc(ByteBuf in) {
        long crcValue = 0L;
        for (int i = 0; i < 4; i++)
            crcValue |= in.readUnsignedByte() << i * 8; 
        long readCrc = this.crc.getValue();
        if (crcValue != readCrc)
            throw new DecompressionException("CRC value missmatch. Expected: " + crcValue + ", Got: " + readCrc); 
    }
    
    private static boolean looksLikeZlib(short cmf_flg) {
        return ((cmf_flg & 0x7800) == 30720 && cmf_flg % 31 == 0);
    }
}
