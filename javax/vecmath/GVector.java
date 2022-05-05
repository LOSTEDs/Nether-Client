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

public class GVector implements Serializable {
    private int elementCount;
    
    private double[] elementData;
    
    public GVector(int length) {
        this.elementCount = length;
        this.elementData = new double[length];
    }
    
    public GVector(double[] vector) {
        this(vector.length);
        System.arraycopy(vector, 0, this.elementData, 0, this.elementCount);
    }
    
    public GVector(GVector vector) {
        this(vector.elementCount);
        System.arraycopy(vector.elementData, 0, this.elementData, 0, this.elementCount);
    }
    
    public GVector(Tuple2f tuple) {
        this(2);
        set(tuple);
    }
    
    public GVector(Tuple3f tuple) {
        this(3);
        set(tuple);
    }
    
    public GVector(Tuple3d tuple) {
        this(3);
        set(tuple);
    }
    
    public GVector(Tuple4f tuple) {
        this(4);
        set(tuple);
    }
    
    public GVector(Tuple4d tuple) {
        this(4);
        set(tuple);
    }
    
    public GVector(double[] vector, int length) {
        this(length);
        System.arraycopy(vector, 0, this.elementData, 0, this.elementCount);
    }
    
    public final double norm() {
        return Math.sqrt(normSquared());
    }
    
    public final double normSquared() {
        double s = 0.0D;
        for (int i = 0; i < this.elementCount; i++)
            s += this.elementData[i] * this.elementData[i]; 
        return s;
    }
    
    public final void normalize(GVector v1) {
        set(v1);
        normalize();
    }
    
    public final void normalize() {
        double len = norm();
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = this.elementData[i] / len; 
    }
    
    public final void scale(double s, GVector v1) {
        set(v1);
        scale(s);
    }
    
    public final void scale(double s) {
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = this.elementData[i] * s; 
    }
    
    public final void scaleAdd(double s, GVector v1, GVector v2) {
        double[] v1data = v1.elementData;
        double[] v2data = v2.elementData;
        if (this.elementCount != v1.elementCount)
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v1's size:" + v1.elementCount); 
        if (this.elementCount != v2.elementCount)
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v2's size:" + v2.elementCount); 
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = s * v1data[i] + v2data[i]; 
    }
    
    public final void add(GVector vector) {
        double[] v1data = vector.elementData;
        if (this.elementCount != vector.elementCount)
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != v2's size:" + vector.elementCount); 
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = this.elementData[i] + v1data[i]; 
    }
    
    public final void add(GVector vector1, GVector vector2) {
        set(vector1);
        add(vector2);
    }
    
    public final void sub(GVector vector) {
        double[] v1data = vector.elementData;
        if (this.elementCount != vector.elementCount)
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != vector's size:" + vector.elementCount); 
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = this.elementData[i] - v1data[i]; 
    }
    
    public final void sub(GVector vector1, GVector vector2) {
        set(vector1);
        sub(vector2);
    }
    
    public final void mul(GMatrix m1, GVector v1) {
        double[] v1data = v1.elementData;
        int v1size = v1.elementCount;
        int nCol = m1.getNumCol();
        int nRow = m1.getNumRow();
        if (v1size != nCol)
            throw new IllegalArgumentException("v1.size:" + v1size + " != m1.nCol:" + nCol); 
        if (this.elementCount != nRow)
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != m1.nRow:" + nRow); 
        for (int i = 0; i < this.elementCount; i++) {
            double sum = 0.0D;
            for (int j = 0; j < nCol; j++)
                sum += m1.getElement(i, j) * v1data[j]; 
            this.elementData[i] = sum;
        } 
    }
    
