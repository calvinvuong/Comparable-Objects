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
	int exp = 0; //keeps track of what exponent to raise 2 to
	for (int i = s.length(); i > 0; i--){ //iterate through s right to left
	    int digit = Integer.parseInt(s.substring(i-1, i)); //convert current digit into an int
	    retInt += digit * (int) Math.pow(2, exp); //add to retInt digit times base 2 raised to the power of the current exponent
	    exp += 1;
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
    public static int binToDecR( String s ) { 
	return binToDecRHelper(s, 0); //start at exponent 1
    }

    /*
      helper method for binToDecR(String s, int exp)
      pre: s represents a non-negative binary number
           exp represents power to raise base 2 to
      post: returns binary as an int in decimal form
    */  
    public static int binToDecRHelper( String s, int exp ){
	if (s.length() == 1){ //base case
	    int digit = Integer.parseInt(s); //int form of remaining digit
	    return digit * (int)Math.pow(2, exp); //convert digit into decimal value
	}
	else{ //recursive case
	    int digit = Integer.parseInt(s.substring(s.length()-1, s.length())); //last most digit of s
	    String next = s.substring(0, s.length()-1); //string of digits without the last one
	    return (digit * (int)Math.pow(2, exp)) + binToDecRHelper(next, exp += 1); //convert digit into decimal value and add to conversion of rest of digits
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	//typecasts as necessary
	if (!(other instanceof Binary)) { return false; } //return false if not same Object types
	//typecasts as necessary
	boolean alias = this == other; //true if this object and other are aliases
	boolean value = this._binNum.equals(((Binary)other)._binNum); //true if binary number of this object is equivalent to that of other's
	return alias || value; //true if either they are aliases or values are equal
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	//comparing base 10 values is easiest approach
	//typecasts as necessary
	return this._decNum - ((Binary)other)._decNum;
    }


    //main method for testing
    public static void main( String[] args ) {

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

    }//end main()

} //end class