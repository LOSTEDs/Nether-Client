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

package io.netty.handler.codec;

import io.netty.util.Signal;

public class DecoderResult {
    protected static final Signal SIGNAL_UNFINISHED = Signal.valueOf(DecoderResult.class.getName() + ".UNFINISHED");
    
    protected static final Signal SIGNAL_SUCCESS = Signal.valueOf(DecoderResult.class.getName() + ".SUCCESS");
    
    public static final DecoderResult UNFINISHED = new DecoderResult((Throwable)SIGNAL_UNFINISHED);
    
    public static final DecoderResult SUCCESS = new DecoderResult((Throwable)SIGNAL_SUCCESS);
    
    private final Throwable cause;
    
    public static DecoderResult failure(Throwable cause) {
        if (cause == null)
            throw new NullPointerException("cause"); 
        return new DecoderResult(cause);
    }
    
    protected DecoderResult(Throwable cause) {
        if (cause == null)
            throw new NullPointerException("cause"); 
        this.cause = cause;
    }
    
    public boolean isFinished() {
        return (this.cause != SIGNAL_UNFINISHED);
    }
    
    public boolean isSuccess() {
        return (this.cause == SIGNAL_SUCCESS);
    }
    
    public boolean isFailure() {
        return (this.cause != SIGNAL_SUCCESS && this.cause != SIGNAL_UNFINISHED);
    }
    
    public Throwable cause() {
        if (isFailure())
            return this.cause; 
        return null;
    }
    
    public String toString() {
        if (isFinished()) {
            if (isSuccess())
                return "success"; 
            String cause = cause().toString();
            StringBuilder buf = new StringBuilder(cause.length() + 17);
            buf.append("failure(");
            buf.append(cause);
            buf.append(')');
            return buf.toString();
        } 
        return "unfinished";
    }
}
