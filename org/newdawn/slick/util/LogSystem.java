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

package org.newdawn.slick.util;

public interface LogSystem {
    void error(String paramString, Throwable paramThrowable);
    
    void error(Throwable paramThrowable);
    
    void error(String paramString);
    
    void warn(String paramString);
    
    void warn(String paramString, Throwable paramThrowable);
    
    void info(String paramString);
    
    void debug(String paramString);
}
