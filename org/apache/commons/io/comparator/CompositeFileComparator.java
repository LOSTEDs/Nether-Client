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

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompositeFileComparator extends AbstractFileComparator implements Serializable {
    private static final Comparator<?>[] EMPTY_COMPARATOR_ARRAY = (Comparator<?>[])new Comparator[0];
    
    private static final long serialVersionUID = -2224170307287243428L;
    
    private static final Comparator<?>[] NO_COMPARATORS = (Comparator<?>[])new Comparator[0];
    
    private final Comparator<File>[] delegates;
    
    public CompositeFileComparator(Comparator<File>... delegates) {
        if (delegates == null) {
            this.delegates = (Comparator<File>[])NO_COMPARATORS;
        } else {
            this.delegates = (Comparator<File>[])new Comparator[delegates.length];
            System.arraycopy(delegates, 0, this.delegates, 0, delegates.length);
        } 
    }
    
    public CompositeFileComparator(Iterable<Comparator<File>> delegates) {
        if (delegates == null) {
            this.delegates = (Comparator<File>[])NO_COMPARATORS;
        } else {
            List<Comparator<File>> list = new ArrayList<>();
            for (Comparator<File> comparator : delegates)
                list.add(comparator); 
            this.delegates = (Comparator<File>[])list.<Comparator<?>>toArray(EMPTY_COMPARATOR_ARRAY);
        } 
    }
    
    public int compare(File file1, File file2) {
        int result = 0;
        for (Comparator<File> delegate : this.delegates) {
            result = delegate.compare(file1, file2);
            if (result != 0)
                break; 
        } 
        return result;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append('{');
        for (int i = 0; i < this.delegates.length; i++) {
            if (i > 0)
                builder.append(','); 
            builder.append(this.delegates[i]);
        } 
        builder.append('}');
        return builder.toString();
    }
}
