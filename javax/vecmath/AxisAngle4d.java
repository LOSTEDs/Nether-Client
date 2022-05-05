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

package javax.vecmath;

import java.io.Serializable;

public class AxisAngle4d implements Serializable {
    public double x;
    
    public double y;
    
    public double z;
    
    public double angle;
    
    public AxisAngle4d(double x, double y, double z, double angle) {
        set(x, y, z, angle);
    }
    
    public AxisAngle4d(double[] a) {
        set(a);
    }
    
    public AxisAngle4d(AxisAngle4d a1) {
        set(a1);
    }
    
    public AxisAngle4d(AxisAngle4f a1) {
        set(a1);
    }
    
    public AxisAngle4d() {
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 1.0D;
        this.angle = 0.0D;
    }
    
    public AxisAngle4d(Vector3d axis, double angle) {
        this.x = axis.x;
        this.y = axis.y;
        this.z = axis.z;
        this.angle = angle;
    }
    
    public final void set(Vector3d axis, double angle) {
        this.x = axis.x;
        this.y = axis.y;
        this.z = axis.z;
        this.angle = angle;
    }
    
    public final void set(double x, double y, double z, double angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle = angle;
    }
    
    public final void set(double[] a) {
        this.x = a[0];
        this.y = a[1];
        this.z = a[2];
        this.angle = a[3];
    }
    
    public final void set(AxisAngle4d a1) {
        this.x = a1.x;
        this.y = a1.y;
        this.z = a1.z;
        this.angle = a1.angle;
    }
    
    public final void set(AxisAngle4f a1) {
        this.x = a1.x;
        this.y = a1.y;
        this.z = a1.z;
        this.angle = a1.angle;
    }
    
    public final void get(double[] a) {
        a[0] = this.x;
        a[1] = this.y;
        a[2] = this.z;
        a[3] = this.angle;
    }
    
    public final void set(Matrix4f m1) {
        setFromMat(m1.m00, m1.m01, m1.m02, m1.m10, m1.m11, m1.m12, m1.m20, m1.m21, m1.m22);
    }
    
    public final void set(Matrix4d m1) {
        setFromMat(m1.m00, m1.m01, m1.m02, m1.m10, m1.m11, m1.m12, m1.m20, m1.m21, m1.m22);
    }
    
    public final void set(Matrix3f m1) {
        setFromMat(m1.m00, m1.m01, m1.m02, m1.m10, m1.m11, m1.m12, m1.m20, m1.m21, m1.m22);
    }
    
    public final void set(Matrix3d m1) {
        setFromMat(m1.m00, m1.m01, m1.m02, m1.m10, m1.m11, m1.m12, m1.m20, m1.m21, m1.m22);
    }
    
    public final void set(Quat4f q1) {
        setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }
    
    public final void set(Quat4d q1) {
        setFromQuat(q1.x, q1.y, q1.z, q1.w);
    }
    
    private void setFromMat(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double cos = (m00 + m11 + m22 - 1.0D) * 0.5D;
        this.x = m21 - m12;
        this.y = m02 - m20;
        this.z = m10 - m01;
        double sin = 0.5D * Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.angle = Math.atan2(sin, cos);
    }
    
    private void setFromQuat(double x, double y, double z, double w) {
        double sin_a2 = Math.sqrt(x * x + y * y + z * z);
        this.angle = 2.0D * Math.atan2(sin_a2, w);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")";
    }
    
    public boolean equals(AxisAngle4d a1) {
        return (a1 != null && this.x == a1.x && this.y == a1.y && this.z == a1.z && this.angle == a1.angle);
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof AxisAngle4d && equals((AxisAngle4d)o1));
    }
    
    public boolean epsilonEquals(AxisAngle4d a1, double epsilon) {
        return (Math.abs(a1.x - this.x) <= epsilon && Math.abs(a1.y - this.y) <= epsilon && Math.abs(a1.z - this.z) <= epsilon && Math.abs(a1.angle - this.angle) <= epsilon);
    }
    
    public int hashCode() {
        long xbits = Double.doubleToLongBits(this.x);
        long ybits = Double.doubleToLongBits(this.y);
        long zbits = Double.doubleToLongBits(this.z);
        long abits = Double.doubleToLongBits(this.angle);
        return (int)(xbits ^ xbits >> 32L ^ ybits ^ ybits >> 32L ^ zbits ^ zbits >> 32L ^ abits ^ abits >> 32L);
    }
}
