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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private ByteArrayOutputStream memoryOutputStream;
    
    private OutputStream currentOutputStream;
    
    private File outputFile;
    
    private final String prefix;
    
    private final String suffix;
    
    private final File directory;
    
    private boolean closed = false;
    
    public DeferredFileOutputStream(int threshold, File outputFile) {
        this(threshold, outputFile, null, null, null, 1024);
    }
    
    public DeferredFileOutputStream(int threshold, int initialBufferSize, File outputFile) {
        this(threshold, outputFile, null, null, null, initialBufferSize);
        if (initialBufferSize < 0)
            throw new IllegalArgumentException("Initial buffer size must be atleast 0."); 
    }
    
    public DeferredFileOutputStream(int threshold, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, 1024);
        if (prefix == null)
            throw new IllegalArgumentException("Temporary file prefix is missing"); 
    }
    
    public DeferredFileOutputStream(int threshold, int initialBufferSize, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, initialBufferSize);
        if (prefix == null)
            throw new IllegalArgumentException("Temporary file prefix is missing"); 
        if (initialBufferSize < 0)
            throw new IllegalArgumentException("Initial buffer size must be atleast 0."); 
    }
    
    private DeferredFileOutputStream(int threshold, File outputFile, String prefix, String suffix, File directory, int initialBufferSize) {
        super(threshold);
        this.outputFile = outputFile;
        this.prefix = prefix;
        this.suffix = suffix;
        this.directory = directory;
        this.memoryOutputStream = new ByteArrayOutputStream(initialBufferSize);
        this.currentOutputStream = this.memoryOutputStream;
    }
    
    protected OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }
    
    protected void thresholdReached() throws IOException {
        if (this.prefix != null)
            this.outputFile = File.createTempFile(this.prefix, this.suffix, this.directory); 
        FileUtils.forceMkdirParent(this.outputFile);
        FileOutputStream fos = new FileOutputStream(this.outputFile);
        try {
            this.memoryOutputStream.writeTo(fos);
        } catch (IOException e) {
            fos.close();
            throw e;
        } 
        this.currentOutputStream = fos;
        this.memoryOutputStream = null;
    }
    
    public boolean isInMemory() {
        return !isThresholdExceeded();
    }
    
    public byte[] getData() {
        if (this.memoryOutputStream != null)
            return this.memoryOutputStream.toByteArray(); 
        return null;
    }
    
    public File getFile() {
        return this.outputFile;
    }
    
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }
    
    public void writeTo(OutputStream out) throws IOException {
        if (!this.closed)
            throw new IOException("Stream not closed"); 
        if (isInMemory()) {
            this.memoryOutputStream.writeTo(out);
        } else {
            try (FileInputStream fis = new FileInputStream(this.outputFile)) {
                IOUtils.copy(fis, out);
            } 
        } 
    }
}
