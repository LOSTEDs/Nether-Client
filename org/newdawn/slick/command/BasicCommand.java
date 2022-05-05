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

public class BasicCommand implements Command {
    private String name;
    
    public BasicCommand(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public boolean equals(Object other) {
        if (other instanceof BasicCommand)
            return ((BasicCommand)other).name.equals(this.name); 
        return false;
    }
    
    public String toString() {
        return "[Command=" + this.name + "]";
    }
}
