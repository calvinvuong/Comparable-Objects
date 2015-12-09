// Calvin Vuong
// APCS1 pd5
// HW45 -- Come Together
// 2015-12-10

public class Rational implements Comparable{

    private int _numerator = 0;
    private int _denominator = 1;

    //default constructor creates Rational w value 0/1
    public Rational() {
	_numerator = 0;
	_denominator = 1;
    }


    //overloaded constructor to allow initialization
    //pre: n, d are ints
    //post: n is numerator, d denominator
    //      if d==0, set d=1
    public Rational( int n, int d ) {
	this();
	if ( d != 0 ) {
	    _numerator = n;
	    _denominator = d;
	}
	else {
	    System.out.println( "Invalid number. Set to 0/1." );
	}
    }


    //return String representation of this Rational number
    public String toString() {
	return _numerator + "/" + _denominator;
    }


    //return floating-pt approx of this Rational number
    public double floatValue() {
	return (double)_numerator / _denominator;
    }


    //multiply this Rational number by another
    //pre:  input is not null
    //post: this Rational becomes product
    //      input Object unchanged
    public void multiply( Rational r ) {
	_numerator = this._numerator * r._numerator;
	_denominator = this._denominator + r._denominator;
    }


    //divide this Rational number by another
    //pre:  input is not null
    //post: this Rational becomes quotient
    //      input Object unchanged
    public void divide( Rational r ) {
	if ( r._numerator != 0 ) {
	    _numerator = _numerator * r._denominator;
	    _denominator = _denominator * r._numerator;
	}
	else {
	    System.out.println( "Div by 0 error. Values unchanged." );
	}
    }


    //add this Rational number to input Rational
    //pre:  input is not null
    //post: this Rational becomes sum
    //      input Object unchanged
    //      This Rational may remain unreduced
    public void add( Rational r ) {
	_numerator = _numerator * r._denominator + r._numerator * _denominator;
	_denominator = _denominator * r._denominator;
    }


    //subtract input Rational from this Rational number
    //pre:  input is not null
    //post: this Rational becomes difference
    //      input Object unchanged
    //      This Rational may remain unreduced
    public void subtract( Rational r ) {
	_numerator = _numerator * r._denominator - r._numerator * _denominator;
	_denominator = _denominator * r._denominator;
    }


    //simplify p/q
    //pre:  n/a
    //post: p/q reduced to simplest form
    public void reduce() 
    {
	int g = gcd(); //perform calculation only once
	_numerator = _numerator / g;
	_denominator = _denominator / g;
    }

		
    //return greatest common divisor of numerator and denominator 
    //pre:  n/a
    //post: this Rational unchanged
    public int gcd()
    {
	return gcd(_numerator, _denominator);
    }


    //calculate greatest common divisor of two integers via Euler's method
    //pre:  int inputs
    //post: returns GCD of inputs
    public static int gcd( int a, int b )
    {
        while ( b != 0 ) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    /* Takes an Object parameter and returns:
       positive int if this Rational greater
       negative int if other Rational greater
       0 if Rationals have same value
       throws NullPointerException if other Object is null
       throws ClassCastException if other Object is not a comparable Object
    */
    public int compareTo( Object other ) {
	if (other == null){ //if null object
	    throw new NullPointerException("You compared a null object.");
	}
	if (!(other instanceof Comparable)){ //if both objects cannot be compared...
	    throw new ClassCastException("You cannot compare two non-comparable objects.");
	}

	int thisNumerator, otherNumerator;
	
	//typecast to access instance var	
	thisNumerator = _numerator * ((Rational) other)._denominator;
	otherNumerator = _denominator * ((Rational) other)._numerator;
	
	return thisNumerator - otherNumerator;
    }


    //override equals method
    public boolean equals( Object rightSide ) {
 
        //First, check for aliasing.
        boolean retVal = this==rightSide;
 
        //Next, if this and input are different Objects,
        if ( !retVal )
	    retVal = this.compareTo( rightSide ) == 0;
 
        return retVal;
    }


    //main method for testing
    public static void main(String[] args) {
	
	Rational r = new Rational( 3, 7 );
	Rational s = new Rational();
	Rational t = new Rational( 8, 5 );

	Rational u = new Rational( 1, 2 );
	Rational v = new Rational( 2, 3 );
	Rational w = new Rational( 8, 12 );
	
	String bad = new String("hola!");
	Rational blank; //null class
	
	//new test cases
	System.out.println(v.compareTo(w)); // 0
	System.out.println(v.compareTo(t)); // -14
	System.out.println(v.compareTo(bad)); //ClassCastException
	//	System.out.println(v.compareTo(blank)); //NullPointerException
	

	/* OLD TEST CASES...
	System.out.println("r: " + r );
	System.out.println("s: " +  s );
	System.out.println("t: " +  t );

	System.out.println( r + " represented as a floating pt num: " 
			    + r.floatValue() );

	System.out.print( r + " * " + t + " = ");
	r.multiply(t);
	System.out.println(r);

	System.out.print( r + " / " + t + " = ");
	r.divide(t);
	System.out.println(r);

	System.out.print( u + " + " + v + " = ");
	u.add(v);
	System.out.println(u);
	
	System.out.print( u + " - " + v + " = ");
	u.subtract(v);
	System.out.println(u);

	System.out.println("GCD of " + r + " = " + r.gcd() );
	System.out.println("GCD of " + t + " = " + t.gcd() );

	System.out.print( r + " in reduced form = ");
	r.reduce();
	System.out.println(r);

	System.out.println( "\nNow testing static gcd...");
	System.out.println( Rational.gcd(100,9) );
	System.out.println( Rational.gcd(245,25) );

	System.out.print( v + " == " + w + " ? ");
	System.out.println( v.equals(w) );
	System.out.println( w.equals(v) );
				
	//tests if comparing to non-Rational will return error or not
	System.out.println( v.compareTo("hi"));
	*/
    }//end main


}//end class Rational
