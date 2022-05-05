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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AccumulatorPathVisitor extends CountingPathVisitor {
    public static AccumulatorPathVisitor withBigIntegerCounters() {
        return new AccumulatorPathVisitor(Counters.bigIntegerPathCounters());
    }
    
    public static AccumulatorPathVisitor withLongCounters() {
        return new AccumulatorPathVisitor(Counters.longPathCounters());
    }
    
    private final List<Path> dirList = new ArrayList<>();
    
    private final List<Path> fileList = new ArrayList<>();
    
    public AccumulatorPathVisitor(Counters.PathCounters pathCounter) {
        super(pathCounter);
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true; 
        if (!super.equals(obj))
            return false; 
        if (!(obj instanceof AccumulatorPathVisitor))
            return false; 
        AccumulatorPathVisitor other = (AccumulatorPathVisitor)obj;
        return (Objects.equals(this.dirList, other.dirList) && Objects.equals(this.fileList, other.fileList));
    }
    
    public List<Path> getDirList() {
        return this.dirList;
    }
    
    public List<Path> getFileList() {
        return this.fileList;
    }
    
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = 31 * result + Objects.hash(new Object[] { this.dirList, this.fileList });
        return result;
    }
    
    public List<Path> relativizeDirectories(Path parent, boolean sort, Comparator<? super Path> comparator) {
        return PathUtils.relativize(getDirList(), parent, sort, comparator);
    }
    
    public List<Path> relativizeFiles(Path parent, boolean sort, Comparator<? super Path> comparator) {
        return PathUtils.relativize(getFileList(), parent, sort, comparator);
    }
    
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        (Files.isDirectory(file, new java.nio.file.LinkOption[0]) ? this.dirList : this.fileList).add(file.normalize());
        return super.visitFile(file, attributes);
    }
}