    public final void mul(GVector v1, GMatrix m1) {
        double[] v1data = v1.elementData;
        int v1size = v1.elementCount;
        int nCol = m1.getNumCol();
        int nRow = m1.getNumRow();
        if (v1size != nRow)
            throw new IllegalArgumentException("v1.size:" + v1size + " != m1.nRow:" + nRow); 
        if (this.elementCount != nCol)
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != m1.nCol:" + nCol); 
        for (int i = 0; i < this.elementCount; i++) {
            double sum = 0.0D;
            for (int j = 0; j < nRow; j++)
                sum += m1.getElement(j, i) * v1data[j]; 
            this.elementData[i] = sum;
        } 
    }
    
    public final void negate() {
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = -this.elementData[i]; 
    }
    
    public final void zero() {
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = 0.0D; 
    }
    
    public final void setSize(int newSize) {
        if (newSize < 0)
            throw new NegativeArraySizeException("newSize:" + newSize + " < 0"); 
        if (this.elementCount < newSize) {
            double[] oldData = this.elementData;
            this.elementData = new double[newSize];
            System.arraycopy(oldData, 0, this.elementData, 0, this.elementCount);
        } 
        this.elementCount = newSize;
    }
    
    public final void set(double[] vector) {
        System.arraycopy(vector, 0, this.elementData, 0, this.elementCount);
    }
    
    public final void set(GVector vector) {
        System.arraycopy(vector.elementData, 0, this.elementData, 0, this.elementCount);
    }
    
    public final void set(Tuple2f tuple) {
        this.elementData[0] = tuple.x;
        this.elementData[1] = tuple.y;
    }
    
    public final void set(Tuple3f tuple) {
        this.elementData[0] = tuple.x;
        this.elementData[1] = tuple.y;
        this.elementData[2] = tuple.z;
    }
    
    public final void set(Tuple3d tuple) {
        this.elementData[0] = tuple.x;
        this.elementData[1] = tuple.y;
        this.elementData[2] = tuple.z;
    }
    
    public final void set(Tuple4f tuple) {
        this.elementData[0] = tuple.x;
        this.elementData[1] = tuple.y;
        this.elementData[2] = tuple.z;
        this.elementData[3] = tuple.w;
    }
    
    public final void set(Tuple4d tuple) {
        this.elementData[0] = tuple.x;
        this.elementData[1] = tuple.y;
        this.elementData[2] = tuple.z;
        this.elementData[3] = tuple.w;
    }
    
    public final int getSize() {
        return this.elementCount;
    }
    
    public final double getElement(int index) {
        try {
            return this.elementData[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "must be in [0, " + (this.elementCount - 1) + "]");
        } 
    }
    
