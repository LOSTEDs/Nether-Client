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

public class DefaultFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = 3260141861365313518L;
    
    public static final Comparator<File> DEFAULT_COMPARATOR = new DefaultFileComparator();
    
    public static final Comparator<File> DEFAULT_REVERSE = new ReverseFileComparator(DEFAULT_COMPARATOR);
    
    public int compare(File file1, File file2) {
        return file1.compareTo(file2);
    }
}
