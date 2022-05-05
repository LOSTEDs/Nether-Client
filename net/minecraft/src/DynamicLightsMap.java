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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicLightsMap {
    private Map<Integer, DynamicLight> map = new HashMap<>();
    
    private List<DynamicLight> list = new ArrayList<>();
    
    private boolean dirty = false;
    
    public DynamicLight put(int p_put_1_, DynamicLight p_put_2_) {
        DynamicLight dynamiclight = this.map.put(Integer.valueOf(p_put_1_), p_put_2_);
        setDirty();
        return dynamiclight;
    }
    
    public DynamicLight get(int p_get_1_) {
        return this.map.get(Integer.valueOf(p_get_1_));
    }
    
    public int size() {
        return this.map.size();
    }
    
    public DynamicLight remove(int p_remove_1_) {
        DynamicLight dynamiclight = this.map.remove(Integer.valueOf(p_remove_1_));
        if (dynamiclight != null)
            setDirty(); 
        return dynamiclight;
    }
    
    public void clear() {
        this.map.clear();
        setDirty();
    }
    
    private void setDirty() {
        this.dirty = true;
    }
    
    public List<DynamicLight> valueList() {
        if (this.dirty) {
            this.list.clear();
            this.list.addAll(this.map.values());
            this.dirty = false;
        } 
        return this.list;
    }
}
