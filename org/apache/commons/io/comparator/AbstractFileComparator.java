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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class AbstractFileComparator implements Comparator<File> {
    public File[] sort(File... files) {
        if (files != null)
            Arrays.sort(files, this); 
        return files;
    }
    
    public List<File> sort(List<File> files) {
        if (files != null)
            Collections.sort(files, this); 
        return files;
    }
    
    public String toString() {
        return getClass().getSimpleName();
    }
}
