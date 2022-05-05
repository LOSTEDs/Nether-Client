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

package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;
import java.util.Objects;

public class CharSequenceReader extends Reader implements Serializable {
    private static final long serialVersionUID = 3724187752191401220L;
    
    private final CharSequence charSequence;
    
    private int idx;
    
    private int mark;
    
    private final int start;
    
    private final Integer end;
    
    public CharSequenceReader(CharSequence charSequence) {
        this(charSequence, 0);
    }
    
    public CharSequenceReader(CharSequence charSequence, int start) {
        this(charSequence, start, 2147483647);
    }
    
    public CharSequenceReader(CharSequence charSequence, int start, int end) {
        if (start < 0)
            throw new IllegalArgumentException("Start index is less than zero: " + start); 
        if (end < start)
            throw new IllegalArgumentException("End index is less than start " + start + ": " + end); 
        this.charSequence = (charSequence != null) ? charSequence : "";
        this.start = start;
        this.end = Integer.valueOf(end);
        this.idx = start;
        this.mark = start;
    }
    
    private int start() {
        return Math.min(this.charSequence.length(), this.start);
    }
    
    private int end() {
        return Math.min(this.charSequence.length(), (this.end == null) ? Integer.MAX_VALUE : this.end.intValue());
    }
    
    public void close() {
        this.idx = this.start;
        this.mark = this.start;
    }
    
    public boolean ready() {
        return (this.idx < end());
    }
    
    public void mark(int readAheadLimit) {
        this.mark = this.idx;
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public int read() {
        if (this.idx >= end())
            return -1; 
        return this.charSequence.charAt(this.idx++);
    }
    
    public int read(char[] array, int offset, int length) {
        if (this.idx >= end())
            return -1; 
        Objects.requireNonNull(array, "array");
        if (length < 0 || offset < 0 || offset + length > array.length)
            throw new IndexOutOfBoundsException("Array Size=" + array.length + ", offset=" + offset + ", length=" + length); 
        if (this.charSequence instanceof String) {
            int j = Math.min(length, end() - this.idx);
            ((String)this.charSequence).getChars(this.idx, this.idx + j, array, offset);
            this.idx += j;
            return j;
        } 
        if (this.charSequence instanceof StringBuilder) {
            int j = Math.min(length, end() - this.idx);
            ((StringBuilder)this.charSequence).getChars(this.idx, this.idx + j, array, offset);
            this.idx += j;
            return j;
        } 
        if (this.charSequence instanceof StringBuffer) {
            int j = Math.min(length, end() - this.idx);
            ((StringBuffer)this.charSequence).getChars(this.idx, this.idx + j, array, offset);
            this.idx += j;
            return j;
        } 
        int count = 0;
        for (int i = 0; i < length; i++) {
            int c = read();
            if (c == -1)
                return count; 
            array[offset + i] = (char)c;
            count++;
        } 
        return count;
    }
    
    public void reset() {
        this.idx = this.mark;
    }
    
    public long skip(long n) {
        if (n < 0L)
            throw new IllegalArgumentException("Number of characters to skip is less than zero: " + n); 
        if (this.idx >= end())
            return 0L; 
        int dest = (int)Math.min(end(), this.idx + n);
        int count = dest - this.idx;
        this.idx = dest;
        return count;
    }
    
    public String toString() {
        CharSequence subSequence = this.charSequence.subSequence(start(), end());
        return subSequence.toString();
    }
}
