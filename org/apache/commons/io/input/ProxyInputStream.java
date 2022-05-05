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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public abstract class ProxyInputStream extends FilterInputStream {
    public ProxyInputStream(InputStream proxy) {
        super(proxy);
    }
    
    public int read() throws IOException {
        try {
            beforeRead(1);
            int b = this.in.read();
            afterRead((b != -1) ? 1 : -1);
            return b;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        } 
    }
    
    public int read(byte[] bts) throws IOException {
        try {
            beforeRead(IOUtils.length(bts));
            int n = this.in.read(bts);
            afterRead(n);
            return n;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        } 
    }
    
    public int read(byte[] bts, int off, int len) throws IOException {
        try {
            beforeRead(len);
            int n = this.in.read(bts, off, len);
            afterRead(n);
            return n;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        } 
    }
    
    public long skip(long ln) throws IOException {
        try {
            return this.in.skip(ln);
        } catch (IOException e) {
            handleIOException(e);
            return 0L;
        } 
    }
    
    public int available() throws IOException {
        try {
            return super.available();
        } catch (IOException e) {
            handleIOException(e);
            return 0;
        } 
    }
    
    public void close() throws IOException {
        IOUtils.close(this.in, this::handleIOException);
    }
    
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
    }
    
    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (IOException e) {
            handleIOException(e);
        } 
    }
    
    public boolean markSupported() {
        return this.in.markSupported();
    }
    
    protected void beforeRead(int n) throws IOException {}
    
    protected void afterRead(int n) throws IOException {}
    
    protected void handleIOException(IOException e) throws IOException {
        throw e;
    }
}
