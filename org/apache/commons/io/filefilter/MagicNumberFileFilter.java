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

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    
    private final byte[] magicNumbers;
    
    private final long byteOffset;
    
    public MagicNumberFileFilter(byte[] magicNumber) {
        this(magicNumber, 0L);
    }
    
    public MagicNumberFileFilter(String magicNumber) {
        this(magicNumber, 0L);
    }
    
    public MagicNumberFileFilter(String magicNumber, long offset) {
        if (magicNumber == null)
            throw new IllegalArgumentException("The magic number cannot be null"); 
        if (magicNumber.isEmpty())
            throw new IllegalArgumentException("The magic number must contain at least one byte"); 
        if (offset < 0L)
            throw new IllegalArgumentException("The offset cannot be negative"); 
        this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
        this.byteOffset = offset;
    }
    
    public MagicNumberFileFilter(byte[] magicNumber, long offset) {
        if (magicNumber == null)
            throw new IllegalArgumentException("The magic number cannot be null"); 
        if (magicNumber.length == 0)
            throw new IllegalArgumentException("The magic number must contain at least one byte"); 
        if (offset < 0L)
            throw new IllegalArgumentException("The offset cannot be negative"); 
        this.magicNumbers = new byte[magicNumber.length];
        System.arraycopy(magicNumber, 0, this.magicNumbers, 0, magicNumber.length);
        this.byteOffset = offset;
    }
    
    public boolean accept(File file) {
        if (file != null && file.isFile() && file.canRead())
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
                byte[] fileBytes = new byte[this.magicNumbers.length];
                randomAccessFile.seek(this.byteOffset);
                int read = randomAccessFile.read(fileBytes);
                if (read != this.magicNumbers.length)
                    return false; 
                return Arrays.equals(this.magicNumbers, fileBytes);
            } catch (IOException iOException) {} 
        return false;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("(");
        builder.append(new String(this.magicNumbers, Charset.defaultCharset()));
        builder.append(",");
        builder.append(this.byteOffset);
        builder.append(")");
        return builder.toString();
    }
}
