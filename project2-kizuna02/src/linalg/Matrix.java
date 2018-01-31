package linalg;


/*** A class that represents a two dimensional real-valued (double) matrix
 *   and supports various matrix computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author ssanner@mie.utoronto.ca, <kizuna021997@gmail.com>
 *
 */
public class Matrix {

	private int _nRows; // Number of rows in this matrix; nomenclature: _ for data member, n for integer
	private int _nCols; // Number of columns in this matrix; nomenclature: _ for data member, n for integer
	// TODO: add your own data member to represent the matrix content
	//       you could use a 2D array, or an array of Vectors (e.g., for each row)
	private double[][] matrix;
	/** Allocates a new matrix of the given row and column dimensions
	 * 
	 * @param rows
	 * @param cols
	 * @throws LinAlgException if either rows or cols is <= 0
	 */
	   public Matrix(int rows, int cols) throws LinAlgException {
        if (rows <= 0 || cols <= 0) {
            throw new LinAlgException("Either rows or cols is <= 0.");
        }
        this._nRows = rows;
        this._nCols = cols;
        this.matrix = new double[_nRows][_nCols];
    }
	
	/** Copy constructor: makes a new copy of an existing Matrix m
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param m
	 */
    public Matrix(Matrix m) {
        this._nRows = m._nRows;
        this._nCols = m._nCols;
        this.matrix = new double[this._nRows][this._nCols];
        for (int i = 0; i < this._nRows; i++) {
            for (int j = 0; j < this._nCols; j++) {
                this.matrix[i][j] = m.matrix[i][j];//copying from each row and col
            }
        }
    }
	/** Constructs a String representation of this Matrix
	 * 
     * @return 
	 */
        @Override
	   public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this._nRows; i++) {
            sb.append("[");
            for (int j = 0; j < this._nCols; j++) {
                sb.append(String.format(" %6.3f ", this.matrix[i][j]));
            }
            sb.append(" ]");
            sb.append("\n");
        }
        return sb.toString();
    }

	/** Tests whether another Object o (most often a matrix) is a equal to *this*
	 *  (i.e., are the dimensions the same and all elements equal each other?)
	 * 
	 * @param o the object to compare to
     * @return 
	 */
        @Override
	public boolean equals(Object o) {
		if(o instanceof Matrix){
                    Matrix m = (Matrix) o;//downcast since o is a subtype of Matrix
                    if ((this._nRows != m._nRows) || (this._nCols != m._nCols)){
                        return false;
                    }
                    for(int i = 0; i < this._nRows; i++){
                        for(int j = 0; j < this._nCols; j++){
                            if(this.matrix[i][j] != m.matrix[i][j]){
                                return false;
                            }
                        }
                    }
                    return true;
                }else{
                    return false;
                }
	}

   

	/** Return the number of rows in this matrix
	 *   
	 * @return 
	 */
	public int getNumRows() {
		return this._nRows;
	}

	/** Return the number of columns in this matrix
	 *   
	 * @return 
	 */
	public int getNumCols() {
		return this._nCols;
	}

	/** Return the scalar value at the given row and column of the matrix
	 * 
	 * @param row
	 * @param col
	 * @return
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public double get(int row, int col) throws LinAlgException {
		if((row < 0)||(col < 0)||(row >= this._nRows)||(col >= this._nCols)){
                    throw new LinAlgException("Row or col indices are out of bounds.");
                }//define the boundaries of matrix
                return this.matrix[row][col];
	}
	
	/** Return the Vector of numbers corresponding to the provided row index
	 * 
	 * @param row
	 * @return
	 * @throws LinAlgException if row is out of bounds
	 */
	public Vector getRow(int row) throws LinAlgException {
           Matrix m = new Matrix(this);
           if((row < 0) || (row >= this._nRows)){
               throw new LinAlgException("Row is out of bounds.");
           }
          Vector r = new Vector(this._nCols);
          for(int i = 0; i < this._nCols; i++){
              r.set(i, m.matrix[row][i]);//set the numbers of vector
          }
          return r;
	}

	/** Set the row and col of this matrix to the provided val
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public void set(int row, int col, double val) throws LinAlgException {
            if((row < 0)||(col < 0)||(row >= this._nRows)||(col >= this._nCols)){
                    throw new LinAlgException("Row or col indices are out of bounds.");
                }
          this.matrix[row][col] = val;
            
	}
	
	/** Return a new Matrix that is the transpose of *this*, i.e., if "transpose"
	 *  is the transpose of Matrix m then for all row, col: transpose[row,col] = m[col,row]
	 *  (should not modify *this*)
	 * 
	 * @return
	 * @throws LinAlgException
	 */
	public Matrix transpose() throws LinAlgException {
		Matrix transpose = new Matrix(_nCols, _nRows);
		for (int row = 0; row < _nRows; row++) {
			for (int col = 0; col < _nCols; col++) {
				transpose.set(col, row, get(row,col));
			}
		}
		return transpose;
	}

	/** Return a new Matrix that is the square identity matrix (1's on diagonal, 0's elsewhere) 
	 *  with the number of rows, cols given by size.  E.g., if size = 3 then the returned matrix
	 *  would be the following:
	 *  
	 *  [ 1 0 0 ]
	 *  [ 0 1 0 ]
	 *  [ 0 0 1 ]
	 * 
	 * @param size
	 * @return
	 * @throws LinAlgException if the size is <= 0
	 */
	public static Matrix GetIdentity(int size) throws LinAlgException {
            if (size <= 0){
                throw new LinAlgException("Invalid size.");
            }
            Matrix iden = new Matrix(size, size);
            for(int row = 0; row < size; row++){
                for(int col = 0; col < size; col++){
                    if(col == row){
                        iden.set(row, col, 1);//initializing elements on diagonal 
                    }else{
                       iden.set(row, col, 0);//initializing the rest of elements
                    }
                }
            }
           return iden;
	}
	
	/** Returns the Matrix result of multiplying Matrix m1 and m2
	 *  (look up the definition of matrix multiply if you don't remember it)
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 * @throws LinAlgException if m1 columns do not match the size of m2 rows
	 */
	   public static Matrix Multiply(Matrix m1, Matrix m2) throws LinAlgException {
        if (m1._nCols != m2._nRows) {
            throw new LinAlgException("m1 columns do not match the size of m2 rows");
        }
        Matrix result = new Matrix(m1._nRows, m2._nCols);
        double[][] mult = new double[m1._nRows][m2._nCols];//dummy variable
        for (int i = 0; i < m1._nRows; i++) {
            for (int j = 0; j < m2._nCols; j++) {
                mult[i][j] = 0;//initialize the resultant matrix
            }
        }
        for (int i = 0; i < m1._nRows; i++) { // updating row of m1
            for (int j = 0; j < m2._nCols; j++) { // updating col of m2
                for (int k = 0; k < m1._nCols; k++) { //perform element-wise multiplication for row k of m1 and col k of m2
                    mult[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        for(int i = 0; i < m1._nRows; i++){
            for (int j = 0; j < m2._nCols; j++){
                result.set(i, j, mult[i][j]);
            }
        }
       return result;
        
    }
	
	/** Returns the Vector result of multiplying Matrix m by Vector v (assuming v is a column vector)
	 * 
	 * @param m
	 * @param v
	 * @return
	 * @throws LinAlgException if m columns do match the size of v
	 */
	public static Vector Multiply(Matrix m, Vector v) throws LinAlgException {
            if(v.getDim() != m._nCols){
                throw new LinAlgException("m columns do not match the size of v.");
            }
            Vector x = new Vector(m._nRows);//length of the resultant Vector array
            double[] vm = new double[m._nRows];//store the results as an array
            for (int i = 0; i < m._nRows; i++) {//updating row rumber
                for (int k = 0; k < m._nCols; k++) {//perform element-wise multiplication of current row of m and v
                    vm[i] += (v.get(k)) * (m.matrix[i][k]);
                }
            }
            for(int i = 0; i < m._nRows; i++){
                x.set(i, vm[i]);//copy by value
            }
            return x;
	}

}
