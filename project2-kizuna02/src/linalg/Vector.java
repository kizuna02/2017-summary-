package linalg;

/**
 * * A class that represents a multidimensional real-valued (double) vector and
 * supports various vector computations required in linear algebra.
 *
 * Class and method comments are in JavaDoc:
 * https://en.wikipedia.org/wiki/Javadoc
 *
 * @author ssanner@mie.utoronto.ca, <kizuna021997@gmail.com>
 *
 */
public class Vector {

    private int _nDim;       // Dimension of the Vector; nomenclature: _ for data member, n for integer
    private double[] _adVal; // Contents of the Vector; nomenclature: _ for data member, a for array, d for double

    /**
     * Constructor: allocates space for a new vector of dimension dim
     *
     * @param dim
     * @throws LinAlgException if vector dimension is < 1
     */
    public Vector(int dim) throws LinAlgException {
        if (dim <= 0) {
            throw new LinAlgException("Vector dimension " + dim + " cannot be less than 1");
        }
        this._nDim = dim;
        this._adVal = new double[dim]; // Entries will be automatically initialized to 0.0
    }

    /**
     * Copy constructor: makes a new copy of an existing Vector v (note: this
     * explicitly allocates new memory and copies over content)
     *
     * @param v
     */
    public Vector(Vector v) {
       this._nDim = v._nDim;
       this._adVal = new double[v._nDim];
       for(int i = 0; i < v._nDim; i++){
           this._adVal[i] = v._adVal[i];//copying by value of each element
       }
    }

    /**
     * Constructor: creates a new Vector with dimension and values given by init
     *
     * @param init: a String formatted like "[ -1.2 2.0 3.1 5.8 ]" (must start
     * with [ and end with ])
     * @throws LinAlgException if init is not properly formatted (missing [ or
     * ], or improperly formatted number)
     */
    public Vector(String init) throws LinAlgException {

        // The following says split init on whitespace (\\s) into an array of Strings
        String[] split = init.split("\\s");
        // Uncomment the following to see what split produces
        //for (int i = 0; i < split.length; i++) {
        //    System.out.println(i + ". " + split[i]);
        //}

        if (!split[0].equals("[") || !split[split.length - 1].equals("]")) {
            throw new LinAlgException("Malformed vector initialization: missing [ or ] in " + init);
        }

        // We don't count the [ and ] in the dimensionality
        _nDim = split.length - 2;
        _adVal = new double[_nDim];

        // Parse each number from init and add it to the Vector in order (note the +1 offset to account for [)
        for (int index = 0; index < _nDim; index++) {
            try {
                set(index, Double.parseDouble(split[index + 1]));
            } catch (NumberFormatException e) {
                throw new LinAlgException("Malformed vector initialization: could not parse " + split[index + 1] + " in " + init);
            }
        }
    }

