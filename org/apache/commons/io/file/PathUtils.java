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
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

public final class PathUtils {
    private static class RelativeSortedPaths {
        final boolean equals;
        
        final List<Path> relativeFileList1;
        
        final List<Path> relativeFileList2;
        
        private RelativeSortedPaths(Path dir1, Path dir2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
            List<Path> tmpRelativeDirList1 = null;
            List<Path> tmpRelativeDirList2 = null;
            List<Path> tmpRelativeFileList1 = null;
            List<Path> tmpRelativeFileList2 = null;
            if (dir1 == null && dir2 == null) {
                this.equals = true;
            } else if ((((dir1 == null) ? 1 : 0) ^ ((dir2 == null) ? 1 : 0)) != 0) {
                this.equals = false;
            } else {
                boolean parentDirExists1 = Files.exists(dir1, linkOptions);
                boolean parentDirExists2 = Files.exists(dir2, linkOptions);
                if (!parentDirExists1 || !parentDirExists2) {
                    this.equals = (!parentDirExists1 && !parentDirExists2);
                } else {
                    AccumulatorPathVisitor visitor1 = PathUtils.accumulate(dir1, maxDepth, fileVisitOptions);
                    AccumulatorPathVisitor visitor2 = PathUtils.accumulate(dir2, maxDepth, fileVisitOptions);
                    if (visitor1.getDirList().size() != visitor2.getDirList().size() || visitor1
                        .getFileList().size() != visitor2.getFileList().size()) {
                        this.equals = false;
                    } else {
                        tmpRelativeDirList1 = visitor1.relativizeDirectories(dir1, true, null);
                        tmpRelativeDirList2 = visitor2.relativizeDirectories(dir2, true, null);
                        if (!tmpRelativeDirList1.equals(tmpRelativeDirList2)) {
                            this.equals = false;
                        } else {
                            tmpRelativeFileList1 = visitor1.relativizeFiles(dir1, true, null);
                            tmpRelativeFileList2 = visitor2.relativizeFiles(dir2, true, null);
                            this.equals = tmpRelativeFileList1.equals(tmpRelativeFileList2);
                        } 
                    } 
                } 
            } 
            this.relativeFileList1 = tmpRelativeFileList1;
            this.relativeFileList2 = tmpRelativeFileList2;
        }
    }
    
    public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
    
    public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
    
    public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
    
