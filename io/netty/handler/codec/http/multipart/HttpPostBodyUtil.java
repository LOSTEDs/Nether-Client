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
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

final class HttpPostBodyUtil {
    public static final int chunkSize = 8096;
    
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    
    public static final String NAME = "name";
    
    public static final String FILENAME = "filename";
    
    public static final String FORM_DATA = "form-data";
    
    public static final String ATTACHMENT = "attachment";
    
    public static final String FILE = "file";
    
    public static final String MULTIPART_MIXED = "multipart/mixed";
    
    public static final Charset ISO_8859_1 = CharsetUtil.ISO_8859_1;
    
    public static final Charset US_ASCII = CharsetUtil.US_ASCII;
    
    public static final String DEFAULT_BINARY_CONTENT_TYPE = "application/octet-stream";
    
    public static final String DEFAULT_TEXT_CONTENT_TYPE = "text/plain";
    
    public enum TransferEncodingMechanism {
        BIT7("7bit"),
        BIT8("8bit"),
        BINARY("binary");
        
        private final String value;
        
        TransferEncodingMechanism(String value) {
            this.value = value;
        }
        
        TransferEncodingMechanism() {
            this.value = name();
        }
        
        public String value() {
            return this.value;
        }
        
        public String toString() {
            return this.value;
        }
    }
    
    static class SeekAheadNoBackArrayException extends Exception {
        private static final long serialVersionUID = -630418804938699495L;
    }
    
    static class SeekAheadOptimize {
        byte[] bytes;
        
        int readerIndex;
        
        int pos;
        
        int origPos;
        
        int limit;
        
        ByteBuf buffer;
        
        SeekAheadOptimize(ByteBuf buffer) throws HttpPostBodyUtil.SeekAheadNoBackArrayException {
            if (!buffer.hasArray())
                throw new HttpPostBodyUtil.SeekAheadNoBackArrayException(); 
            this.buffer = buffer;
            this.bytes = buffer.array();
            this.readerIndex = buffer.readerIndex();
            this.origPos = this.pos = buffer.arrayOffset() + this.readerIndex;
            this.limit = buffer.arrayOffset() + buffer.writerIndex();
        }
        
        void setReadPosition(int minus) {
            this.pos -= minus;
            this.readerIndex = getReadPosition(this.pos);
            this.buffer.readerIndex(this.readerIndex);
        }
        
        int getReadPosition(int index) {
            return index - this.origPos + this.readerIndex;
        }
        
        void clear() {
            this.buffer = null;
            this.bytes = null;
            this.limit = 0;
            this.pos = 0;
            this.readerIndex = 0;
        }
    }
    
    static int findNonWhitespace(String sb, int offset) {
        int result;
        for (result = offset; result < sb.length() && 
            Character.isWhitespace(sb.charAt(result)); result++);
        return result;
    }
    
    static int findWhitespace(String sb, int offset) {
        int result;
        for (result = offset; result < sb.length() && 
            !Character.isWhitespace(sb.charAt(result)); result++);
        return result;
    }
    
    static int findEndOfString(String sb) {
        int result;
        for (result = sb.length(); result > 0 && 
            Character.isWhitespace(sb.charAt(result - 1)); result--);
        return result;
    }
}
