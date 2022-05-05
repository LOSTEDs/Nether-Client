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

import java.io.IOException;

public class VecmathTest {
    public static String NL = System.getProperty("line.separator");
    
    public static float epsilon = 1.0E-5F;
    
    public static boolean equals(double m1, double m2) {
        return (Math.abs(m1 - m2) < epsilon);
    }
    
    public static boolean equals(Matrix3d m1, Matrix3d m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Matrix4d m1, Matrix4d m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Tuple4d m1, Tuple4d m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Tuple3d m1, Tuple3d m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Matrix3f m1, Matrix3f m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Matrix4f m1, Matrix4f m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(GMatrix m1, GMatrix m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(GVector v1, GVector v2) {
        return v1.epsilonEquals(v2, epsilon);
    }
    
    public static boolean equals(Tuple4f m1, Tuple4f m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(Tuple3f m1, Tuple3f m2) {
        return m1.epsilonEquals(m2, epsilon);
    }
    
    public static boolean equals(AxisAngle4d a1, AxisAngle4d a2) {
        if (0.0D < a1.x * a2.x + a1.y * a2.y + a1.z * a2.z)
            return (equals(a1.y * a2.z - a1.z * a2.y, 0.0D) && equals(a1.z * a2.x - a1.x * a2.z, 0.0D) && equals(a1.x * a2.y - a1.y * a2.x, 0.0D) && equals(a1.angle, a2.angle)); 
        return (equals(a1.y * a2.z - a1.z * a2.y, 0.0D) && equals(a1.z * a2.x - a1.x * a2.z, 0.0D) && equals(a1.x * a2.y - a1.y * a2.x, 0.0D) && (equals(a1.angle, -a2.angle) || equals(a1.angle + a2.angle, 6.283185307179586D) || equals(a1.angle + a2.angle, -6.283185307179586D)));
    }
    
    public static boolean equals(AxisAngle4f a1, AxisAngle4f a2) {
        if (0.0F < a1.x * a2.x + a1.y * a2.y + a1.z * a2.z)
            return (equals((a1.y * a2.z - a1.z * a2.y), 0.0D) && equals((a1.z * a2.x - a1.x * a2.z), 0.0D) && equals((a1.x * a2.y - a1.y * a2.x), 0.0D) && equals(a1.angle, a2.angle)); 
        return (equals((a1.y * a2.z - a1.z * a2.y), 0.0D) && equals((a1.z * a2.x - a1.x * a2.z), 0.0D) && equals((a1.x * a2.y - a1.y * a2.x), 0.0D) && (equals(a1.angle, -a2.angle) || equals((a1.angle + a2.angle), 6.283185307179586D) || equals((a1.angle + a2.angle), -6.283185307179586D)));
    }
    
    public static void ASSERT(boolean condition) {
        if (!condition)
            throw new InternalError("Vecmath Test Failed!"); 
    }
    
    public static void ASSERT(boolean condition, String comment) {
        if (!condition)
            throw new InternalError("Vecmath Test Failed!: " + comment); 
    }
    
    public static void exit() {
        System.out.println("java.vecmath all test passed successfully.");
        System.out.print("Quit ?");
        try {
            System.in.read();
        } catch (IOException e) {}
    }
    
    public static void main(String[] v) {
        System.out.print("Vector3d ...");
        Vector3dTest();
        System.out.println("ok.");
        System.out.print("Vector3f ...");
        Vector3fTest();
        System.out.println("ok.");
        System.out.print("Matrix3d with Quat4d, AxisAngle4d, Point/Vector3d interaction ...");
        Matrix3dTest();
        System.out.println("ok.");
        System.out.print("Matrix3f with Quat4f, AxisAngle4f, Point/Vector3f interaction ...");
        Matrix3fTest();
        System.out.println("ok.");
        System.out.print("Matrix4d with Quat4d, AxisAngle4d, Point/Vector3d interaction ...");
        Matrix4dTest();
        System.out.println("ok.");
        System.out.print("Matrix4f with Quat4f, AxisAngle4f, Point/Vector3f interaction ...");
        Matrix4fTest();
        System.out.println("ok.");
        System.out.print("GMatrix with GVector interaction ...");
        GMatrixTest();
        System.out.println("ok.");
        System.out.print("SVD test ...");
        SVDTest();
        System.out.println("ok.");
        exit();
    }
    
    public static void Vector3dTest() {
        Vector3d zeroVector = new Vector3d();
        Vector3d v1 = new Vector3d(2.0D, 3.0D, 4.0D);
        Vector3d v2 = new Vector3d(2.0D, 5.0D, -8.0D);
        Vector3d v3 = new Vector3d();
        v3.cross(v1, v2);
        ASSERT(equals(v3.dot(v1), 0.0D));
        ASSERT(equals(v3.dot(v2), 0.0D));
        v1.cross(v1, v2);
        ASSERT(equals(v1, new Vector3d(-44.0D, 24.0D, 4.0D)));
        ASSERT(equals(v2.lengthSquared(), 93.0D));
        ASSERT(equals(v2.length(), Math.sqrt(93.0D)));
        v1.set(v2);
        v2.normalize();
        ASSERT(equals(v2.length(), 1.0D));
        v1.cross(v2, v1);
        ASSERT(equals(v1, zeroVector));
        v1.set(1.0D, 2.0D, 3.0D);
        v2.set(-1.0D, -6.0D, -3.0D);
        double ang = v1.angle(v2);
        ASSERT(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
        v1.set(v2);
        ang = v1.angle(v2);
        ASSERT(equals(ang, 0.0D));
        ASSERT(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
        v1.set(1.0D, 2.0D, 3.0D);
        v2.set(1.0D, 2.0D, 3.00001D);
        ang = v1.angle(v2);
        ASSERT(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
        v1.set(1.0D, 2.0D, 3.0D);
        v2.set(-1.0D, -2.0D, -3.00001D);
        ang = v1.angle(v2);
        ASSERT(equals(v1.length() * v2.length() * Math.cos(ang), v1.dot(v2)));
    }
    
    public static void Vector3fTest() {
        Vector3f zeroVector = new Vector3f();
        Vector3f v1 = new Vector3f(2.0F, 3.0F, 4.0F);
        Vector3f v2 = new Vector3f(2.0F, 5.0F, -8.0F);
        Vector3f v3 = new Vector3f();
        v3.cross(v1, v2);
        ASSERT(equals(v3.dot(v1), 0.0D));
        ASSERT(equals(v3.dot(v2), 0.0D));
        v1.cross(v1, v2);
        ASSERT(equals(v1, new Vector3f(-44.0F, 24.0F, 4.0F)));
        ASSERT(equals(v2.lengthSquared(), 93.0D));
        ASSERT(equals(v2.length(), Math.sqrt(93.0D)));
        v1.set(v2);
        v2.normalize();
        ASSERT(equals(v2.length(), 1.0D));
        v1.cross(v2, v1);
        ASSERT(equals(v1, zeroVector));
        v1.set(1.0F, 2.0F, 3.0F);
        v2.set(-1.0F, -6.0F, -3.0F);
        double ang = v1.angle(v2);
        ASSERT(equals((v1.length() * v2.length()) * Math.cos(ang), v1.dot(v2)));
        v1.set(v2);
        ang = v1.angle(v2);
        ASSERT(equals(ang, 0.0D));
        ASSERT(equals((v1.length() * v2.length()) * Math.cos(ang), v1.dot(v2)));
    }
    
    public static void Matrix3dTest() {
        Matrix3d O = new Matrix3d();
        Matrix3d I = new Matrix3d();
        I.setIdentity();
        Matrix3d m1 = new Matrix3d();
        Matrix3d m2 = new Matrix3d();
        double[] v = { 2.0D, 1.0D, 4.0D, 1.0D, -2.0D, 3.0D, -3.0D, -1.0D, 1.0D };
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++)
                m1.setElement(i, k, (i * 2 * k + 3)); 
        } 
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++)
                ASSERT(equals(m1.getElement(j, k), (j * 2 * k + 3))); 
        } 
        m1.set(v);
        m2 = new Matrix3d(m1);
        m2.mul(O);
        ASSERT(equals(m2, O));
        m2.mul(m1, I);
        ASSERT(equals(m2, m1));
        ASSERT(equals(m1.determinant(), -36.0D));
        m2.negate(m1);
        m2.add(m1);
        ASSERT(equals(m2, O));
        m2.negate(m1);
        Matrix3d m3 = new Matrix3d(m1);
        m3.sub(m2);
        m3.mul(0.5D);
        ASSERT(equals(m1, m3));
        m3.invert(m2);
        m3.mul(m2);
        ASSERT(equals(m3, I));
        Point3d p1 = new Point3d(1.0D, 2.0D, 3.0D);
        Vector3d v1 = new Vector3d(2.0D, -1.0D, -4.0D);
        p1.set(1.0D, 0.0D, 0.0D);
        m1.rotZ(0.5235987755982988D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(Math.cos(0.5235987755982988D), Math.sin(0.5235987755982988D), 0.0D)));
        p1.set(1.0D, 0.0D, 0.0D);
        m1.rotY(1.0471975511965976D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        AxisAngle4d a1 = new AxisAngle4d(0.0D, 1.0D, 0.0D, 1.0471975511965976D);
        p1.set(1.0D, 0.0D, 0.0D);
        m1.set(a1);
        m1.transform(p1, p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        Quat4d q1 = new Quat4d();
        p1.set(1.0D, 0.0D, 0.0D);
        q1.set(a1);
        m2.set(q1);
        ASSERT(equals(m1, m2));
        m2.transform(p1, p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        a1.set(1.0D, 2.0D, -3.0D, 1.0471975511965976D);
        Mat3dQuatAxisAngle(a1);
        a1.set(1.0D, 2.0D, 3.0D, Math.PI);
        Mat3dQuatAxisAngle(a1);
        a1.set(1.0D, 0.1D, 0.1D, Math.PI);
        Mat3dQuatAxisAngle(a1);
        a1.set(0.1D, 1.0D, 0.1D, Math.PI);
        Mat3dQuatAxisAngle(a1);
        a1.set(0.1D, 0.1D, 1.0D, Math.PI);
        Mat3dQuatAxisAngle(a1);
        a1.set(1.0D, 1.0D, 1.0D, 2.0943951023931953D);
        m1.set(a1);
        p1.set(1.0D, 0.0D, 0.0D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(0.0D, 1.0D, 0.0D)));
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(0.0D, 0.0D, 1.0D)));
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(1.0D, 0.0D, 0.0D)));
        m1.set(a1);
        ASSERT(equals(m1.determinant(), 1.0D));
        ASSERT(equals(m1.getScale(), 1.0D));
        m2.set(a1);
        m2.normalize();
        ASSERT(equals(m1, m2));
        m2.set(a1);
        m2.normalizeCP();
        ASSERT(equals(m1, m2));
        double scale = 3.0D;
        m2.rotZ(-0.7853981633974483D);
        m2.mul(scale);
        ASSERT(equals(m2.determinant(), scale * scale * scale));
        ASSERT(equals(m2.getScale(), scale));
        m2.normalize();
        ASSERT(equals(m2.determinant(), 1.0D));
        ASSERT(equals(m2.getScale(), 1.0D));
        m2.rotX(1.0471975511965976D);
        m2.mul(scale);
        ASSERT(equals(m2.determinant(), scale * scale * scale));
        ASSERT(equals(m2.getScale(), scale));
        m2.normalizeCP();
        ASSERT(equals(m2.determinant(), 1.0D));
        ASSERT(equals(m2.getScale(), 1.0D));
        m1.set(a1);
        m2.invert(m1);
        m1.transpose();
        ASSERT(equals(m1, m2));
    }
    
    static void Mat3dQuatAxisAngle(AxisAngle4d a1) {
        Matrix3d m1 = new Matrix3d();
        Matrix3d m2 = new Matrix3d();
        AxisAngle4d a2 = new AxisAngle4d();
        Quat4d q1 = new Quat4d();
        Quat4d q2 = new Quat4d();
        q1.set(a1);
        a2.set(q1);
        ASSERT(equals(a1, a2));
        q2 = new Quat4d();
        q2.set(a2);
        ASSERT(equals(q1, q2));
        q1.set(a1);
        m1.set(q1);
        q2.set(m1);
        ASSERT(equals(q1, q2));
        m2.set(q2);
        ASSERT(equals(m1, m2));
        m1.set(a1);
        a2.set(m1);
        ASSERT(equals(a1, a2));
        m2.set(a1);
        ASSERT(equals(m1, m2));
        a1.x *= 2.0D;
        a1.y *= 2.0D;
        a1.z *= 2.0D;
        m2.set(a1);
        a1.x = -a1.x;
        a1.y = -a1.y;
        a1.z = -a1.z;
        a1.angle = -a1.angle;
        m2.set(a1);
        ASSERT(equals(m1, m2));
    }
    
    public static void Matrix3fTest() {}
    
    static void Mat4dQuatAxisAngle(AxisAngle4d a1) {
        Matrix4d m1 = new Matrix4d();
        Matrix4d m2 = new Matrix4d();
        AxisAngle4d a2 = new AxisAngle4d();
        Quat4d q1 = new Quat4d();
        Quat4d q2 = new Quat4d();
        q1.set(a1);
        a2.set(q1);
        ASSERT(equals(a1, a2));
        q2 = new Quat4d();
        q2.set(a2);
        ASSERT(equals(q1, q2));
        q1.set(a1);
        m1.set(q1);
        q2.set(m1);
        ASSERT(equals(q1, q2));
        m2.set(q2);
        ASSERT(equals(m1, m2));
        m1.set(a1);
        a2.set(m1);
        ASSERT(equals(a1, a2));
        m2.set(a1);
        ASSERT(equals(m1, m2));
        a1.x *= 2.0D;
        a1.y *= 2.0D;
        a1.z *= 2.0D;
        m2.set(a1);
        a1.x = -a1.x;
        a1.y = -a1.y;
        a1.z = -a1.z;
        a1.angle = -a1.angle;
        m2.set(a1);
        ASSERT(equals(m1, m2));
    }
    
    public static void Matrix4dTest() {
        Matrix4d O = new Matrix4d();
        Matrix4d I = new Matrix4d();
        I.setIdentity();
        Matrix4d m1 = new Matrix4d();
        Matrix4d m2 = new Matrix4d();
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++)
                m1.setElement(i, k, (i * 2 * k + 3)); 
        } 
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++)
                ASSERT(equals(m1.getElement(j, k), (j * 2 * k + 3))); 
        } 
        m1 = new Matrix4d(2.0D, 1.0D, 4.0D, 1.0D, -2.0D, 3.0D, -3.0D, 1.0D, -1.0D, 1.0D, 2.0D, 2.0D, 0.0D, 8.0D, 1.0D, -10.0D);
        m2 = new Matrix4d(m1);
        m2.mul(O);
        ASSERT(equals(m2, O), "O = m2 x O");
        m2.mul(m1, I);
        ASSERT(equals(m2, m1), "m2 = m1 x I");
        m2.negate(m1);
        m2.add(m1);
        ASSERT(equals(m2, O));
        double[] v = { 
                5.0D, 1.0D, 4.0D, 0.0D, 2.0D, 3.0D, -4.0D, -1.0D, 2.0D, 3.0D, 
                -4.0D, -1.0D, 1.0D, 1.0D, 1.0D, 1.0D };
        m2.set(v);
        m2.negate(m1);
        Matrix4d m3 = new Matrix4d(m1);
        m3.sub(m2);
        m3.mul(0.5D);
        ASSERT(equals(m1, m3));
        m2 = new Matrix4d(0.5D, 1.0D, 4.0D, 1.0D, -2.0D, 3.0D, -4.0D, -1.0D, 1.0D, 9.0D, 100.0D, 2.0D, -20.0D, 2.0D, 1.0D, 9.0D);
        m3.invert(m2);
        m3.mul(m2);
        ASSERT(equals(m3, I));
        m1 = new Matrix4d(-1.0D, 2.0D, 0.0D, 3.0D, -1.0D, 1.0D, -3.0D, -1.0D, 1.0D, 2.0D, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
        Point3d p1 = new Point3d(1.0D, 2.0D, 3.0D);
        Vector3d v0 = new Vector3d();
        Vector3d v1 = new Vector3d(1.0D, 2.0D, 3.0D);
        Vector4d V2 = new Vector4d(2.0D, -1.0D, -4.0D, 1.0D);
        ASSERT(m1.toString().equals("[" + NL + "  [-1.0\t2.0\t0.0\t3.0]" + NL + "  [-1.0\t1.0\t-3.0\t-1.0]" + NL + "  [1.0\t2.0\t1.0\t1.0]" + NL + "  [0.0\t0.0\t0.0\t1.0] ]"));
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(6.0D, -9.0D, 9.0D)));
        m1.transform(V2, V2);
        ASSERT(equals(V2, new Vector4d(-1.0D, 8.0D, -3.0D, 1.0D)));
        p1.set(1.0D, 0.0D, 0.0D);
        m1.rotZ(0.5235987755982988D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(Math.cos(0.5235987755982988D), Math.sin(0.5235987755982988D), 0.0D)));
        p1.set(1.0D, 0.0D, 0.0D);
        m1.rotY(1.0471975511965976D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        AxisAngle4d a1 = new AxisAngle4d(0.0D, 1.0D, 0.0D, 1.0471975511965976D);
        p1.set(1.0D, 0.0D, 0.0D);
        m1.set(a1);
        m1.transform(p1, p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        Quat4d q1 = new Quat4d();
        p1.set(1.0D, 0.0D, 0.0D);
        q1.set(a1);
        m2.set(q1);
        ASSERT(equals(m1, m2));
        m2.transform(p1, p1);
        ASSERT(equals(p1, new Point3d(Math.cos(1.0471975511965976D), 0.0D, -Math.sin(1.0471975511965976D))));
        a1.set(1.0D, 2.0D, -3.0D, 1.0471975511965976D);
        Mat4dQuatAxisAngle(a1);
        a1.set(1.0D, 2.0D, 3.0D, Math.PI);
        Mat4dQuatAxisAngle(a1);
        a1.set(1.0D, 0.1D, 0.1D, Math.PI);
        Mat4dQuatAxisAngle(a1);
        a1.set(0.1D, 1.0D, 0.1D, Math.PI);
        Mat4dQuatAxisAngle(a1);
        a1.set(0.1D, 0.1D, 1.0D, Math.PI);
        Mat4dQuatAxisAngle(a1);
        a1.set(1.0D, 1.0D, 1.0D, 2.0943951023931953D);
        m1.set(a1);
        p1.set(1.0D, 0.0D, 0.0D);
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(0.0D, 1.0D, 0.0D)));
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(0.0D, 0.0D, 1.0D)));
        m1.transform(p1);
        ASSERT(equals(p1, new Point3d(1.0D, 0.0D, 0.0D)));
        m1.set(a1);
        ASSERT(equals(m1.determinant(), 1.0D));
        ASSERT(equals(m1.getScale(), 1.0D));
        m2.set(a1);
        m1.set(a1);
        m2.invert(m1);
        m1.transpose();
        ASSERT(equals(m1, m2));
        Matrix3d n1 = new Matrix3d();
        n1.set(a1);
        Matrix3d n2 = new Matrix3d();
        v1.set(2.0D, -1.0D, -1.0D);
        m1.set(n1, v1, 0.4D);
        m2.set(n1, v1, 0.4D);
        Vector3d v2 = new Vector3d();
        double s = m1.get(n2, v2);
        ASSERT(equals(n1, n2));
        ASSERT(equals(s, 0.4D));
        ASSERT(equals(v1, v2));
        ASSERT(equals(m1, m2));
    }
    
    public static void Matrix4fTest() {}
    
    public static void GMatrixTest() {
        GMatrix I44 = new GMatrix(4, 4);
        GMatrix O44 = new GMatrix(4, 4);
        O44.setZero();
        GMatrix O34 = new GMatrix(3, 4);
        O34.setZero();
        GMatrix m1 = new GMatrix(3, 4);
        GMatrix m2 = new GMatrix(3, 4);
        Matrix3d mm1 = new Matrix3d();
        Matrix3d mm2 = new Matrix3d();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                m1.setElement(i, k, ((i + 1) * (k + 2)));
                if (k < 3)
                    mm1.setElement(i, k, ((i + 1) * (k + 2))); 
            } 
        } 
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 4; k++)
                ASSERT(equals(m1.getElement(j, k), ((j + 1) * (k + 2)))); 
        } 
        m1.get(mm2);
        ASSERT(equals(mm1, mm2));
        m2.mul(m1, I44);
        ASSERT(equals(m1, m2));
        m2.mul(m1, O44);
        ASSERT(equals(O34, m2));
        Matrix4d mm3 = new Matrix4d(1.0D, 2.0D, 3.0D, 4.0D, -2.0D, 3.0D, -1.0D, 3.0D, -1.0D, -2.0D, -4.0D, 1.0D, 1.0D, 1.0D, -1.0D, -2.0D);
        Matrix4d mm4 = new Matrix4d();
        Matrix4d mm5 = new Matrix4d();
        mm5.set(mm3);
        m1.setSize(4, 4);
        m2.setSize(4, 4);
        m1.set(mm3);
        ASSERT(m1.toString().equals("[" + NL + "  [1.0\t2.0\t3.0\t4.0]" + NL + "  [-2.0\t3.0\t-1.0\t3.0]" + NL + "  [-1.0\t-2.0\t-4.0\t1.0]" + NL + "  [1.0\t1.0\t-1.0\t-2.0] ]"));
        m2.set(m1);
        m1.invert();
        mm3.invert();
        mm5.mul(mm3);
        ASSERT(equals(mm5, new Matrix4d(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D)));
        m1.get(mm4);
        ASSERT(equals(mm3, mm4));
        m1.mul(m2);
        ASSERT(equals(m1, I44));
        Matrix4d mm6 = new Matrix4d(1.0D, 2.0D, 3.0D, 4.0D, -2.0D, 3.0D, -1.0D, 3.0D, -1.0D, -2.0D, -4.0D, 1.0D, 1.0D, 1.0D, -1.0D, -2.0D);
        Vector4d vv1 = new Vector4d(1.0D, -1.0D, -1.0D, 2.0D);
        Vector4d vv2 = new Vector4d();
        Vector4d vv3 = new Vector4d(4.0D, 2.0D, 7.0D, -3.0D);
        mm6.transform(vv1, vv2);
        ASSERT(equals(vv2, vv3));
        m1.set(mm6);
        GVector x = new GVector(4);
        GVector v2 = new GVector(4);
        GVector b = new GVector(4);
        x.set(vv1);
        b.set(vv3);
        GVector mx = new GVector(4);
        mx.mul(m1, x);
        ASSERT(equals(mx, b));
        GVector p = new GVector(4);
        m1.LUD(m2, p);
        ASSERT(checkLUD(m1, m2, p));
        GVector xx = new GVector(4);
        xx.LUDBackSolve(m2, b, p);
        ASSERT(equals(xx, x));
        GMatrix u = new GMatrix(m1.getNumRow(), m1.getNumRow());
        GMatrix w = new GMatrix(m1.getNumRow(), m1.getNumCol());
        GMatrix v = new GMatrix(m1.getNumCol(), m1.getNumCol());
        int rank = m1.SVD(u, w, v);
        ASSERT((rank == 4));
        ASSERT(checkSVD(m1, u, w, v));
        xx.SVDBackSolve(u, w, v, b);
        ASSERT(equals(xx, x));
    }
    
    static boolean checkLUD(GMatrix m, GMatrix LU, GVector permutation) {
        int n = m.getNumCol();
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double aij = 0.0D;
                int min = (i < j) ? i : j;
                for (int k = 0; k <= min; k++) {
                    if (i != k) {
                        aij += LU.getElement(i, k) * LU.getElement(k, j);
                    } else {
                        aij += LU.getElement(k, j);
                    } 
                } 
                if (Math.abs(aij - m.getElement((int)permutation.getElement(i), j)) > epsilon) {
                    System.out.println("a[" + i + "," + j + "] = " + aij + "(LU)ij ! = " + m.getElement((int)permutation.getElement(i), j));
                    ok = false;
                } 
            } 
        } 
        return ok;
    }
    
    static boolean checkSVD(GMatrix m, GMatrix u, GMatrix w, GMatrix v) {
        boolean ok = true;
        int wsize = (w.getNumRow() < w.getNumRow()) ? w.getNumRow() : w.getNumCol();
        for (int i = 0; i < m.getNumRow(); i++) {
            for (int j = 0; j < m.getNumCol(); j++) {
                double sum = 0.0D;
                for (int k = 0; k < m.getNumCol(); k++)
                    sum += u.getElement(i, k) * w.getElement(k, k) * v.getElement(j, k); 
                if (epsilon < Math.abs(m.getElement(i, j) - sum)) {
                    System.out.println("(SVD)ij = " + sum + " != a[" + i + "," + j + "] = " + m.getElement(i, j));
                    ok = false;
                } 
            } 
        } 
        if (!ok) {
            System.out.print("[W] = ");
            System.out.println(w);
            System.out.print("[U] = ");
            System.out.println(u);
            System.out.print("[V] = ");
            System.out.println(v);
        } 
        return ok;
    }
    
    public static void SVDTest() {
        double[] val = { 
                1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 0.0D, 
                8.0D, 7.0D, 6.0D, 5.0D, 4.0D, 3.0D, 2.0D, 1.0D, 0.0D, 1.0D };
        int m = 5;
        int n = 4;
        GMatrix matA = new GMatrix(m, n, val);
        GMatrix matU = new GMatrix(m, m);
        GMatrix matW = new GMatrix(m, n);
        GMatrix matV = new GMatrix(n, n);
        int rank = matA.SVD(matU, matW, matV);
        GMatrix matTEMP = new GMatrix(m, n);
        matTEMP.mul(matU, matW);
        matV.transpose();
        matTEMP.mul(matV);
        if (!equals(matTEMP, matA)) {
            System.out.println("matU=" + matU);
            System.out.println("matW=" + matW);
            System.out.println("matV=" + matV);
            System.out.println("matA=" + matA);
            System.out.println("UWV=" + matTEMP);
        } 
        ASSERT(equals(matTEMP, matA));
    }
}
