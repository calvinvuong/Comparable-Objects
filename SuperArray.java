/*  Calvin Vuong
    APCS1 pd5
    HW45 -- Come Together
    2015-12-10 */

//NEW METHODS FOR HW45 BELOW size()
public class SuperArray{
     
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;
    
    //position of last meaningful value
    private int _lastPos;
    
    //size of this instance of SuperArray
    private int _size;
    
    		
    //~~~~~METHODS~~~~~
    //default constructor --> initializes 10-item array
    public SuperArray() { 
    	_data = new Comparable[10];
    	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }
    
    		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 ){
	    foo = foo.substring( 0, foo.length()-1 );
	}
	foo += "]";
	return foo;
    }
    
    		
    //double capacity of this SuperArray
    private void expand() {
    	Comparable[] temp = new Comparable[ _data.length * 2 ];
    	for( int i = 0; i < _data.length; i++ ){
    	    temp[i] = _data[i];
    	}
    	_data = temp;
    }
    
    		
    //accessor -- return value at specified index
    public Comparable get( int index ) { 
	return _data[index];
    }
    
    		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) { 
	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }
    
    
    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) { 
        if (_lastPos == _data.length - 1){ //if no room to add...
            expand(); //expand to make room
        }
        _data[_lastPos+1] = newVal; //make pos after last meaningful one newVal
        _lastPos += 1;
        _size += 1;
    }
    
    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (index <= _size){ //index within bounds...
	    if (_lastPos == _data.length - 1){ //if no room to add...
		expand(); //expand to make room
	    }
	    Comparable displaced = null; //value of displaced element
	    //for every index up to lastPos, and one index after
	    //to account for one more element added
	    for (int i = 0; i <= _lastPos + 1; i++){
		if (i == index){ //if location of insert
		    displaced = _data[i];
		    _data[i] = newVal; //overwrite
		}
		else if (i > index){ //if after shift
		    //value of i becomes the next displaced
		    Comparable newDisplaced = _data[i]; 
		    _data[i] = displaced; //overwrite with previous displaced
		    displaced = newDisplaced; //update displaced
		}
	    } //close for loop
	    //update lastPos and size, since new element added
	    _lastPos += 1;
	    _size += 1;
	}
	else{//if index out of bounds, print error message
	    System.out.println("Index out of bounds");
	}
    }
    
    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) { 
        for (int i = index; i<= _lastPos - 1; i++){
            _data[i] = _data[i+1];
        }
        _lastPos -= 1;
        _size -= 1;
    }
    
    
    //return number of meaningful items in _data
    public int size() { return _size; }

    
    //return index of first occurrence of Comparable Object c
    public int linSearch(Comparable c){
	for (int i = 0; i < _size; i++){ //iterate left to right
	    if ( _data[i].equals(c) ) { //if Comparable at index i is identical to Comparable c...
		return i; //return index
	    }
	}
	//loop exits if no match; return -1 in this case
	return -1;
    }

    //returns true if Comparables in _data is sorted from smallest to largest
    //false otherwise
    public boolean isSorted(){
	for (int i = 0; i < _size-1; i++){ //iterate left to right up to and including 2nd to last Comparable
	    if ( _data[i].compareTo(_data[i+1]) > 0 ){ //if value of Comparable at this index is greater than Comparable at next index...
		return false; //the list is not sorted
	    }
	}
	return true;
    }

    //main method for testing
    public static void main( String[] args ) { 

	//NEW TEST CASES; TESTS linSearch() and isSorted()
	SuperArray a = new SuperArray();
	a.add(new Rational(0,1)); //0
	a.add(new Binary("1")); //1
	a.add(new Rational(5,5)); //1
	a.add(new Hexadecimal("3")); //3
	a.add(new Binary("11")); //3
	a.add(new Rational(10, 2)); //5
	a.add(new Hexadecimal("F")); //15
	a.add(new Rational(30,1));//30
	a.add(new Binary("101000")); //40
	a.add(new Binary("111111")); //63
	a.add(new Hexadecimal("CDE45")); //843333

	System.out.println(a);
	
	System.out.println(a.linSearch( new Binary("1111") )); //6
	System.out.println(a.linSearch( new Binary("100") )); //-1
	System.out.println(a.linSearch( new Hexadecimal("1") )); //1
	System.out.println(a.linSearch( new Rational(9, 3) )); //3

	System.out.println(a.isSorted()); //true

	SuperArray b = new SuperArray();
	b.add(new Rational(0,1)); //0
	b.add(new Binary("1")); //1
	b.add(new Rational(5,5)); //1
	b.add(new Hexadecimal("3")); //3
	b.add(new Binary("11")); //3
	b.add(new Rational(1, 2)); //.5
	b.add(new Hexadecimal("F")); //15
	b.add(new Rational(30,1));//30
	b.add(new Binary("101000")); //40
	b.add(new Binary("111111")); //63
	b.add(new Hexadecimal("CDE45")); //843333
	
	System.out.println(b.isSorted()); //false

	/*OLD TEST CASES; TESTS LISTINT interface
	ListInt calvin = new SuperArray();
	System.out.println(calvin); // []
	calvin.add(0);
	calvin.add(1);
	calvin.add(2);
	calvin.add(3);
	calvin.add(4);
	System.out.println(calvin); //[0,1,2,3,4]
	System.out.println(calvin.size()); //should be 5

	calvin.add(2, 100); 
	System.out.println(calvin); //[0,1,100,2,3,4]
       	System.out.println(calvin.size()); //should be 6

	calvin.remove(1);
	System.out.println(calvin); //[0,100,2,3,4]
	System.out.println(calvin.size()); //should be 5

	/*OLD TEST CASES; TESTS SuperArray

    	SuperArray curtis = new SuperArray();
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);

	for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set(i,i*2);
	    curtis._size++; //necessary bc no add() method yet
	}

	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);

	System.out.println("testing get()...");
	for( int i = 0; i < curtis._size; i++ ) {
	    System.out.print( "item at index" + i + ":\t" );
	    System.out.println( curtis.get(i) );
	}

	System.out.println("Expanded SuperArray curtis:");
	curtis.expand();
	System.out.println(curtis);

	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.add(5);
	mayfield.add(4);
	mayfield.add(3);
	mayfield.add(2);
	mayfield.add(1);

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	

	mayfield.add(3,99);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(2,88);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(1,77);
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	*/

    }//end main
    		
}//end class