    /**
     * Overrides method toString() on Object: converts the class to a human
     * readable String
     *
     * Note 1: this is invoked *automatically* when the object is listed where a
     * String is expected, e.g., "System.out.println(v);" is actually equivalent
     * to "System.out.println(v.toString());"
     *
     * Note 2: for debugging purposes, you should always define a toString()
     * method on a class you define
     * @return 
     */
     // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
    @Override
    public String toString() {
        // We could just repeatedly append to an existing String, but that copies the String each
        // time, whereas a StringBuilder simply appends new characters to the end of the String
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < _nDim; i++) {
            sb.append(String.format(" %6.3f ", _adVal[i])); // Append each vector value in order
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Overrides address equality check on Object: allows semantic equality
     * testing of vectors, i.e., here we say two objects are equal iff they have
     * the same dimensions and values match at all indices
     *
     * Note: you should almost always define equals() since the default equals()
     * on Object simply tests that two objects occupy the same space in memory
     * (are actually the same instance), but does not test that two objects may
     * be different instances but have the same content
     *
     * @param o the object to compare to
     */
    @Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
    public boolean equals(Object o) {
        if (o instanceof Vector) {
            Vector v = (Vector) o; // This is called a cast (or downcast)... we can do it since we
            // know from the if statement that o is actually of subtype Vector
            if (_nDim != v._nDim) {
                return false; // Two Vectors cannot be equal if they don't have the same dimension
            }
            for (int index = 0; index < _nDim; index++) {
                if (_adVal[index] != v._adVal[index]) {
                    return false; // If two Vectors mismatch at any index, they are not equal
                }
            }
            return true; // Everything matched... objects are equal!
        } else // if we get here "(o instanceof Vector)" was false
        {
            return false; // Two objects cannot be equal if they don't have the same class type
        }
    }

    /**
     * Get the dimension of this vector
     *
     * @return 
     * @return: the dimensionality of this Vector
     */
    public int getDim() {
        //return the dimension of vector
        return this._nDim;
    }

    /**
     * Returns the value of this vector at the given index (remember: array
     * indices start at 0)
     *
     * @param index
     * @return
     * @throws LinAlgException if array index is out of bounds (see throw
     * examples above)
     */
    public double get(int index) throws LinAlgException {
        if (index < 0 || index >= this._nDim) {
            throw new LinAlgException("Invalid index.");
        }
        return this._adVal[index];
    }

    /**
     * Set the value val of the vector at the given index (remember: array
     * indices start at 0)
     *
     * @param index
     * @param val
     * @throws LinAlgException if array index is out of bounds (see throw
     * examples above)
     */
    public void set(int index, double val) throws LinAlgException {
         if (index < 0 || index >= this._nDim) {
            throw new LinAlgException("Invalid index.");
        }
             this._adVal[index] = val;//pass the value to the element of the index
    }

    /**
     * Change the dimension of this Vector by *reallocating array storage* and
     * copying content over ... if new dim is larger than current dim then the
     * additional indices take value 0.0 ... if new dim is smaller than current
     * dim then any indices in current vector beyond current dim are simply lost
     *
     * @param new_dim
     * @throws LinAlgException if vector dimension is < 1
     */
    public void changeDim(int new_dim) throws LinAlgException {
        double[] newVal = new double[new_dim];//dummy variable
        if (new_dim < 1){
           throw new LinAlgException("Invalid vector dimension."); 
        }
        if(new_dim > this._nDim){
            System.arraycopy(this._adVal, 0, newVal, 0, _nDim);
            for(int i = this._nDim; i < new_dim; i++){
               newVal[i] = 0;//initializing
            }
        }else if(new_dim <= this._nDim){
            System.arraycopy(this._adVal, 0, newVal, 0, new_dim);
           
        }
        this._adVal = newVal;//copying by reference
        this._nDim = new_dim;
    }

    /**
     * This adds a scalar d to all elements of *this* Vector (should modify
     * *this*)
     *
     * @param d
     */
    public void scalarAddInPlace(double d) {
        for (int index = 0; index < this._nDim; index++) {
            this._adVal[index] += d;//add value d to each element
        }
    }

    /**
     * This creates a new Vector, adds a scalar d to it, and returns it (should
     * not modify *this*)
     *
     * @param d
     * @return new Vector after scalar addition
     */
    public Vector scalarAdd(double d) {
        Vector add = new Vector(this);
        add.scalarAddInPlace(d);
        return add; //retrun a copy of the vector after scalar addition
    }

    /**
     * This multiplies a scalar d by all elements of *this* Vector (should
     * modify *this*)
     *
     * @param d
     */
    public void scalarMultInPlace(double d) {
       for (int index = 0; index < this._nDim; index++){
           this._adVal[index] *= d;//multiply d to each element
       }
        
    }

    /**
     * This creates a new Vector, multiplies it by a scalar d, and returns it
     * (should not modify *this*)
     *
     * @param d
     * @return new Vector after scalar addition
     */
    public Vector scalarMult(double d) {
       Vector mult = new Vector(this);
       mult.scalarMultInPlace(d);
       return mult;//return a copy of the Vector after scalar multiplication
    }

    /**
     * Performs an element-wise addition of v to *this*, modifies *this*
     *
     * @param v
     * @throws LinAlgException if dimensions of the two operand vectors do not
     * match
     */
    public void elementwiseAddInPlace(Vector v) throws LinAlgException {
      if (this._nDim != v.getDim()){
          throw new LinAlgException("Dimensions of the 2 operand vecotrs do not match.");
      }
      for (int i = 0; i < _nDim; i++){
          this._adVal[i] += v._adVal[i];//add individually
      }
    }

    /**
     * Performs an element-wise addition of *this* and v and returns a new Vector
     * with result
     *
     * @param v
     * @return
     * @throws LinAlgException if dimensions of the two operand vectors do not
     * match
     */
    public Vector elementwiseAdd(Vector v) throws LinAlgException {
       if (this._nDim != v.getDim()){
          throw new LinAlgException("Dimensions of the 2 operand vecotrs do not match.");
      }
       Vector eleAdd = new Vector(this);
       eleAdd.elementwiseAddInPlace(v);
       return eleAdd;//return a copy of the Vector after element-wise addition
    }

    /**
     * Performs an elementwise multiplication of v and *this*, modifies *this*
     *
     * @param v
     * @throws LinAlgException if dimensions of the two operand vectors do not
     * match
     */
    public void elementwiseMultInPlace(Vector v) throws LinAlgException {
        if (this._nDim != v.getDim()){
          throw new LinAlgException("Dimensions of the 2 operand vecotrs do not match.");
      }
       for (int i = 0; i < this._nDim; i++){
           this._adVal[i] *= v._adVal[i];//multiply individually
       }
        
    }

    /**
     * Performs an elementwise multiplication of *this* and v and returns a new
     * Vector with result
     *
     * @param v
     * @return
     * @throws LinAlgException if dimensions of the two operand vectors do not
     * match
     */
    public Vector elementwiseMult(Vector v) throws LinAlgException {
       if (this._nDim != v.getDim()){
          throw new LinAlgException("Dimensions of the 2 operand vecotrs do not match.");
      }
       Vector eleMult = new Vector(this);
       eleMult.elementwiseMultInPlace(v);
       return eleMult;//return a copy of current Vector after element-wise multiplication
    }

    /**
     * Performs an inner product of Vectors v1 and v2 and returns the scalar
     * result
     *
     * @param v1
     * @param v2
     * @return
     * @throws LinAlgException
     */
    public static double InnerProd(Vector v1, Vector v2) throws LinAlgException {
        double scalar_result = 0;
       if (v1.getDim() != v2.getDim()){
           throw new LinAlgException("Dimensions of the 2 operand vecotrs do not match.");
       }
       for (int count = 0; count < v1.getDim(); count++){
           scalar_result += v1.get(count)*v2.get(count);//element-wise multiplication;then sum up
       }                                                //also use getter since it is static
       return scalar_result;
    }
}
