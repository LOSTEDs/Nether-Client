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

public enum StandardDeleteOption implements DeleteOption {
    OVERRIDE_READ_ONLY;
    
    public static boolean overrideReadOnly(DeleteOption[] options) {
        if (options == null || options.length == 0)
            return false; 
        for (DeleteOption deleteOption : options) {
            if (deleteOption == OVERRIDE_READ_ONLY)
                return true; 
        } 
        return false;
    }
}
