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

package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LineIterator implements Iterator<String>, Closeable {
    private final BufferedReader bufferedReader;
    
    private String cachedLine;
    
    private boolean finished = false;
    
    public LineIterator(Reader reader) throws IllegalArgumentException {
        if (reader == null)
            throw new IllegalArgumentException("Reader must not be null"); 
        if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader)reader;
        } else {
            this.bufferedReader = new BufferedReader(reader);
        } 
    }
    
    public boolean hasNext() {
        if (this.cachedLine != null)
            return true; 
        if (this.finished)
            return false; 
        try {
            while (true) {
                String line = this.bufferedReader.readLine();
                if (line == null) {
                    this.finished = true;
                    return false;
                } 
                if (isValidLine(line)) {
                    this.cachedLine = line;
                    return true;
                } 
            } 
        } catch (IOException ioe) {
            IOUtils.closeQuietly(this, e -> ioe.addSuppressed(e));
            throw new IllegalStateException(ioe);
        } 
    }
    
    protected boolean isValidLine(String line) {
        return true;
    }
    
    public String next() {
        return nextLine();
    }
    
    public String nextLine() {
        if (!hasNext())
            throw new NoSuchElementException("No more lines"); 
        String currentLine = this.cachedLine;
        this.cachedLine = null;
        return currentLine;
    }
    
    public void close() throws IOException {
        this.finished = true;
        this.cachedLine = null;
        IOUtils.close(this.bufferedReader);
    }
    
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }
    
    @Deprecated
    public static void closeQuietly(LineIterator iterator) {
        IOUtils.closeQuietly(iterator);
    }
}
