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

package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.ArrayList;

public class NavPath {
    private ArrayList links = new ArrayList();
    
    public void push(Link link) {
        this.links.add(link);
    }
    
    public int length() {
        return this.links.size();
    }
    
    public float getX(int step) {
        return ((Link)this.links.get(step)).getX();
    }
    
    public float getY(int step) {
        return ((Link)this.links.get(step)).getY();
    }
    
    public String toString() {
        return "[Path length=" + length() + "]";
    }
    
    public void remove(int i) {
        this.links.remove(i);
    }
}
