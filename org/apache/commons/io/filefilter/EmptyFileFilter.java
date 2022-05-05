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

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 3631422087512832211L;
    
    public static final IOFileFilter EMPTY = new EmptyFileFilter();
    
    public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);
    
    public boolean accept(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            return (files == null || files.length == 0);
        } 
        return (file.length() == 0L);
    }
}
