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

package io.netty.handler.ssl;

import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.apache.tomcat.jni.Library;
import org.apache.tomcat.jni.SSL;

public final class OpenSsl {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(OpenSsl.class);
    
    private static final Throwable UNAVAILABILITY_CAUSE;
    
    static final String IGNORABLE_ERROR_PREFIX = "error:00000000:";
    
    static {
        Throwable cause = null;
        try {
            NativeLibraryLoader.load("netty-tcnative", SSL.class.getClassLoader());
            Library.initialize("provided");
            SSL.initialize(null);
        } catch (Throwable t) {
            cause = t;
            logger.debug("Failed to load netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable.", t);
        } 
        UNAVAILABILITY_CAUSE = cause;
    }
    
    public static boolean isAvailable() {
        return (UNAVAILABILITY_CAUSE == null);
    }
    
    public static void ensureAvailability() {
        if (UNAVAILABILITY_CAUSE != null)
            throw (Error)(new UnsatisfiedLinkError("failed to load the required native library")).initCause(UNAVAILABILITY_CAUSE); 
    }
    
    public static Throwable unavailabilityCause() {
        return UNAVAILABILITY_CAUSE;
    }
}
