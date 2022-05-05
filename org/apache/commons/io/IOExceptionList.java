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
import java.util.Collections;
import java.util.List;

public class IOExceptionList extends IOException {
    private static final long serialVersionUID = 1L;
    
    private final List<? extends Throwable> causeList;
    
    public IOExceptionList(List<? extends Throwable> causeList) {
        super(String.format("%,d exceptions: %s", new Object[] { Integer.valueOf((causeList == null) ? 0 : causeList.size()), causeList }), (causeList == null) ? null : causeList
                .get(0));
        this.causeList = (causeList == null) ? Collections.<Throwable>emptyList() : causeList;
    }
    
    public <T extends Throwable> List<T> getCauseList() {
        return (List)this.causeList;
    }
    
    public <T extends Throwable> T getCause(int index) {
        return (T)this.causeList.get(index);
    }
    
    public <T extends Throwable> T getCause(int index, Class<T> clazz) {
        return (T)this.causeList.get(index);
    }
    
    public <T extends Throwable> List<T> getCauseList(Class<T> clazz) {
        return (List)this.causeList;
    }
}