    public final void setElement(int index, double value) {
        try {
            this.elementData[index] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + " must be in [0, " + (this.elementCount - 1) + "]");
        } 
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("(");
        for (int i = 0; i < this.elementCount - 1; i++) {
            buf.append(this.elementData[i]);
            buf.append(",");
        } 
        buf.append(this.elementData[this.elementCount - 1]);
        buf.append(")");
        return buf.toString();
    }
    
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.elementCount; i++) {
            long bits = Double.doubleToLongBits(this.elementData[i]);
            hash ^= (int)(bits ^ bits >> 32L);
        } 
        return hash;
    }
    
    public boolean equals(GVector vector1) {
        if (vector1 == null)
            return false; 
        if (this.elementCount != vector1.elementCount)
            return false; 
        double[] v1data = vector1.elementData;
        for (int i = 0; i < this.elementCount; i++) {
            if (this.elementData[i] != v1data[i])
                return false; 
        } 
        return true;
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof GVector && equals((GVector)o1));
    }
    
    public boolean epsilonEquals(GVector v1, double epsilon) {
        if (this.elementCount != v1.elementCount)
            return false; 
        double[] v1data = v1.elementData;
        for (int i = 0; i < this.elementCount; i++) {
            if (Math.abs(this.elementData[i] - v1data[i]) > epsilon)
                return false; 
        } 
        return true;
    }
    
    public final double dot(GVector v1) {
        double[] v1data = v1.elementData;
        if (this.elementCount != v1.elementCount)
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != v1.size:" + v1.elementCount); 
        double sum = 0.0D;
        for (int i = 0; i < this.elementCount; i++)
            sum += this.elementData[i] * v1data[i]; 
        return sum;
    }
    
    public final void SVDBackSolve(GMatrix U, GMatrix W, GMatrix V, GVector b) {
        if (this.elementCount != U.getNumRow() || this.elementCount != U.getNumCol())
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != U.nRow,nCol:" + U.getNumRow() + "," + U.getNumCol()); 
        if (this.elementCount != W.getNumRow())
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != W.nRow:" + W.getNumRow()); 
        if (b.elementCount != W.getNumCol())
            throw new ArrayIndexOutOfBoundsException("b.size:" + b.elementCount + " != W.nCol:" + W.getNumCol()); 
        if (b.elementCount != V.getNumRow() || b.elementCount != V.getNumCol())
            throw new ArrayIndexOutOfBoundsException("b.size:" + this.elementCount + " != V.nRow,nCol:" + V.getNumRow() + "," + V.getNumCol()); 
        int m = U.getNumRow();
        int n = V.getNumRow();
        double[] tmp = new double[n];
        for (int j = 0; j < n; j++) {
            double s = 0.0D;
            double wj = W.getElement(j, j);
            if (wj != 0.0D) {
                for (int k = 0; k < m; k++)
                    s += U.getElement(k, j) * b.elementData[k]; 
                s /= wj;
            } 
            tmp[j] = s;
        } 
        for (int i = 0; i < n; i++) {
            double s = 0.0D;
            for (int jj = 0; jj < n; jj++)
                s += V.getElement(i, jj) * tmp[jj]; 
            this.elementData[i] = s;
        } 
    }
    
    public final void LUDBackSolve(GMatrix LU, GVector b, GVector permutation) {
        if (this.elementCount != b.elementCount)
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != b.size:" + b.elementCount); 
        if (this.elementCount != LU.getNumRow())
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != LU.nRow:" + LU.getNumRow()); 
        if (this.elementCount != LU.getNumCol())
            throw new ArrayIndexOutOfBoundsException("this.size:" + this.elementCount + " != LU.nCol:" + LU.getNumCol()); 
        int n = this.elementCount;
        double[] indx = permutation.elementData;
        double[] x = this.elementData;
        double[] bdata = b.elementData;
        for (int i = 0; i < n; i++)
            x[i] = bdata[(int)indx[i]]; 
        int ii = -1;
        for (int j = 0; j < n; j++) {
            double sum = x[j];
            if (0 <= ii) {
                for (int m = ii; m <= j - 1; m++)
                    sum -= LU.getElement(j, m) * x[m]; 
            } else if (sum != 0.0D) {
                ii = j;
            } 
            x[j] = sum;
        } 
        for (int k = n - 1; k >= 0; k--) {
            double sum = x[k];
            for (int m = k + 1; m < n; m++)
                sum -= LU.getElement(k, m) * x[m]; 
            x[k] = sum / LU.getElement(k, k);
        } 
    }
    
    public final double angle(GVector v1) {
        return Math.acos(dot(v1) / norm() / v1.norm());
    }
    
    public final void interpolate(GVector v1, GVector v2, float alpha) {
        interpolate(v1, v2, alpha);
    }
    
    public final void interpolate(GVector v1, float alpha) {
        interpolate(v1, alpha);
    }
    
    public final void interpolate(GVector v1, GVector v2, double alpha) {
        set(v1);
        interpolate(v2, alpha);
    }
    
    public final void interpolate(GVector v1, double alpha) {
        double[] v1data = v1.elementData;
        if (this.elementCount != v1.elementCount)
            throw new IllegalArgumentException("this.size:" + this.elementCount + " != v1.size:" + v1.elementCount); 
        double beta = 1.0D - alpha;
        for (int i = 0; i < this.elementCount; i++)
            this.elementData[i] = beta * this.elementData[i] + alpha * v1data[i]; 
    }
}
