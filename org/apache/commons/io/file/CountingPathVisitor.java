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

package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    
    private final Counters.PathCounters pathCounters;
    
    public static CountingPathVisitor withBigIntegerCounters() {
        return new CountingPathVisitor(Counters.bigIntegerPathCounters());
    }
    
    public static CountingPathVisitor withLongCounters() {
        return new CountingPathVisitor(Counters.longPathCounters());
    }
    
    public CountingPathVisitor(Counters.PathCounters pathCounter) {
        this.pathCounters = Objects.<Counters.PathCounters>requireNonNull(pathCounter, "pathCounter");
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true; 
        if (!(obj instanceof CountingPathVisitor))
            return false; 
        CountingPathVisitor other = (CountingPathVisitor)obj;
        return Objects.equals(this.pathCounters, other.pathCounters);
    }
    
    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }
    
    public int hashCode() {
        return Objects.hash(new Object[] { this.pathCounters });
    }
    
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        this.pathCounters.getDirectoryCounter().increment();
        return FileVisitResult.CONTINUE;
    }
    
    public String toString() {
        return this.pathCounters.toString();
    }
    
    protected void updateFileCounters(Path file, BasicFileAttributes attributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(attributes.size());
    }
    
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        if (Files.exists(file, new java.nio.file.LinkOption[0]))
            updateFileCounters(file, attributes); 
        return FileVisitResult.CONTINUE;
    }
}
