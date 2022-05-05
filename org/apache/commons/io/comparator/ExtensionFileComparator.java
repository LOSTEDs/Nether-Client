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

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class ExtensionFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = 1928235200184222815L;
    
    public static final Comparator<File> EXTENSION_COMPARATOR = new ExtensionFileComparator();
    
    public static final Comparator<File> EXTENSION_REVERSE = new ReverseFileComparator(EXTENSION_COMPARATOR);
    
    public static final Comparator<File> EXTENSION_INSENSITIVE_COMPARATOR = new ExtensionFileComparator(IOCase.INSENSITIVE);
    
    public static final Comparator<File> EXTENSION_INSENSITIVE_REVERSE = new ReverseFileComparator(EXTENSION_INSENSITIVE_COMPARATOR);
    
    public static final Comparator<File> EXTENSION_SYSTEM_COMPARATOR = new ExtensionFileComparator(IOCase.SYSTEM);
    
    public static final Comparator<File> EXTENSION_SYSTEM_REVERSE = new ReverseFileComparator(EXTENSION_SYSTEM_COMPARATOR);
    
    private final IOCase caseSensitivity;
    
    public ExtensionFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }
    
    public ExtensionFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public int compare(File file1, File file2) {
        String suffix1 = FilenameUtils.getExtension(file1.getName());
        String suffix2 = FilenameUtils.getExtension(file2.getName());
        return this.caseSensitivity.checkCompareTo(suffix1, suffix2);
    }
    
    public String toString() {
        return super.toString() + "[caseSensitivity=" + this.caseSensitivity + "]";
    }
}
