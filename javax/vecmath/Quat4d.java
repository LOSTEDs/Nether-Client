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

public class Quat4d extends Tuple4d implements Serializable {
    public Quat4d(double x, double y, double z, double w) {
        super(x, y, z, w);
    }
    
    public Quat4d(double[] q) {
        super(q);
    }
    
    public Quat4d(Quat4d q1) {
        super(q1);
    }
    
    public Quat4d(Quat4f q1) {
        super(q1);
    }
    
    public Quat4d(Tuple4d t1) {
        super(t1);
    }
    
    public Quat4d(Tuple4f t1) {
        super(t1);
    }
    
    public Quat4d() {}
    
    public final void conjugate(Quat4d q1) {
        this.x = -q1.x;
        this.y = -q1.y;
        this.z = -q1.z;
        this.w = q1.w;
    }
    
    public final void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }
    
    public final void mul(Quat4d q1, Quat4d q2) {
        set(q1.x * q2.w + q1.w * q2.x + q1.y * q2.z - q1.z * q2.y, q1.y * q2.w + q1.w * q2.y + q1.z * q2.x - q1.x * q2.z, q1.z * q2.w + q1.w * q2.z + q1.x * q2.y - q1.y * q2.x, q1.w * q2.w - q1.x * q2.x - q1.y * q2.y - q1.z * q2.z);
    }
    
    public final void mul(Quat4d q1) {
        set(this.x * q1.w + this.w * q1.x + this.y * q1.z - this.z * q1.y, this.y * q1.w + this.w * q1.y + this.z * q1.x - this.x * q1.z, this.z * q1.w + this.w * q1.z + this.x * q1.y - this.y * q1.x, this.w * q1.w - this.x * q1.x - this.y * q1.y - this.z * q1.z);
    }
    
    public final void mulInverse(Quat4d q1, Quat4d q2) {
        double n = norm();
        n = (n == 0.0D) ? n : (1.0D / n);
        set((q1.x * q2.w - q1.w * q2.x - q1.y * q2.z + q1.z * q2.y) * n, (q1.y * q2.w - q1.w * q2.y - q1.z * q2.x + q1.x * q2.z) * n, (q1.z * q2.w - q1.w * q2.z - q1.x * q2.y + q1.y * q2.x) * n, (q1.w * q2.w + q1.x * q2.x + q1.y * q2.y + q1.z * q2.z) * n);
    }
    
    public final void mulInverse(Quat4d q1) {
        double n = norm();
        n = (n == 0.0D) ? n : (1.0D / n);
        set((this.x * q1.w - this.w * q1.x - this.y * q1.z + this.z * q1.y) * n, (this.y * q1.w - this.w * q1.y - this.z * q1.x + this.x * q1.z) * n, (this.z * q1.w - this.w * q1.z - this.x * q1.y + this.y * q1.x) * n, (this.w * q1.w + this.x * q1.x + this.y * q1.y + this.z * q1.z) * n);
    }
    
    private final double norm() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }
    
    public final void inverse(Quat4d q1) {
        double n = q1.norm();
        this.x = -q1.x / n;
        this.y = -q1.y / n;
        this.z = -q1.z / n;
        q1.w /= n;
    }
    
    public final void inverse() {
        double n = norm();
        this.x = -this.x / n;
        this.y = -this.y / n;
        this.z = -this.z / n;
        this.w /= n;
    }
    
    public final void normalize(Quat4d q1) {
        double n = Math.sqrt(q1.norm());
        q1.x /= n;
        q1.y /= n;
        q1.z /= n;
        q1.w /= n;
    }
    
    public final void normalize() {
        double n = Math.sqrt(norm());
        this.x /= n;
        this.y /= n;
        this.z /= n;
        this.w /= n;
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
    
    public final void set(AxisAngle4f a1) {
        this.x = a1.x;
        this.y = a1.y;
        this.z = a1.z;
        double n = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double s = Math.sin(0.5D * a1.angle) / n;
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w = Math.cos(0.5D * a1.angle);
    }
    
    public final void set(AxisAngle4d a1) {
        this.x = a1.x;
        this.y = a1.y;
        this.z = a1.z;
        double n = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double s = Math.sin(0.5D * a1.angle) / n;
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w = Math.cos(0.5D * a1.angle);
    }
    
    public final void interpolate(Quat4d q1, double alpha) {
        normalize();
        double n1 = Math.sqrt(q1.norm());
        double x1 = q1.x / n1;
        double y1 = q1.y / n1;
        double z1 = q1.z / n1;
        double w1 = q1.w / n1;
        double t = this.x * x1 + this.y * y1 + this.z * z1 + this.w * w1;
        if (1.0D <= Math.abs(t))
            return; 
        t = Math.acos(t);
        double sin_t = Math.sin(t);
        if (sin_t == 0.0D)
            return; 
        double s = Math.sin((1.0D - alpha) * t) / sin_t;
        t = Math.sin(alpha * t) / sin_t;
        this.x = s * this.x + t * x1;
        this.y = s * this.y + t * y1;
        this.z = s * this.z + t * z1;
        this.w = s * this.w + t * w1;
    }
    
    public final void interpolate(Quat4d q1, Quat4d q2, double alpha) {
        set(q1);
        interpolate(q2, alpha);
    }
    
    private void setFromMat(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double tr = m00 + m11 + m22;
        if (tr >= 0.0D) {
            double s = Math.sqrt(tr + 1.0D);
            this.w = s * 0.5D;
            s = 0.5D / s;
            this.x = (m21 - m12) * s;
            this.y = (m02 - m20) * s;
            this.z = (m10 - m01) * s;
        } else {
            double max = Math.max(Math.max(m00, m11), m22);
            if (max == m00) {
                double d = Math.sqrt(m00 - m11 + m22 + 1.0D);
                this.x = d * 0.5D;
                d = 0.5D / d;
                this.y = (m01 + m10) * d;
                this.z = (m20 + m02) * d;
                this.w = (m21 - m12) * d;
            } else if (max == m11) {
                double d = Math.sqrt(m11 - m22 + m00 + 1.0D);
                this.y = d * 0.5D;
                d = 0.5D / d;
                this.z = (m12 + m21) * d;
                this.x = (m01 + m10) * d;
                this.w = (m02 - m20) * d;
            } else {
                double d = Math.sqrt(m22 - m00 + m11 + 1.0D);
                this.z = d * 0.5D;
                d = 0.5D / d;
                this.x = (m20 + m02) * d;
                this.y = (m12 + m21) * d;
                this.w = (m10 - m01) * d;
            } 
        } 
    }
}
