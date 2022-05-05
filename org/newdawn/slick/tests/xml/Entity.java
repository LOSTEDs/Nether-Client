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

package org.newdawn.slick.tests.xml;

public class Entity {
    private float x;
    
    private float y;
    
    private Inventory invent;
    
    private Stats stats;
    
    private void add(Inventory inventory) {
        this.invent = inventory;
    }
    
    private void add(Stats stats) {
        this.stats = stats;
    }
    
    public void dump(String prefix) {
        System.out.println(prefix + "Entity " + this.x + "," + this.y);
        this.invent.dump(prefix + "\t");
        this.stats.dump(prefix + "\t");
    }
}
