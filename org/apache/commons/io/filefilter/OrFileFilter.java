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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable {
    private static final long serialVersionUID = 5767770777065432721L;
    
    private final List<IOFileFilter> fileFilters;
    
    public OrFileFilter() {
        this.fileFilters = new ArrayList<>();
    }
    
    public OrFileFilter(List<IOFileFilter> fileFilters) {
        if (fileFilters == null) {
            this.fileFilters = new ArrayList<>();
        } else {
            this.fileFilters = new ArrayList<>(fileFilters);
        } 
    }
    
    public OrFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        if (filter1 == null || filter2 == null)
            throw new IllegalArgumentException("The filters must not be null"); 
        this.fileFilters = new ArrayList<>(2);
        addFileFilter(filter1);
        addFileFilter(filter2);
    }
    
    public void addFileFilter(IOFileFilter ioFileFilter) {
        this.fileFilters.add(ioFileFilter);
    }
    
    public List<IOFileFilter> getFileFilters() {
        return Collections.unmodifiableList(this.fileFilters);
    }
    
    public boolean removeFileFilter(IOFileFilter ioFileFilter) {
        return this.fileFilters.remove(ioFileFilter);
    }
    
    public void setFileFilters(List<IOFileFilter> fileFilters) {
        this.fileFilters.clear();
        this.fileFilters.addAll(fileFilters);
    }
    
    public boolean accept(File file) {
        for (IOFileFilter fileFilter : this.fileFilters) {
            if (fileFilter.accept(file))
                return true; 
        } 
        return false;
    }
    
    public boolean accept(File file, String name) {
        for (IOFileFilter fileFilter : this.fileFilters) {
            if (fileFilter.accept(file, name))
                return true; 
        } 
        return false;
    }
    
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.fileFilters != null)
            for (int i = 0; i < this.fileFilters.size(); i++) {
                if (i > 0)
                    buffer.append(","); 
                Object filter = this.fileFilters.get(i);
                buffer.append((filter == null) ? "null" : filter.toString());
            }  
        buffer.append(")");
        return buffer.toString();
    }
}
