/*  Calvin Vuong
    APCS1 pd5
    HW45 -- Come Together
    2015-12-10 */

public class SuperArray{
     
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;
    
    //position of last meaningful value
    private int _lastPos;
    
    //size of this instance of SuperArray
    private int _size;
    
    		
    //~~~~~METHODS~~~~~
    //default constructor --> initializes 10-item array
    public SuperArray() { 
    	_data = new int[10];
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
    	int[] temp = new int[ _data.length * 2 ];
    	for( int i = 0; i < _data.length; i++ ){
    	    temp[i] = _data[i];
    	}
    	_data = temp;
    }
    
    		
    //accessor -- return value at specified index
    public int get( int index ) { 
	return _data[index];
    }
    
    		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) { 
	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }
    
    
    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) { 
        if (_lastPos == _data.length - 1){ //if no room to add...
            expand(); //expand to make room
        }
        _data[_lastPos+1] = newVal; //make pos after last meaningful one newVal
        _lastPos += 1;
        _size += 1;
    }
    
    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, int newVal ) {
	if (index <= _size){ //index within bounds...
	    if (_lastPos == _data.length - 1){ //if no room to add...
		expand(); //expand to make room
	    }
	    int displaced = 0; //value of displaced element
	    //for every index up to lastPos, and one index after
	    //to account for one more element added
	    for (int i = 0; i <= _lastPos + 1; i++){
		if (i == index){ //if location of insert
		    displaced = _data[i];
		    _data[i] = newVal; //overwrite
		}
		else if (i > index){ //if after shift
		    //value of i becomes the next displaced
		    int newDisplaced = _data[i]; 
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
    
    //main method for testing
    public static void main( String[] args ) { 

	//NEW TEST CASES; TESTS ListInt INTERFACE
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
