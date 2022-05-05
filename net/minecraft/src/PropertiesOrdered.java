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

package net.minecraft.src;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesOrdered extends Properties {
    private Set<Object> keysOrdered = new LinkedHashSet();
    
    public synchronized Object put(Object p_put_1_, Object p_put_2_) {
        this.keysOrdered.add(p_put_1_);
        return super.put(p_put_1_, p_put_2_);
    }
    
    public Set<Object> keySet() {
        Set<Object> set = super.keySet();
        this.keysOrdered.retainAll(set);
        return Collections.unmodifiableSet(this.keysOrdered);
    }
    
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(keySet());
    }
}
