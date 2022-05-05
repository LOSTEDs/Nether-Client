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

package org.newdawn.slick.svg;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Figure {
    public static final int ELLIPSE = 1;
    
    public static final int LINE = 2;
    
    public static final int RECTANGLE = 3;
    
    public static final int PATH = 4;
    
    public static final int POLYGON = 5;
    
    private int type;
    
    private Shape shape;
    
    private NonGeometricData data;
    
    private Transform transform;
    
    public Figure(int type, Shape shape, NonGeometricData data, Transform transform) {
        this.shape = shape;
        this.data = data;
        this.type = type;
        this.transform = transform;
    }
    
    public Transform getTransform() {
        return this.transform;
    }
    
    public int getType() {
        return this.type;
    }
    
    public Shape getShape() {
        return this.shape;
    }
    
    public NonGeometricData getData() {
        return this.data;
    }
}
