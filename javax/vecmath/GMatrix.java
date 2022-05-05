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

public class GMatrix implements Serializable {
    private double[] elementData;
    
    private int nRow;
    
    private int nCol;
    
    public GMatrix(int nRow, int nCol) {
        if (nRow < 0)
            throw new NegativeArraySizeException(nRow + " < 0"); 
        if (nCol < 0)
            throw new NegativeArraySizeException(nCol + " < 0"); 
        this.nRow = nRow;
        this.nCol = nCol;
        this.elementData = new double[nRow * nCol];
        setIdentity();
    }
    
    public GMatrix(int nRow, int nCol, double[] matrix) {
        if (nRow < 0)
            throw new NegativeArraySizeException(nRow + " < 0"); 
        if (nCol < 0)
            throw new NegativeArraySizeException(nCol + " < 0"); 
        this.nRow = nRow;
        this.nCol = nCol;
        this.elementData = new double[nRow * nCol];
        set(matrix);
    }
    
    public GMatrix(GMatrix matrix) {
        this.nRow = matrix.nRow;
        this.nCol = matrix.nCol;
        int newSize = this.nRow * this.nCol;
        this.elementData = new double[newSize];
        System.arraycopy(matrix.elementData, 0, this.elementData, 0, newSize);
    }
    
    public final void mul(GMatrix m1) {
        mul(this, m1);
    }
    
