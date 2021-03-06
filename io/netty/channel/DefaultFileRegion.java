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

package io.netty.channel;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class DefaultFileRegion extends AbstractReferenceCounted implements FileRegion {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultFileRegion.class);
    
    private final FileChannel file;
    
    private final long position;
    
    private final long count;
    
    private long transfered;
    
    public DefaultFileRegion(FileChannel file, long position, long count) {
        if (file == null)
            throw new NullPointerException("file"); 
        if (position < 0L)
            throw new IllegalArgumentException("position must be >= 0 but was " + position); 
        if (count < 0L)
            throw new IllegalArgumentException("count must be >= 0 but was " + count); 
        this.file = file;
        this.position = position;
        this.count = count;
    }
    
    public long position() {
        return this.position;
    }
    
    public long count() {
        return this.count;
    }
    
    public long transfered() {
        return this.transfered;
    }
    
    public long transferTo(WritableByteChannel target, long position) throws IOException {
        long count = this.count - position;
        if (count < 0L || position < 0L)
            throw new IllegalArgumentException("position out of range: " + position + " (expected: 0 - " + (this.count - 1L) + ')'); 
        if (count == 0L)
            return 0L; 
        long written = this.file.transferTo(this.position + position, count, target);
        if (written > 0L)
            this.transfered += written; 
        return written;
    }
    
    protected void deallocate() {
        try {
            this.file.close();
        } catch (IOException e) {
            if (logger.isWarnEnabled())
                logger.warn("Failed to close a file.", e); 
        } 
    }
}
