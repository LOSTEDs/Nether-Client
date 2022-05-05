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

import java.io.File;

@Deprecated
public class FileCleaner {
    static final FileCleaningTracker theInstance = new FileCleaningTracker();
    
    @Deprecated
    public static void track(File file, Object marker) {
        theInstance.track(file, marker);
    }
    
    @Deprecated
    public static void track(File file, Object marker, FileDeleteStrategy deleteStrategy) {
        theInstance.track(file, marker, deleteStrategy);
    }
    
    @Deprecated
    public static void track(String path, Object marker) {
        theInstance.track(path, marker);
    }
    
    @Deprecated
    public static void track(String path, Object marker, FileDeleteStrategy deleteStrategy) {
        theInstance.track(path, marker, deleteStrategy);
    }
    
    @Deprecated
    public static int getTrackCount() {
        return theInstance.getTrackCount();
    }
    
    @Deprecated
    public static synchronized void exitWhenFinished() {
        theInstance.exitWhenFinished();
    }
    
    public static FileCleaningTracker getInstance() {
        return theInstance;
    }
}
