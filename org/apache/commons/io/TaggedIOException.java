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

import java.io.IOException;
import java.io.Serializable;

public class TaggedIOException extends IOExceptionWithCause {
    private static final long serialVersionUID = -6994123481142850163L;
    
    private final Serializable tag;
    
    public static boolean isTaggedWith(Throwable throwable, Object tag) {
        return (tag != null && throwable instanceof TaggedIOException && tag
            
            .equals(((TaggedIOException)throwable).tag));
    }
    
    public static void throwCauseIfTaggedWith(Throwable throwable, Object tag) throws IOException {
        if (isTaggedWith(throwable, tag))
            throw ((TaggedIOException)throwable).getCause(); 
    }
    
    public TaggedIOException(IOException original, Serializable tag) {
        super(original.getMessage(), original);
        this.tag = tag;
    }
    
    public Serializable getTag() {
        return this.tag;
    }
    
    public synchronized IOException getCause() {
        return (IOException)super.getCause();
    }
}
