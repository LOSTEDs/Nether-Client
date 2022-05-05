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
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Objects;

public class CopyDirectoryVisitor extends CountingPathVisitor {
    private static final CopyOption[] EMPTY_COPY_OPTIONS = new CopyOption[0];
    
    private final CopyOption[] copyOptions;
    
    private final Path sourceDirectory;
    
    private final Path targetDirectory;
    
    public CopyDirectoryVisitor(Counters.PathCounters pathCounter, Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) {
        super(pathCounter);
        this.sourceDirectory = sourceDirectory;
        this.targetDirectory = targetDirectory;
        this.copyOptions = (copyOptions == null) ? EMPTY_COPY_OPTIONS : (CopyOption[])copyOptions.clone();
    }
    
    protected void copy(Path sourceFile, Path targetFile) throws IOException {
        Files.copy(sourceFile, targetFile, this.copyOptions);
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true; 
        if (!super.equals(obj))
            return false; 
        if (getClass() != obj.getClass())
            return false; 
        CopyDirectoryVisitor other = (CopyDirectoryVisitor)obj;
        return (Arrays.equals((Object[])this.copyOptions, (Object[])other.copyOptions) && Objects.equals(this.sourceDirectory, other.sourceDirectory) && 
            Objects.equals(this.targetDirectory, other.targetDirectory));
    }
    
    public CopyOption[] getCopyOptions() {
        return (CopyOption[])this.copyOptions.clone();
    }
    
    public Path getSourceDirectory() {
        return this.sourceDirectory;
    }
    
    public Path getTargetDirectory() {
        return this.targetDirectory;
    }
    
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode((Object[])this.copyOptions);
        result = 31 * result + Objects.hash(new Object[] { this.sourceDirectory, this.targetDirectory });
        return result;
    }
    
    public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) throws IOException {
        Path newTargetDir = this.targetDirectory.resolve(this.sourceDirectory.relativize(directory));
        if (Files.notExists(newTargetDir, new java.nio.file.LinkOption[0]))
            Files.createDirectory(newTargetDir, (FileAttribute<?>[])new FileAttribute[0]); 
        return super.preVisitDirectory(directory, attributes);
    }
    
    public FileVisitResult visitFile(Path sourceFile, BasicFileAttributes attributes) throws IOException {
        Path targetFile = this.targetDirectory.resolve(this.sourceDirectory.relativize(sourceFile));
        copy(sourceFile, targetFile);
        return super.visitFile(targetFile, attributes);
    }
}
