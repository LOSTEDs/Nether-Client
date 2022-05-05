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

package org.apache.commons.io.input;

import java.io.IOException;

public class XmlStreamReaderException extends IOException {
    private static final long serialVersionUID = 1L;
    
    private final String bomEncoding;
    
    private final String xmlGuessEncoding;
    
    private final String xmlEncoding;
    
    private final String contentTypeMime;
    
    private final String contentTypeEncoding;
    
    public XmlStreamReaderException(String msg, String bomEnc, String xmlGuessEnc, String xmlEnc) {
        this(msg, null, null, bomEnc, xmlGuessEnc, xmlEnc);
    }
    
    public XmlStreamReaderException(String msg, String ctMime, String ctEnc, String bomEnc, String xmlGuessEnc, String xmlEnc) {
        super(msg);
        this.contentTypeMime = ctMime;
        this.contentTypeEncoding = ctEnc;
        this.bomEncoding = bomEnc;
        this.xmlGuessEncoding = xmlGuessEnc;
        this.xmlEncoding = xmlEnc;
    }
    
    public String getBomEncoding() {
        return this.bomEncoding;
    }
    
    public String getXmlGuessEncoding() {
        return this.xmlGuessEncoding;
    }
    
    public String getXmlEncoding() {
        return this.xmlEncoding;
    }
    
    public String getContentTypeMime() {
        return this.contentTypeMime;
    }
    
    public String getContentTypeEncoding() {
        return this.contentTypeEncoding;
    }
}
