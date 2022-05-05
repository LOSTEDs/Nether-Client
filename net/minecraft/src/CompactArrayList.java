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

public class CompactArrayList {
    private ArrayList list;
    
    private int initialCapacity;
    
    private float loadFactor;
    
    private int countValid;
    
    public CompactArrayList() {
        this(10, 0.75F);
    }
    
    public CompactArrayList(int p_i30_1_) {
        this(p_i30_1_, 0.75F);
    }
    
    public CompactArrayList(int p_i31_1_, float p_i31_2_) {
        this.list = null;
        this.initialCapacity = 0;
        this.loadFactor = 1.0F;
        this.countValid = 0;
        this.list = new ArrayList(p_i31_1_);
        this.initialCapacity = p_i31_1_;
        this.loadFactor = p_i31_2_;
    }
    
    public void add(int p_add_1_, Object p_add_2_) {
        if (p_add_2_ != null)
            this.countValid++; 
        this.list.add(p_add_1_, p_add_2_);
    }
    
    public boolean add(Object p_add_1_) {
        if (p_add_1_ != null)
            this.countValid++; 
        return this.list.add(p_add_1_);
    }
    
    public Object set(int p_set_1_, Object p_set_2_) {
        Object object = this.list.set(p_set_1_, p_set_2_);
        if (p_set_2_ != object) {
            if (object == null)
                this.countValid++; 
            if (p_set_2_ == null)
                this.countValid--; 
        } 
        return object;
    }
    
    public Object remove(int p_remove_1_) {
        Object object = this.list.remove(p_remove_1_);
        if (object != null)
            this.countValid--; 
        return object;
    }
    
    public void clear() {
        this.list.clear();
        this.countValid = 0;
    }
    
    public void compact() {
        if (this.countValid <= 0 && this.list.size() <= 0) {
            clear();
        } else if (this.list.size() > this.initialCapacity) {
            float f = this.countValid * 1.0F / this.list.size();
            if (f <= this.loadFactor) {
                int i = 0;
                for (int j = 0; j < this.list.size(); j++) {
                    Object object = this.list.get(j);
                    if (object != null) {
                        if (j != i)
                            this.list.set(i, object); 
                        i++;
                    } 
                } 
                for (int k = this.list.size() - 1; k >= i; k--)
                    this.list.remove(k); 
            } 
        } 
    }
    
    public boolean contains(Object p_contains_1_) {
        return this.list.contains(p_contains_1_);
    }
    
    public Object get(int p_get_1_) {
        return this.list.get(p_get_1_);
    }
    
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
    public int size() {
        return this.list.size();
    }
    
    public int getCountValid() {
        return this.countValid;
    }
}
