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

package io.netty.util.internal.logging;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.slf4j.LoggerFactory;

public class Slf4JLoggerFactory extends InternalLoggerFactory {
    public Slf4JLoggerFactory() {}
    
    Slf4JLoggerFactory(boolean failIfNOP) {
        assert failIfNOP;
        final StringBuffer buf = new StringBuffer();
        PrintStream err = System.err;
        try {
            System.setErr(new PrintStream(new OutputStream() {
                            public void write(int b) {
                                buf.append((char)b);
                            }
                        },  true, "US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        } 
        try {
            if (LoggerFactory.getILoggerFactory() instanceof org.slf4j.helpers.NOPLoggerFactory)
                throw new NoClassDefFoundError(buf.toString()); 
            err.print(buf);
            err.flush();
        } finally {
            System.setErr(err);
        } 
    }
    
    public InternalLogger newInstance(String name) {
        return new Slf4JLogger(LoggerFactory.getLogger(name));
    }
}