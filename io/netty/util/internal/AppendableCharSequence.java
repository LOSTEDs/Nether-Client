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

package io.netty.util.internal;

import java.io.IOException;
import java.util.Arrays;

public final class AppendableCharSequence implements CharSequence, Appendable {
    private char[] chars;
    
    private int pos;
    
    public AppendableCharSequence(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length: " + length + " (length: >= 1)"); 
        this.chars = new char[length];
    }
    
    private AppendableCharSequence(char[] chars) {
        this.chars = chars;
        this.pos = chars.length;
    }
    
    public int length() {
        return this.pos;
    }
    
    public char charAt(int index) {
        if (index > this.pos)
            throw new IndexOutOfBoundsException(); 
        return this.chars[index];
    }
    
    public AppendableCharSequence subSequence(int start, int end) {
        return new AppendableCharSequence(Arrays.copyOfRange(this.chars, start, end));
    }
    
    public AppendableCharSequence append(char c) {
        if (this.pos == this.chars.length) {
            char[] old = this.chars;
            int len = old.length << 1;
            if (len < 0)
                throw new IllegalStateException(); 
            this.chars = new char[len];
            System.arraycopy(old, 0, this.chars, 0, old.length);
        } 
        this.chars[this.pos++] = c;
        return this;
    }
    
    public AppendableCharSequence append(CharSequence csq) {
        return append(csq, 0, csq.length());
    }
    
    public AppendableCharSequence append(CharSequence csq, int start, int end) {
        if (csq.length() < end)
            throw new IndexOutOfBoundsException(); 
        int length = end - start;
        if (length > this.chars.length - this.pos)
            this.chars = expand(this.chars, this.pos + length, this.pos); 
        if (csq instanceof AppendableCharSequence) {
            AppendableCharSequence seq = (AppendableCharSequence)csq;
            char[] src = seq.chars;
            System.arraycopy(src, start, this.chars, this.pos, length);
            this.pos += length;
            return this;
        } 
        for (int i = start; i < end; i++)
            this.chars[this.pos++] = csq.charAt(i); 
        return this;
    }
    
    public void reset() {
        this.pos = 0;
    }
    
    public String toString() {
        return new String(this.chars, 0, this.pos);
    }
    
    public String substring(int start, int end) {
        int length = end - start;
        if (start > this.pos || length > this.pos)
            throw new IndexOutOfBoundsException(); 
        return new String(this.chars, start, length);
    }
    
    private static char[] expand(char[] array, int neededSpace, int size) {
        int newCapacity = array.length;
        do {
            newCapacity <<= 1;
            if (newCapacity < 0)
                throw new IllegalStateException(); 
        } while (neededSpace > newCapacity);
        char[] newArray = new char[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }
}