    public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];
    
    private static AccumulatorPathVisitor accumulate(Path directory, int maxDepth, FileVisitOption[] fileVisitOptions) throws IOException {
        return visitFileTree(AccumulatorPathVisitor.withLongCounters(), directory, 
                toFileVisitOptionSet(fileVisitOptions), maxDepth);
    }
    
    public static Counters.PathCounters cleanDirectory(Path directory) throws IOException {
        return cleanDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
    }
    
    public static Counters.PathCounters cleanDirectory(Path directory, DeleteOption... options) throws IOException {
        return ((CleaningPathVisitor)visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), options, new String[0]), directory))
            .getPathCounters();
    }
    
    public static Counters.PathCounters copyDirectory(Path sourceDirectory, Path targetDirectory, CopyOption... copyOptions) throws IOException {
        return ((CopyDirectoryVisitor)visitFileTree(new CopyDirectoryVisitor(
                    Counters.longPathCounters(), sourceDirectory, targetDirectory, copyOptions), sourceDirectory))
            .getPathCounters();
    }
    
    public static Path copyFile(URL sourceFile, Path targetFile, CopyOption... copyOptions) throws IOException {
        try (InputStream inputStream = sourceFile.openStream()) {
            Files.copy(inputStream, targetFile, copyOptions);
            return targetFile;
        } 
    }
    
    public static Path copyFileToDirectory(Path sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
        return Files.copy(sourceFile, targetDirectory.resolve(sourceFile.getFileName()), copyOptions);
    }
    
    public static Path copyFileToDirectory(URL sourceFile, Path targetDirectory, CopyOption... copyOptions) throws IOException {
        try (InputStream inputStream = sourceFile.openStream()) {
            Files.copy(inputStream, targetDirectory.resolve(sourceFile.getFile()), copyOptions);
            return targetDirectory;
        } 
    }
    
    public static Counters.PathCounters countDirectory(Path directory) throws IOException {
        return ((CountingPathVisitor)visitFileTree(new CountingPathVisitor(Counters.longPathCounters()), directory)).getPathCounters();
    }
    
    public static Counters.PathCounters delete(Path path) throws IOException {
        return delete(path, EMPTY_DELETE_OPTION_ARRAY);
    }
    
    public static Counters.PathCounters delete(Path path, DeleteOption... options) throws IOException {
        return Files.isDirectory(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }) ? deleteDirectory(path, options) : 
            deleteFile(path, options);
    }
    
    public static Counters.PathCounters deleteDirectory(Path directory) throws IOException {
        return deleteDirectory(directory, EMPTY_DELETE_OPTION_ARRAY);
    }
    
    public static Counters.PathCounters deleteDirectory(Path directory, DeleteOption... options) throws IOException {
        return ((DeletingPathVisitor)visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), options, new String[0]), directory))
            .getPathCounters();
    }
    
    public static Counters.PathCounters deleteFile(Path file) throws IOException {
        return deleteFile(file, EMPTY_DELETE_OPTION_ARRAY);
    }
    
    public static Counters.PathCounters deleteFile(Path file, DeleteOption... options) throws IOException {
        if (Files.isDirectory(file, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }))
            throw new NoSuchFileException(file.toString()); 
        Counters.PathCounters pathCounts = Counters.longPathCounters();
        boolean exists = Files.exists(file, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
        long size = exists ? Files.size(file) : 0L;
        if (overrideReadOnly(options) && exists)
            setReadOnly(file, false, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }); 
        if (Files.deleteIfExists(file)) {
            pathCounts.getFileCounter().increment();
            pathCounts.getByteCounter().add(size);
        } 
        return pathCounts;
    }
    
    private static boolean overrideReadOnly(DeleteOption[] options) {
        if (options == null)
            return false; 
        for (DeleteOption deleteOption : options) {
            if (deleteOption == StandardDeleteOption.OVERRIDE_READ_ONLY)
                return true; 
        } 
        return false;
    }
    
    public static boolean directoryAndFileContentEquals(Path path1, Path path2) throws IOException {
        return directoryAndFileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }
    
    public static boolean directoryAndFileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions, FileVisitOption[] fileVisitOption) throws IOException {
        if (path1 == null && path2 == null)
            return true; 
        if ((((path1 == null) ? 1 : 0) ^ ((path2 == null) ? 1 : 0)) != 0)
            return false; 
        if (!Files.exists(path1, new LinkOption[0]) && !Files.exists(path2, new LinkOption[0]))
            return true; 
        RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path1, path2, 2147483647, linkOptions, fileVisitOption);
        if (!relativeSortedPaths.equals)
            return false; 
        List<Path> fileList1 = relativeSortedPaths.relativeFileList1;
        List<Path> fileList2 = relativeSortedPaths.relativeFileList2;
        for (Path path : fileList1) {
            int binarySearch = Collections.binarySearch((List)fileList2, path);
            if (binarySearch > -1) {
                if (!fileContentEquals(path1.resolve(path), path2.resolve(path), linkOptions, openOptions))
                    return false; 
                continue;
            } 
            throw new IllegalStateException("Unexpected mismatch.");
        } 
        return true;
    }
    
    public static boolean directoryContentEquals(Path path1, Path path2) throws IOException {
        return directoryContentEquals(path1, path2, 2147483647, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }
    
    public static boolean directoryContentEquals(Path path1, Path path2, int maxDepth, LinkOption[] linkOptions, FileVisitOption[] fileVisitOptions) throws IOException {
        return (new RelativeSortedPaths(path1, path2, maxDepth, linkOptions, fileVisitOptions)).equals;
    }
    
    public static boolean fileContentEquals(Path path1, Path path2) throws IOException {
        return fileContentEquals(path1, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
    }
    
    public static boolean fileContentEquals(Path path1, Path path2, LinkOption[] linkOptions, OpenOption[] openOptions) throws IOException {
        if (path1 == null && path2 == null)
            return true; 
        if ((((path1 == null) ? 1 : 0) ^ ((path2 == null) ? 1 : 0)) != 0)
            return false; 
        Path nPath1 = path1.normalize();
        Path nPath2 = path2.normalize();
        boolean path1Exists = Files.exists(nPath1, linkOptions);
        if (path1Exists != Files.exists(nPath2, linkOptions))
            return false; 
        if (!path1Exists)
            return true; 
        if (Files.isDirectory(nPath1, linkOptions))
            throw new IOException("Can't compare directories, only files: " + nPath1); 
        if (Files.isDirectory(nPath2, linkOptions))
            throw new IOException("Can't compare directories, only files: " + nPath2); 
        if (Files.size(nPath1) != Files.size(nPath2))
            return false; 
        if (path1.equals(path2))
            return true; 
        try(InputStream inputStream1 = Files.newInputStream(nPath1, openOptions); 
                InputStream inputStream2 = Files.newInputStream(nPath2, openOptions)) {
            return IOUtils.contentEquals(inputStream1, inputStream2);
        } 
    }
    
    public static List<AclEntry> getAclEntryList(Path sourcePath) throws IOException {
        AclFileAttributeView fileAttributeView = Files.<AclFileAttributeView>getFileAttributeView(sourcePath, AclFileAttributeView.class, new LinkOption[0]);
        return (fileAttributeView == null) ? null : fileAttributeView.getAcl();
    }
    
    public static boolean isEmpty(Path path) throws IOException {
        return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
    }
    
    public static boolean isEmptyDirectory(Path directory) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
            if (directoryStream.iterator().hasNext())
                return false; 
        } 
        return true;
    }
    
    public static boolean isEmptyFile(Path file) throws IOException {
        return (Files.size(file) <= 0L);
    }
    
    static List<Path> relativize(Collection<Path> collection, Path parent, boolean sort, Comparator<? super Path> comparator) {
        Stream<Path> stream = collection.stream().map(parent::relativize);
        if (sort)
            stream = (comparator == null) ? stream.sorted() : stream.sorted(comparator); 
        return stream.collect((Collector)Collectors.toList());
    }
    
    public static Path setReadOnly(Path path, boolean readOnly, LinkOption... options) throws IOException {
        DosFileAttributeView fileAttributeView = Files.<DosFileAttributeView>getFileAttributeView(path, DosFileAttributeView.class, options);
        if (fileAttributeView != null) {
            fileAttributeView.setReadOnly(readOnly);
            return path;
        } 
        PosixFileAttributeView posixFileAttributeView = Files.<PosixFileAttributeView>getFileAttributeView(path, PosixFileAttributeView.class, options);
        if (posixFileAttributeView != null) {
            PosixFileAttributes readAttributes = posixFileAttributeView.readAttributes();
            Set<PosixFilePermission> permissions = readAttributes.permissions();
            permissions.remove(PosixFilePermission.OWNER_WRITE);
            permissions.remove(PosixFilePermission.GROUP_WRITE);
            permissions.remove(PosixFilePermission.OTHERS_WRITE);
            return Files.setPosixFilePermissions(path, permissions);
        } 
        throw new IOException("No DosFileAttributeView or PosixFileAttributeView for " + path);
    }
    
    static Set<FileVisitOption> toFileVisitOptionSet(FileVisitOption... fileVisitOptions) {
        return (fileVisitOptions == null) ? EnumSet.<FileVisitOption>noneOf(FileVisitOption.class) : 
            (Set<FileVisitOption>)Arrays.<FileVisitOption>stream(fileVisitOptions).collect(Collectors.toSet());
    }
    
    public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, Path directory) throws IOException {
        Files.walkFileTree(directory, (FileVisitor<? super Path>)visitor);
        return visitor;
    }
    
    public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, Path start, Set<FileVisitOption> options, int maxDepth) throws IOException {
        Files.walkFileTree(start, options, maxDepth, (FileVisitor<? super Path>)visitor);
        return visitor;
    }
    
    public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, String first, String... more) throws IOException {
        return visitFileTree(visitor, Paths.get(first, more));
    }
    
    public static <T extends FileVisitor<? super Path>> T visitFileTree(T visitor, URI uri) throws IOException {
        return visitFileTree(visitor, Paths.get(uri));
    }
}
