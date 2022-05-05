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

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class Log {
    private static boolean verbose = true;
    
    private static boolean forcedVerbose = false;
    
    private static final String forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";
    
    private static final String forceVerbosePropertyOnValue = "true";
    
    private static LogSystem logSystem = new DefaultLogSystem();
    
    public static void setLogSystem(LogSystem system) {
        logSystem = system;
    }
    
    public static void setVerbose(boolean v) {
        if (forcedVerbose)
            return; 
        verbose = v;
    }
    
    public static void checkVerboseLogSetting() {
        try {
            AccessController.doPrivileged(new PrivilegedAction() {
                        public Object run() {
                            String val = System.getProperty("org.newdawn.slick.forceVerboseLog");
                            if (val != null && val.equalsIgnoreCase("true"))
                                Log.setForcedVerboseOn(); 
                            return null;
                        }
                    });
        } catch (Throwable e) {}
    }
    
    public static void setForcedVerboseOn() {
        forcedVerbose = true;
        verbose = true;
    }
    
    public static void error(String message, Throwable e) {
        logSystem.error(message, e);
    }
    
    public static void error(Throwable e) {
        logSystem.error(e);
    }
    
    public static void error(String message) {
        logSystem.error(message);
    }
    
    public static void warn(String message) {
        logSystem.warn(message);
    }
    
    public static void warn(String message, Throwable e) {
        logSystem.warn(message, e);
    }
    
    public static void info(String message) {
        if (verbose || forcedVerbose)
            logSystem.info(message); 
    }
    
    public static void debug(String message) {
        if (verbose || forcedVerbose)
            logSystem.debug(message); 
    }
}
