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

public class TrueFileFilter implements IOFileFilter, Serializable {
    private static final long serialVersionUID = 8782512160909720199L;
    
    public static final IOFileFilter TRUE = new TrueFileFilter();
    
    public static final IOFileFilter INSTANCE = TRUE;
    
    public boolean accept(File file) {
        return true;
    }
    
    public boolean accept(File dir, String name) {
        return true;
    }
}
