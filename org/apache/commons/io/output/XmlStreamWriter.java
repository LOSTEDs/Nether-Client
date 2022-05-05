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

package org.apache.commons.io.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.input.XmlStreamReader;

public class XmlStreamWriter extends Writer {
    private static final int BUFFER_SIZE = 8192;
    
    private final OutputStream out;
    
    private final String defaultEncoding;
    
    private StringWriter xmlPrologWriter = new StringWriter(8192);
    
    private Writer writer;
    
    private String encoding;
    
    public XmlStreamWriter(OutputStream out) {
        this(out, (String)null);
    }
    
    public XmlStreamWriter(OutputStream out, String defaultEncoding) {
        this.out = out;
        this.defaultEncoding = (defaultEncoding != null) ? defaultEncoding : "UTF-8";
    }
    
    public XmlStreamWriter(File file) throws FileNotFoundException {
        this(file, (String)null);
    }
    
    public XmlStreamWriter(File file, String defaultEncoding) throws FileNotFoundException {
        this(new FileOutputStream(file), defaultEncoding);
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    
    public void close() throws IOException {
        if (this.writer == null) {
            this.encoding = this.defaultEncoding;
            this.writer = new OutputStreamWriter(this.out, this.encoding);
            this.writer.write(this.xmlPrologWriter.toString());
        } 
        this.writer.close();
    }
    
    public void flush() throws IOException {
        if (this.writer != null)
            this.writer.flush(); 
    }
    
    private void detectEncoding(char[] cbuf, int off, int len) throws IOException {
        int size = len;
        StringBuffer xmlProlog = this.xmlPrologWriter.getBuffer();
        if (xmlProlog.length() + len > 8192)
            size = 8192 - xmlProlog.length(); 
        this.xmlPrologWriter.write(cbuf, off, size);
        if (xmlProlog.length() >= 5) {
            if (xmlProlog.substring(0, 5).equals("<?xml")) {
                int xmlPrologEnd = xmlProlog.indexOf("?>");
                if (xmlPrologEnd > 0) {
                    Matcher m = ENCODING_PATTERN.matcher(xmlProlog.substring(0, xmlPrologEnd));
                    if (m.find()) {
                        this.encoding = m.group(1).toUpperCase(Locale.ROOT);
                        this.encoding = this.encoding.substring(1, this.encoding.length() - 1);
                    } else {
                        this.encoding = this.defaultEncoding;
                    } 
                } else if (xmlProlog.length() >= 8192) {
                    this.encoding = this.defaultEncoding;
                } 
            } else {
                this.encoding = this.defaultEncoding;
            } 
            if (this.encoding != null) {
                this.xmlPrologWriter = null;
                this.writer = new OutputStreamWriter(this.out, this.encoding);
                this.writer.write(xmlProlog.toString());
                if (len > size)
                    this.writer.write(cbuf, off + size, len - size); 
            } 
        } 
    }
    
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (this.xmlPrologWriter != null) {
            detectEncoding(cbuf, off, len);
        } else {
            this.writer.write(cbuf, off, len);
        } 
    }
    
    static final Pattern ENCODING_PATTERN = XmlStreamReader.ENCODING_PATTERN;
}
