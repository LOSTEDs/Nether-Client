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

package net.minecraft.util;

public class Tuple<A, B> {
    private A a;
    
    private B b;
    
    public Tuple(A aIn, B bIn) {
        this.a = aIn;
        this.b = bIn;
    }
    
    public A getFirst() {
        return this.a;
    }
    
    public B getSecond() {
        return this.b;
    }
}
