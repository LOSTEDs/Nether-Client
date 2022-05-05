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
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SequenceReader extends Reader {
    private Reader reader;
    
    private Iterator<? extends Reader> readers;
    
    public SequenceReader(Iterable<? extends Reader> readers) {
        this.readers = ((Iterable<? extends Reader>)Objects.<Iterable<? extends Reader>>requireNonNull(readers, "readers")).iterator();
        this.reader = nextReader();
    }
    
    public SequenceReader(Reader... readers) {
        this(Arrays.asList(readers));
    }
    
    public void close() throws IOException {
        this.readers = null;
        this.reader = null;
    }
    
    private Reader nextReader() {
        return this.readers.hasNext() ? this.readers.next() : null;
    }
    
    public int read() throws IOException {
        int c = -1;
        while (this.reader != null) {
            c = this.reader.read();
            if (c == -1)
                this.reader = nextReader(); 
        } 
        return c;
    }
    
    public int read(char[] cbuf, int off, int len) throws IOException {
        Objects.requireNonNull(cbuf, "cbuf");
        if (len < 0 || off < 0 || off + len > cbuf.length)
            throw new IndexOutOfBoundsException("Array Size=" + cbuf.length + ", offset=" + off + ", length=" + len); 
        int count = 0;
        while (this.reader != null) {
            int readLen = this.reader.read(cbuf, off, len);
            if (readLen == -1) {
                this.reader = nextReader();
                continue;
            } 
            count += readLen;
            off += readLen;
            len -= readLen;
            if (len <= 0)
                break; 
        } 
        if (count > 0)
            return count; 
        return -1;
    }
}
