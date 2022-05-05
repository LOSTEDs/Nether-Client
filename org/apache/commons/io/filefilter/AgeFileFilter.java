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

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class AgeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -2132740084016138541L;
    
    private final long cutoff;
    
    private final boolean acceptOlder;
    
    public AgeFileFilter(long cutoff) {
        this(cutoff, true);
    }
    
    public AgeFileFilter(long cutoff, boolean acceptOlder) {
        this.acceptOlder = acceptOlder;
        this.cutoff = cutoff;
    }
    
    public AgeFileFilter(Date cutoffDate) {
        this(cutoffDate, true);
    }
    
    public AgeFileFilter(Date cutoffDate, boolean acceptOlder) {
        this(cutoffDate.getTime(), acceptOlder);
    }
    
    public AgeFileFilter(File cutoffReference) {
        this(cutoffReference, true);
    }
    
    public AgeFileFilter(File cutoffReference, boolean acceptOlder) {
        this(cutoffReference.lastModified(), acceptOlder);
    }
    
    public boolean accept(File file) {
        boolean newer = FileUtils.isFileNewer(file, this.cutoff);
        return (this.acceptOlder != newer);
    }
    
    public String toString() {
        String condition = this.acceptOlder ? "<=" : ">";
        return super.toString() + "(" + condition + this.cutoff + ")";
    }
}
