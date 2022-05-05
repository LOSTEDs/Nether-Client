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

package org.apache.commons.io.output;

import java.io.OutputStream;

public class CountingOutputStream extends ProxyOutputStream {
    private long count = 0L;
    
    public CountingOutputStream(OutputStream out) {
        super(out);
    }
    
    protected synchronized void beforeWrite(int n) {
        this.count += n;
    }
    
    public int getCount() {
        long result = getByteCount();
        if (result > 2147483647L)
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int"); 
        return (int)result;
    }
    
    public int resetCount() {
        long result = resetByteCount();
        if (result > 2147483647L)
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int"); 
        return (int)result;
    }
    
    public synchronized long getByteCount() {
        return this.count;
    }
    
    public synchronized long resetByteCount() {
        long tmp = this.count;
        this.count = 0L;
        return tmp;
    }
}
