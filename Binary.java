// Calvin Vuong
// APCS1 pd5
// HW43 -- This or That
// 2015-12-07

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n); //set _binNum to converted form of decimal
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	_binNum = s;
	_decNum = binToDec(s); //set _decNum to converted form of decimal
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum; //return binary value of Object
    }

    public int getDecNum(){
	return _decNum;
    }

    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	String bin = ""; //binary value to be returned
	while (n != 0){
	    bin = (n % 2) + bin; //add binary digits right to left; add remainder as a digit
	    n = n / 2; //repeat for quotient
	}
	return bin;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	if (n == 0){ //base case
	    return "";
	}
	else{ //recursive case
	    return decToBinR(n / 2) + (n % 2); //add remainder (digit) to bin conversion of quotient
	}
    }


    /*=====================================
      String binToDec(String) -- converts binary input to base-10
      pre:  s represents non-negative binary number
      post: returns int in decimal form equivalent to s
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int retInt = 0;
	for (int i = 0; i < s.length(); i++){ //iterate through s left to right
	    int digit = Integer.parseInt(s.substring(i, i+1)); //convert current digit into an int
	    retInt += digit * (int) Math.pow(2, s.length()-1 - i); //add to retInt digit times base 2 raised to an exp based on pos
	}
	return retInt;
    }


    /*=====================================
      String binToDecR(String) -- converts binary input to base-10, recursively
      pre:  s represents non-negative binary number
      post: returns binary as an int in decimal form
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ){
	if (s.length() == 0){ //base case
	    return 0;
	}
	else{ //recursive case
	    int binDigit = Integer.parseInt(s.substring(0,1)); //first bin digit
	    int binValue = binDigit * (int) Math.pow(2, s.length()-1); //value of this bin digit in base 10
	    String next = s.substring(1); //string of digits without the first one
	    return binValue + binToDecR(next); //add value of this digit to value of all others
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	if (this == other) { return true; } //true if this object and other are aliases
	//if not aliases...
	return this.compareTo(other) == 0;
    }


    /* Takes an Object parameter and returns:
       positive int if this Object greater
       negative int if other Object greater
       0 if Objects have same value
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

        //typecast or convert into a Rational for easy compare
	Rational thisAsRational = new Rational(_decNum, 1);
	Rational otherAsRational = null;
	if (other instanceof Rational){
	    otherAsRational = (Rational) other;
	}
	else if (other instanceof Binary){
	    otherAsRational = new Rational( ((Binary) other).getDecNum(), 1);
	}
	else if (other instanceof Hexadecimal){
	    otherAsRational = new Rational( ((Hexadecimal) other).getDecNum(), 1);
	}

        //compare through cross multiplication
        int thisNumerator, otherNumerator;

        //typecast to access instance var
        thisNumerator = thisAsRational.getNumerator() * otherAsRational.getDenominator();
        otherNumerator = thisAsRational.getDenominator() * otherAsRational.getNumerator();

        return thisNumerator - otherNumerator;

    }



    //main method for testing
    public static void main( String[] args ) {

	Binary b1 = new Binary("1101"); //13
	Rational r1 = new Rational(26, 2); //13
	Hexadecimal h1 = new Hexadecimal("D"); //13

	Binary b2 = new Binary("111"); //7
	Rational r2 = new Rational(7, 2); // 7/2
	Hexadecimal h2 = new Hexadecimal("AC23D7"); //large

	String bad = new String("hola!");
	Binary empty = null;

	System.out.println(b1.compareTo(r1)); //0
	System.out.println(b1.compareTo(h1)); //0
	System.out.println(r1.compareTo(h1)); //0
	
	System.out.println(b2.compareTo(r2)); //positive
	System.out.println(r2.compareTo(h2)); //negative
	
	System.out.println(b1.compareTo(bad)); //ClassCastException
	System.out.println(b1.compareTo(empty)); //NullPointerException

	/*OLD TEST CASES...
	System.out.println();
	System.out.println( "Testing ..." );

	//test recursive conversion methods
	System.out.println( "\nRecursive Methods");

	System.out.println(Binary.decToBin(3)); //11
	System.out.println(Binary.binToDec("11")); //should be 3
	System.out.println(Binary.decToBin(92)); //1011100
	System.out.println(Binary.binToDec("1011100")); //92

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	//tests toString() and non-recursive conversion methods as well
	System.out.println( "\n" + b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	*/
    }//end main()

} //end class