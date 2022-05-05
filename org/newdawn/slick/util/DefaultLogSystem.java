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

import java.io.PrintStream;
import java.util.Date;

public class DefaultLogSystem implements LogSystem {
    public static PrintStream out = System.out;
    
    public void error(String message, Throwable e) {
        error(message);
        error(e);
    }
    
    public void error(Throwable e) {
        out.println(new Date() + " ERROR:" + e.getMessage());
        e.printStackTrace(out);
    }
    
    public void error(String message) {
        out.println(new Date() + " ERROR:" + message);
    }
    
    public void warn(String message) {
        out.println(new Date() + " WARN:" + message);
    }
    
    public void info(String message) {
        out.println(new Date() + " INFO:" + message);
    }
    
    public void debug(String message) {
        out.println(new Date() + " DEBUG:" + message);
    }
    
    public void warn(String message, Throwable e) {
        warn(message);
        e.printStackTrace(out);
    }
}
