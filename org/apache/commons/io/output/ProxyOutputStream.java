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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class ProxyOutputStream extends FilterOutputStream {
    public ProxyOutputStream(OutputStream proxy) {
        super(proxy);
    }
    
    public void write(int idx) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(idx);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        } 
    }
    
    public void write(byte[] bts) throws IOException {
        try {
            int len = IOUtils.length(bts);
            beforeWrite(len);
            this.out.write(bts);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        } 
    }
    
    public void write(byte[] bts, int st, int end) throws IOException {
        try {
            beforeWrite(end);
            this.out.write(bts, st, end);
            afterWrite(end);
        } catch (IOException e) {
            handleIOException(e);
        } 
    }
    
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        } 
    }
    
    public void close() throws IOException {
        IOUtils.close(this.out, this::handleIOException);
    }
    
    protected void beforeWrite(int n) throws IOException {}
    
    protected void afterWrite(int n) throws IOException {}
    
    protected void handleIOException(IOException e) throws IOException {
        throw e;
    }
}
