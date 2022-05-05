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

public class Link {
    private float px;
    
    private float py;
    
    private Space target;
    
    public Link(float px, float py, Space target) {
        this.px = px;
        this.py = py;
        this.target = target;
    }
    
    public float distance2(float tx, float ty) {
        float dx = tx - this.px;
        float dy = ty - this.py;
        return dx * dx + dy * dy;
    }
    
    public float getX() {
        return this.px;
    }
    
    public float getY() {
        return this.py;
    }
    
    public Space getTarget() {
        return this.target;
    }
}
