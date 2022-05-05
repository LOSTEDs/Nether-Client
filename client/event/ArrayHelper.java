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

package client.event;

import java.util.Iterator;

public class ArrayHelper<T> implements Iterable<T> {
    private T[] elements;
    
    public ArrayHelper(Object[] array) {
        this.elements = (T[])array;
    }
    
    public ArrayHelper() {
        this.elements = (T[])new Object[0];
    }
    
    public void add(T t) {
        if (t != null) {
            Object[] array = new Object[size() + 1];
            for (int i = 0; i < array.length; i++) {
                if (i < size()) {
                    array[i] = get(i);
                } else {
                    array[i] = t;
                } 
            } 
            set((T[])array);
        } 
    }
    
    public boolean contains(T t) {
        Object[] array;
        for (int lenght = (array = (Object[])array()).length, i = 0; i < lenght; i++) {
            T entry = (T)array[i];
            if (entry.equals(t))
                return true; 
        } 
        return false;
    }
    
    public void remove(T t) {
        if (contains(t)) {
            Object[] array = new Object[size() - 1];
            boolean b = true;
            for (int i = 0; i < size(); i++) {
                if (b && get(i).equals(t)) {
                    b = false;
                } else {
                    array[b ? i : (i - 1)] = get(i);
                } 
            } 
            set((T[])array);
        } 
    }
    
    public T[] array() {
        return this.elements;
    }
    
    public int size() {
        return (array()).length;
    }
    
    public void set(Object[] array) {
        this.elements = (T[])array;
    }
    
    public T get(int index) {
        return array()[index];
    }
    
    public void clear() {
        this.elements = (T[])new Object[0];
    }
    
    public boolean isEmpty() {
        return (size() == 0);
    }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
                private int index = 0;
                
                public boolean hasNext() {
                    return (this.index < ArrayHelper.this.size() && ArrayHelper.this.get(this.index) != null);
                }
                
                public T next() {
                    return ArrayHelper.this.get(this.index++);
                }
                
                public void remove() {
                    ArrayHelper.this.remove(ArrayHelper.this.get(this.index));
                }
            };
    }
}