    public final void mul(GMatrix m1, GMatrix m2) {
        if (this.nRow != m1.nRow)
            throw new ArrayIndexOutOfBoundsException("nRow:" + this.nRow + " != m1.nRow:" + m1.nRow); 
        if (this.nCol != m2.nCol)
            throw new ArrayIndexOutOfBoundsException("nCol:" + this.nCol + " != m2.nCol:" + m2.nCol); 
        if (m1.nCol != m2.nRow)
            throw new ArrayIndexOutOfBoundsException("m1.nCol:" + m1.nCol + " != m2.nRow:" + m2.nRow); 
        double[] newData = new double[this.nCol * this.nRow];
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++) {
                double sum = 0.0D;
                for (int k = 0; k < m1.nCol; k++)
                    sum += m1.elementData[i * m1.nCol + k] * m2.elementData[k * m2.nCol + j]; 
                newData[i * this.nCol + j] = sum;
            } 
        } 
        this.elementData = newData;
    }
    
    public final void mul(GVector v1, GVector v2) {
        if (this.nRow < v1.getSize())
            throw new IllegalArgumentException("nRow:" + this.nRow + " < v1.getSize():" + v1.getSize()); 
        if (this.nCol < v2.getSize())
            throw new IllegalArgumentException("nCol:" + this.nCol + " < v2.getSize():" + v2.getSize()); 
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++)
                this.elementData[i * this.nCol + j] = v1.getElement(i) * v2.getElement(j); 
        } 
    }
    
    public final void add(GMatrix m1) {
        if (this.nRow != m1.nRow || this.nCol != m1.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m1:(" + m1.nRow + "x" + m1.nCol + ")."); 
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = this.elementData[i] + m1.elementData[i]; 
    }
    
    public final void add(GMatrix m1, GMatrix m2) {
        if (this.nRow != m1.nRow || this.nCol != m1.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m1:(" + m1.nRow + "x" + m1.nCol + ")."); 
        if (this.nRow != m2.nRow || this.nCol != m2.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m2:(" + m2.nRow + "x" + m2.nCol + ")."); 
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = m1.elementData[i] + m2.elementData[i]; 
    }
    
    public final void sub(GMatrix m1) {
        if (this.nRow != m1.nRow || this.nCol != m1.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m1:(" + m1.nRow + "x" + m1.nCol + ")."); 
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = this.elementData[i] - m1.elementData[i]; 
    }
    
    public final void sub(GMatrix m1, GMatrix m2) {
        if (this.nRow != m1.nRow || this.nCol != m1.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m1:(" + m1.nRow + "x" + m1.nCol + ")."); 
        if (this.nRow != m2.nRow || this.nCol != m2.nCol)
            throw new IllegalArgumentException("this:(" + this.nRow + "x" + this.nCol + ") != m2:(" + m2.nRow + "x" + m2.nCol + ")."); 
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = m1.elementData[i] - m2.elementData[i]; 
    }
    
    public final void negate() {
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = -this.elementData[i]; 
    }
    
    public final void negate(GMatrix m1) {
        set(m1);
        negate();
    }
    
    public final void setIdentity() {
        setZero();
        int min = (this.nRow < this.nCol) ? this.nRow : this.nCol;
        for (int i = 0; i < min; i++)
            this.elementData[i * this.nCol + i] = 1.0D; 
    }
    
    public final void setZero() {
        for (int i = 0; i < this.nRow * this.nCol; i++)
            this.elementData[i] = 0.0D; 
    }
    
    public final void identityMinus() {
        negate();
        int min = (this.nRow < this.nCol) ? this.nRow : this.nCol;
        for (int i = 0; i < min; i++)
            this.elementData[i * this.nCol + i] = this.elementData[i * this.nCol + i] + 1.0D; 
    }
    
    public final void invert() {
        if (this.nRow != this.nCol)
            throw new ArrayIndexOutOfBoundsException("not a square matrix"); 
        int n = this.nRow;
        GMatrix LU = new GMatrix(n, n);
        GVector permutation = new GVector(n);
        GVector column = new GVector(n);
        GVector unit = new GVector(n);
        LUD(LU, permutation);
        for (int j = 0; j < n; j++) {
            unit.zero();
            unit.setElement(j, 1.0D);
            column.LUDBackSolve(LU, unit, permutation);
            setColumn(j, column);
        } 
    }
    
    public final void invert(GMatrix m1) {
        set(m1);
        invert();
    }
    
    public final void copySubMatrix(int rowSource, int colSource, int numRow, int numCol, int rowDest, int colDest, GMatrix target) {
        if (rowSource < 0 || colSource < 0 || rowDest < 0 || colDest < 0)
            throw new ArrayIndexOutOfBoundsException("rowSource,colSource,rowDest,colDest < 0."); 
        if (this.nRow < numRow + rowSource || this.nCol < numCol + colSource)
            throw new ArrayIndexOutOfBoundsException("Source GMatrix too small."); 
        if (target.nRow < numRow + rowDest || target.nCol < numCol + colDest)
            throw new ArrayIndexOutOfBoundsException("Target GMatrix too small."); 
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++)
                target.elementData[(i + rowDest) * this.nCol + j + colDest] = this.elementData[(i + rowSource) * this.nCol + j + colSource]; 
        } 
    }
    
    public final void setSize(int nRow, int nCol) {
        if (nRow < 0 || nCol < 0)
            throw new NegativeArraySizeException("nRow or nCol < 0"); 
        int oldnRow = this.nRow;
        int oldnCol = this.nCol;
        int oldSize = this.nRow * this.nCol;
        this.nRow = nRow;
        this.nCol = nCol;
        int newSize = nRow * nCol;
        double[] oldData = this.elementData;
        if (oldnCol == nCol) {
            if (nRow <= oldnRow)
                return; 
            this.elementData = new double[newSize];
            System.arraycopy(oldData, 0, this.elementData, 0, oldSize);
        } else {
            this.elementData = new double[newSize];
            setZero();
            for (int i = 0; i < oldnRow; i++)
                System.arraycopy(oldData, i * oldnCol, this.elementData, i * nCol, oldnCol); 
        } 
    }
    
    public final void set(double[] matrix) {
        int size = this.nRow * this.nCol;
        System.arraycopy(matrix, 0, this.elementData, 0, size);
    }
    
    public final void set(Matrix3f m1) {
        this.elementData[0] = m1.m00;
        this.elementData[1] = m1.m01;
        this.elementData[2] = m1.m02;
        this.elementData[this.nCol] = m1.m10;
        this.elementData[this.nCol + 1] = m1.m11;
        this.elementData[this.nCol + 2] = m1.m12;
        this.elementData[2 * this.nCol] = m1.m20;
        this.elementData[2 * this.nCol + 1] = m1.m21;
        this.elementData[2 * this.nCol + 2] = m1.m22;
    }
    
    public final void set(Matrix3d m1) {
        this.elementData[0] = m1.m00;
        this.elementData[1] = m1.m01;
        this.elementData[2] = m1.m02;
        this.elementData[this.nCol] = m1.m10;
        this.elementData[this.nCol + 1] = m1.m11;
        this.elementData[this.nCol + 2] = m1.m12;
        this.elementData[2 * this.nCol] = m1.m20;
        this.elementData[2 * this.nCol + 1] = m1.m21;
        this.elementData[2 * this.nCol + 2] = m1.m22;
    }
    
    public final void set(Matrix4f m1) {
        this.elementData[0] = m1.m00;
        this.elementData[1] = m1.m01;
        this.elementData[2] = m1.m02;
        this.elementData[3] = m1.m03;
        this.elementData[this.nCol] = m1.m10;
        this.elementData[this.nCol + 1] = m1.m11;
        this.elementData[this.nCol + 2] = m1.m12;
        this.elementData[this.nCol + 3] = m1.m13;
        this.elementData[2 * this.nCol] = m1.m20;
        this.elementData[2 * this.nCol + 1] = m1.m21;
        this.elementData[2 * this.nCol + 2] = m1.m22;
        this.elementData[2 * this.nCol + 3] = m1.m23;
        this.elementData[3 * this.nCol] = m1.m30;
        this.elementData[3 * this.nCol + 1] = m1.m31;
        this.elementData[3 * this.nCol + 2] = m1.m32;
        this.elementData[3 * this.nCol + 3] = m1.m33;
    }
    
    public final void set(Matrix4d m1) {
        this.elementData[0] = m1.m00;
        this.elementData[1] = m1.m01;
        this.elementData[2] = m1.m02;
        this.elementData[3] = m1.m03;
        this.elementData[this.nCol] = m1.m10;
        this.elementData[this.nCol + 1] = m1.m11;
        this.elementData[this.nCol + 2] = m1.m12;
        this.elementData[this.nCol + 3] = m1.m13;
        this.elementData[2 * this.nCol] = m1.m20;
        this.elementData[2 * this.nCol + 1] = m1.m21;
        this.elementData[2 * this.nCol + 2] = m1.m22;
        this.elementData[2 * this.nCol + 3] = m1.m23;
        this.elementData[3 * this.nCol] = m1.m30;
        this.elementData[3 * this.nCol + 1] = m1.m31;
        this.elementData[3 * this.nCol + 2] = m1.m32;
        this.elementData[3 * this.nCol + 3] = m1.m33;
    }
    
    public final void set(GMatrix m1) {
        if (m1.nRow < this.nRow || m1.nCol < this.nCol)
            throw new ArrayIndexOutOfBoundsException("m1 smaller than this matrix"); 
        System.arraycopy(m1.elementData, 0, this.elementData, 0, this.nRow * this.nCol);
    }
    
    public final int getNumRow() {
        return this.nRow;
    }
    
    public final int getNumCol() {
        return this.nCol;
    }
    
    public final double getElement(int row, int column) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        if (this.nCol <= column)
            throw new ArrayIndexOutOfBoundsException("column:" + column + " > matrix's nCol:" + this.nCol); 
        if (column < 0)
            throw new ArrayIndexOutOfBoundsException("column:" + column + " < 0"); 
        return this.elementData[row * this.nCol + column];
    }
    
    public final void setElement(int row, int column, double value) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        if (this.nCol <= column)
            throw new ArrayIndexOutOfBoundsException("column:" + column + " > matrix's nCol:" + this.nCol); 
        if (column < 0)
            throw new ArrayIndexOutOfBoundsException("column:" + column + " < 0"); 
        this.elementData[row * this.nCol + column] = value;
    }
    
    public final void getRow(int row, double[] array) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        if (array.length < this.nCol)
            throw new ArrayIndexOutOfBoundsException("array length:" + array.length + " smaller than matrix's nCol:" + this.nCol); 
        System.arraycopy(this.elementData, row * this.nCol, array, 0, this.nCol);
    }
    
    public final void getRow(int row, GVector vector) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        if (vector.getSize() < this.nCol)
            throw new ArrayIndexOutOfBoundsException("vector size:" + vector.getSize() + " smaller than matrix's nCol:" + this.nCol); 
        for (int i = 0; i < this.nCol; i++)
            vector.setElement(i, this.elementData[row * this.nCol + i]); 
    }
    
    public final void getColumn(int col, double[] array) {
        if (this.nCol <= col)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " > matrix's nCol:" + this.nCol); 
        if (col < 0)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " < 0"); 
        if (array.length < this.nRow)
            throw new ArrayIndexOutOfBoundsException("array.length:" + array.length + " < matrix's nRow=" + this.nRow); 
        for (int i = 0; i < this.nRow; i++)
            array[i] = this.elementData[i * this.nCol + col]; 
    }
    
    public final void getColumn(int col, GVector vector) {
        if (this.nCol <= col)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " > matrix's nCol:" + this.nCol); 
        if (col < 0)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " < 0"); 
        if (vector.getSize() < this.nRow)
            throw new ArrayIndexOutOfBoundsException("vector size:" + vector.getSize() + " < matrix's nRow:" + this.nRow); 
        for (int i = 0; i < this.nRow; i++)
            vector.setElement(i, this.elementData[i * this.nCol + col]); 
    }
    
    public final void get(Matrix3d m1) {
        m1.m00 = this.elementData[0];
        m1.m01 = this.elementData[1];
        m1.m02 = this.elementData[2];
        m1.m10 = this.elementData[this.nCol];
        m1.m11 = this.elementData[this.nCol + 1];
        m1.m12 = this.elementData[this.nCol + 2];
        m1.m20 = this.elementData[2 * this.nCol];
        m1.m21 = this.elementData[2 * this.nCol + 1];
        m1.m22 = this.elementData[2 * this.nCol + 2];
    }
    
    public final void get(Matrix3f m1) {
        m1.m00 = (float)this.elementData[0];
        m1.m01 = (float)this.elementData[1];
        m1.m02 = (float)this.elementData[2];
        m1.m10 = (float)this.elementData[this.nCol];
        m1.m11 = (float)this.elementData[this.nCol + 1];
        m1.m12 = (float)this.elementData[this.nCol + 2];
        m1.m20 = (float)this.elementData[2 * this.nCol];
        m1.m21 = (float)this.elementData[2 * this.nCol + 1];
        m1.m22 = (float)this.elementData[2 * this.nCol + 2];
    }
    
    public final void get(Matrix4d m1) {
        m1.m00 = this.elementData[0];
        m1.m01 = this.elementData[1];
        m1.m02 = this.elementData[2];
        m1.m03 = this.elementData[3];
        m1.m10 = this.elementData[this.nCol];
        m1.m11 = this.elementData[this.nCol + 1];
        m1.m12 = this.elementData[this.nCol + 2];
        m1.m13 = this.elementData[this.nCol + 3];
        m1.m20 = this.elementData[2 * this.nCol];
        m1.m21 = this.elementData[2 * this.nCol + 1];
        m1.m22 = this.elementData[2 * this.nCol + 2];
        m1.m23 = this.elementData[2 * this.nCol + 3];
        m1.m30 = this.elementData[3 * this.nCol];
        m1.m31 = this.elementData[3 * this.nCol + 1];
        m1.m32 = this.elementData[3 * this.nCol + 2];
        m1.m33 = this.elementData[3 * this.nCol + 3];
    }
    
    public final void get(Matrix4f m1) {
        m1.m00 = (float)this.elementData[0];
        m1.m01 = (float)this.elementData[1];
        m1.m02 = (float)this.elementData[2];
        m1.m03 = (float)this.elementData[3];
        m1.m10 = (float)this.elementData[this.nCol];
        m1.m11 = (float)this.elementData[this.nCol + 1];
        m1.m12 = (float)this.elementData[this.nCol + 2];
        m1.m13 = (float)this.elementData[this.nCol + 3];
        m1.m20 = (float)this.elementData[2 * this.nCol];
        m1.m21 = (float)this.elementData[2 * this.nCol + 1];
        m1.m22 = (float)this.elementData[2 * this.nCol + 2];
        m1.m23 = (float)this.elementData[2 * this.nCol + 3];
        m1.m30 = (float)this.elementData[3 * this.nCol];
        m1.m31 = (float)this.elementData[3 * this.nCol + 1];
        m1.m32 = (float)this.elementData[3 * this.nCol + 2];
        m1.m33 = (float)this.elementData[3 * this.nCol + 3];
    }
    
    public final void get(GMatrix m1) {
        if (m1.nRow < this.nRow || m1.nCol < this.nCol)
            throw new IllegalArgumentException("m1 matrix is smaller than this matrix."); 
        if (m1.nCol == this.nCol) {
            System.arraycopy(this.elementData, 0, m1.elementData, 0, this.nRow * this.nCol);
        } else {
            for (int i = 0; i < this.nRow; i++)
                System.arraycopy(this.elementData, i * this.nCol, m1.elementData, i * m1.nCol, this.nCol); 
        } 
    }
    
    public final void setRow(int row, double[] array) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        if (array.length < this.nCol)
            throw new ArrayIndexOutOfBoundsException("array length:" + array.length + " < matrix's nCol=" + this.nCol); 
        System.arraycopy(array, 0, this.elementData, row * this.nCol, this.nCol);
    }
    
    public final void setRow(int row, GVector vector) {
        if (this.nRow <= row)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " > matrix's nRow:" + this.nRow); 
        if (row < 0)
            throw new ArrayIndexOutOfBoundsException("row:" + row + " < 0"); 
        int vecSize = vector.getSize();
        if (vecSize < this.nCol)
            throw new ArrayIndexOutOfBoundsException("vector's size:" + vecSize + " < matrix's nCol=" + this.nCol); 
        for (int i = 0; i < this.nCol; i++)
            this.elementData[row * this.nCol + i] = vector.getElement(i); 
    }
    
    public final void setColumn(int col, double[] array) {
        if (this.nCol <= col)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " > matrix's nCol=" + this.nCol); 
        if (col < 0)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " < 0"); 
        if (array.length < this.nRow)
            throw new ArrayIndexOutOfBoundsException("array length:" + array.length + " < matrix's nRow:" + this.nRow); 
        for (int i = 0; i < this.nRow; i++)
            this.elementData[i * this.nCol + col] = array[i]; 
    }
    
    public final void setColumn(int col, GVector vector) {
        if (this.nCol <= col)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " > matrix's nCol=" + this.nCol); 
        if (col < 0)
            throw new ArrayIndexOutOfBoundsException("col:" + col + " < 0"); 
        int vecSize = vector.getSize();
        if (vecSize < this.nRow)
            throw new ArrayIndexOutOfBoundsException("vector size:" + vecSize + " < matrix's nRow=" + this.nRow); 
        for (int i = 0; i < this.nRow; i++)
            this.elementData[i * this.nCol + col] = vector.getElement(i); 
    }
    
    public final void mulTransposeBoth(GMatrix m1, GMatrix m2) {
        mul(m2, m1);
        transpose();
    }
    
    public final void mulTransposeRight(GMatrix m1, GMatrix m2) {
        if (m1.nCol != m2.nCol || this.nRow != m1.nRow || this.nCol != m2.nRow)
            throw new ArrayIndexOutOfBoundsException("matrices mismatch"); 
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++) {
                double sum = 0.0D;
                for (int k = 0; k < m1.nCol; k++)
                    sum += m1.elementData[i * m1.nCol + k] * m2.elementData[j * m2.nCol + k]; 
                this.elementData[i * this.nCol + j] = sum;
            } 
        } 
    }
    
    public final void mulTransposeLeft(GMatrix m1, GMatrix m2) {
        transpose(m1);
        mul(m2);
    }
    
    public final void transpose() {
        for (int i = 0; i < this.nRow; i++) {
            for (int j = i + 1; j < this.nCol; j++) {
                double tmp = this.elementData[i * this.nCol + j];
                this.elementData[i * this.nCol + j] = this.elementData[j * this.nCol + i];
                this.elementData[j * this.nCol + i] = tmp;
            } 
        } 
    }
    
    public final void transpose(GMatrix m1) {
        set(m1);
        transpose();
    }
    
    public String toString() {
        String nl = System.getProperty("line.separator");
        StringBuffer out = new StringBuffer("[");
        out.append(nl);
        for (int i = 0; i < this.nRow; i++) {
            out.append("  [");
            for (int j = 0; j < this.nCol; j++) {
                if (0 < j)
                    out.append("\t"); 
                out.append(this.elementData[i * this.nCol + j]);
            } 
            if (i + 1 < this.nRow) {
                out.append("]");
                out.append(nl);
            } else {
                out.append("] ]");
            } 
        } 
        return out.toString();
    }
    
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.nRow * this.nCol; i++) {
            long bits = Double.doubleToLongBits(this.elementData[i]);
            hash ^= (int)(bits ^ bits >> 32L);
        } 
        return hash;
    }
    
    public boolean equals(GMatrix m1) {
        if (m1 == null)
            return false; 
        if (m1.nRow != this.nRow)
            return false; 
        if (m1.nCol != this.nCol)
            return false; 
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++) {
                if (this.elementData[i * this.nCol + j] != m1.elementData[i * this.nCol + j])
                    return false; 
            } 
        } 
        return true;
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof GMatrix && equals((GMatrix)o1));
    }
    
    public boolean epsilonEquals(GMatrix m1, float epsilon) {
        if (m1.nRow != this.nRow)
            return false; 
        if (m1.nCol != this.nCol)
            return false; 
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++) {
                if (epsilon < Math.abs(this.elementData[i * this.nCol + j] - m1.elementData[i * this.nCol + j]))
                    return false; 
            } 
        } 
        return true;
    }
    
    public boolean epsilonEquals(GMatrix m1, double epsilon) {
        if (m1.nRow != this.nRow)
            return false; 
        if (m1.nCol != this.nCol)
            return false; 
        for (int i = 0; i < this.nRow; i++) {
            for (int j = 0; j < this.nCol; j++) {
                if (epsilon < Math.abs(this.elementData[i * this.nCol + j] - m1.elementData[i * this.nCol + j]))
                    return false; 
            } 
        } 
        return true;
    }
    
    public final double trace() {
        int min = (this.nRow < this.nCol) ? this.nRow : this.nCol;
        double trace = 0.0D;
        for (int i = 0; i < min; i++)
            trace += this.elementData[i * this.nCol + i]; 
        return trace;
    }
    
    public final void setScale(double scale) {
        setZero();
        int min = (this.nRow < this.nCol) ? this.nRow : this.nCol;
        for (int i = 0; i < min; i++)
            this.elementData[i * this.nCol + i] = scale; 
    }
    
    private void setDiag(int i, double value) {
        this.elementData[i * this.nCol + i] = value;
    }
    
    private double getDiag(int i) {
        return this.elementData[i * this.nCol + i];
    }
    
    private double dpythag(double a, double b) {
        double absa = Math.abs(a);
        double absb = Math.abs(b);
        if (absa > absb) {
            if (absa == 0.0D)
                return 0.0D; 
            double d = absb / absa;
            if (Math.abs(d) <= Double.MIN_VALUE)
                return absa; 
            return absa * Math.sqrt(1.0D + d * d);
        } 
        if (absb == 0.0D)
            return 0.0D; 
        double term = absa / absb;
        if (Math.abs(term) <= Double.MIN_VALUE)
            return absb; 
        return absb * Math.sqrt(1.0D + term * term);
    }
    
    public final int SVD(GMatrix u, GMatrix w, GMatrix v) {
        if (u.nRow != this.nRow || u.nCol != this.nRow)
            throw new ArrayIndexOutOfBoundsException("The U Matrix invalid size"); 
        if (v.nRow != this.nCol || v.nCol != this.nCol)
            throw new ArrayIndexOutOfBoundsException("The V Matrix invalid size"); 
        if (w.nCol != this.nCol || w.nRow != this.nRow)
            throw new ArrayIndexOutOfBoundsException("The W Matrix invalid size"); 
        int m = this.nRow;
        int n = this.nCol;
        int imax = (m > n) ? m : n;
        double[] A = u.elementData;
        double[] V = v.elementData;
        int l = 0, nm = 0;
        double[] rv1 = new double[n];
        get(u);
        int i;
        for (i = m; i < imax; i++) {
            for (int k = 0; k < imax; k++)
                A[i * m + k] = 0.0D; 
        } 
        for (int j = n; j < imax; j++) {
            for (i = 0; i < imax; i++)
                A[i * m + j] = 0.0D; 
        } 
        w.setZero();
        double anorm = 0.0D, scale = anorm, g = scale;
        i = 0;
    }
    
    private void swapRows(int i, int j) {
        for (int k = 0; k < this.nCol; k++) {
            double tmp = this.elementData[i * this.nCol + k];
            this.elementData[i * this.nCol + k] = this.elementData[j * this.nCol + k];
            this.elementData[j * this.nCol + k] = tmp;
        } 
    }
    
    public final int LUD(GMatrix LU, GVector permutation) {
        if (this.nRow != this.nCol)
            throw new ArrayIndexOutOfBoundsException("not a square matrix"); 
        int n = this.nRow;
        if (n != LU.nRow)
            throw new ArrayIndexOutOfBoundsException("this.nRow:" + n + " != LU.nRow:" + LU.nRow); 
        if (n != LU.nCol)
            throw new ArrayIndexOutOfBoundsException("this.nCol:" + n + " != LU.nCol:" + LU.nCol); 
        if (permutation.getSize() < n)
            throw new ArrayIndexOutOfBoundsException("permutation.size:" + permutation.getSize() + " < this.nCol:" + n); 
        if (this != LU)
            LU.set(this); 
        int even = 1;
        double[] a = LU.elementData;
        for (int i = 0; i < n; i++)
            permutation.setElement(i, i); 
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < j; k++) {
                double sum = a[k * n + j];
                for (int i1 = 0; i1 < k; i1++) {
                    if (a[k * n + i1] != 0.0D && a[i1 * n + j] != 0.0D)
                        sum -= a[k * n + i1] * a[i1 * n + j]; 
                } 
                a[k * n + j] = sum;
            } 
            double big = 0.0D;
            int imax = j;
            for (int m = j; m < n; m++) {
                double d1 = a[m * n + j];
                for (int i1 = 0; i1 < j; i1++) {
                    if (a[m * n + i1] != 0.0D && a[i1 * n + j] != 0.0D)
                        d1 -= a[m * n + i1] * a[i1 * n + j]; 
                } 
                a[m * n + j] = d1;
                double dum = Math.abs(d1);
                if (dum >= big) {
                    big = dum;
                    imax = m;
                } 
            } 
            if (j != imax) {
                LU.swapRows(imax, j);
                double tmp = permutation.getElement(imax);
                permutation.setElement(imax, permutation.getElement(j));
                permutation.setElement(j, tmp);
                even = -even;
            } 
            if (j != n - 1) {
                double d = 1.0D / a[j * n + j];
                for (int i1 = j + 1; i1 < n; i1++)
                    a[i1 * n + j] = a[i1 * n + j] * d; 
            } 
        } 
        return even;
    }
}
