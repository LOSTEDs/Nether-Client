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

package org.newdawn.slick.command;

public class MouseButtonControl implements Control {
    private int button;
    
    public MouseButtonControl(int button) {
        this.button = button;
    }
    
    public boolean equals(Object o) {
        if (o instanceof MouseButtonControl)
            return (((MouseButtonControl)o).button == this.button); 
        return false;
    }
    
    public int hashCode() {
        return this.button;
    }
}
