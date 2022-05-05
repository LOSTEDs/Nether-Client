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
import org.newdawn.slick.geom.TexCoordGenerator;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class RadialGradientFill implements TexCoordGenerator {
    private Vector2f centre;
    
    private float radius;
    
    private Gradient gradient;
    
    private Shape shape;
    
    public RadialGradientFill(Shape shape, Transform trans, Gradient gradient) {
        this.gradient = gradient;
        this.radius = gradient.getR();
        float x = gradient.getX1();
        float y = gradient.getY1();
        float[] c = { x, y };
        gradient.getTransform().transform(c, 0, c, 0, 1);
        trans.transform(c, 0, c, 0, 1);
        float[] rt = { x, y - this.radius };
        gradient.getTransform().transform(rt, 0, rt, 0, 1);
        trans.transform(rt, 0, rt, 0, 1);
        this.centre = new Vector2f(c[0], c[1]);
        Vector2f dis = new Vector2f(rt[0], rt[1]);
        this.radius = dis.distance(this.centre);
    }
    
    public Vector2f getCoordFor(float x, float y) {
        float u = this.centre.distance(new Vector2f(x, y));
        u /= this.radius;
        if (u > 0.99F)
            u = 0.99F; 
        return new Vector2f(u, 0.0F);
    }
}
