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

package org.apache.commons.io.monitor;

import java.io.File;

public interface FileAlterationListener {
    void onStart(FileAlterationObserver paramFileAlterationObserver);
    
    void onDirectoryCreate(File paramFile);
    
    void onDirectoryChange(File paramFile);
    
    void onDirectoryDelete(File paramFile);
    
    void onFileCreate(File paramFile);
    
    void onFileChange(File paramFile);
    
    void onFileDelete(File paramFile);
    
    void onStop(FileAlterationObserver paramFileAlterationObserver);
}
