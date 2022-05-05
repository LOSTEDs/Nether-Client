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
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

public class RandomAccessFileInputStream extends InputStream {
    private final boolean closeOnClose;
    
    private final RandomAccessFile randomAccessFile;
    
    public RandomAccessFileInputStream(RandomAccessFile file) {
        this(file, false);
    }
    
    public RandomAccessFileInputStream(RandomAccessFile file, boolean closeOnClose) {
        this.randomAccessFile = Objects.<RandomAccessFile>requireNonNull(file, "file");
        this.closeOnClose = closeOnClose;
    }
    
    public int available() throws IOException {
        long avail = availableLong();
        if (avail > 2147483647L)
            return Integer.MAX_VALUE; 
        return (int)avail;
    }
    
    public long availableLong() throws IOException {
        return this.randomAccessFile.length() - this.randomAccessFile.getFilePointer();
    }
    
    public void close() throws IOException {
        super.close();
        if (this.closeOnClose)
            this.randomAccessFile.close(); 
    }
    
    public RandomAccessFile getRandomAccessFile() {
        return this.randomAccessFile;
    }
    
    public boolean isCloseOnClose() {
        return this.closeOnClose;
    }
    
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }
    
    public int read(byte[] bytes) throws IOException {
        return this.randomAccessFile.read(bytes);
    }
    
    public int read(byte[] bytes, int offset, int length) throws IOException {
        return this.randomAccessFile.read(bytes, offset, length);
    }
    
    private void seek(long position) throws IOException {
        this.randomAccessFile.seek(position);
    }
    
    public long skip(long skipCount) throws IOException {
        if (skipCount <= 0L)
            return 0L; 
        long filePointer = this.randomAccessFile.getFilePointer();
        long fileLength = this.randomAccessFile.length();
        if (filePointer >= fileLength)
            return 0L; 
        long targetPos = filePointer + skipCount;
        long newPos = (targetPos > fileLength) ? (fileLength - 1L) : targetPos;
        if (newPos > 0L)
            seek(newPos); 
        return this.randomAccessFile.getFilePointer() - filePointer;
    }
}
