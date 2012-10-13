/* CSE 17
 * Zach Souser 
 * zrs212 
 * Program Description: A basic class for handling and processing various arrays
 * Program #2
 * DEADLINE: September 17, 2009 
 */ 

class P2 {
    public static void main(String arg[]) {
	double x[]={3,5,2,9,27,8.6,24}, 
	    y[][]={{2.1,3,4}, {2,2,3}},
		s[][]={{2,3},{5,4}},
		    t[][]={{1,2,3},{2,8,5},{3,5,6}};
          
		    System.out.print("The following array has all six entries, 1's");
		    show(allOnes(6));  
		    System.out.println();     
		    test("Sum of entries in ",x,sum(x));
		    show(s[0]);
		    System.out.println(" dot ");
		    show(s[1]);     
		    System.out.println(" is "+dot(s[0],s[1]));     
		    test("Sum of squares of entries in ",s[0],sumSq(s[0])); 
		    test("The mean of the entries in ",x,mean(x));
		    testSquare(t);
		    testSymmetric(t);
		    testSquare(y);
		    testSymmetric(s);
    }
  
    // Returns the sum of the values of a
  
    public static double sum(double[] a) {
	double sum = 0;
	for (int i = 0; i < a.length; i++)
	    sum += a[i];
	return sum;
    }
  
    // Returns the sum of all the squared values of a
  
    public static double sumSq(double[] a) {
	double sum = 0; 
    
	for (int i = 0; i < a.length; i++)
	    sum += a[i] * a[i];
    
	return sum;
    }
  
    // Checks if the sizes of a1 and a2 are the same, and returns the combined sums of both arrays by referencing the sum() method
  
    public static double dot(double[] a1, double[] a2) {
	if (a1.length == a2.length) return sum(a1) + sum(a2);
	return 0;
    }
  
    public static void test(String preCalc, double x[],double result) {
	System.out.print(preCalc);
	show(x);
	System.out.println(" is "+result);
    }
  
    public static void testSquare(double t[][]) {  
	System.out.println("The two dimensional array");
	show(t);
	System.out.print(" is ");
	if(!isSquare(t))             
	    System.out.print("not "); 
	System.out.println("square");   
    }
  
    // Determines whether an array has a square shape or not
  
    public static boolean isSquare(double[][] t) {
	boolean result = false;
    
	for (int i = 0; i < t.length; i++)
	    result |= t.length == (t[i]).length;
    
	return result;
    }
  
    public static void testSymmetric(double t[][]) {
	System.out.println("The two dimensional array");
	show(t);
	System.out.print(" is ");
	if(!isSymmetric(t)) 
	    System.out.print("not ");
	System.out.println("symmetric");
    }
  
    // Determines whether a two-dimensional array is symmetric
  
    public static boolean isSymmetric(double[][] t) {
	boolean result = isSquare(t);
	for (int i = 0; i < t.length; i++) {
	    for (int k = 0; k < t[i].length; k++) {
		result &= t[i][k] == t[k][i];
	    }
	}
	return result;
    }        
  
    // Outputs the contents of a one-dimensional array
  
    public static void show(double[] array) {
	System.out.print("(");
	for (int i = 0; i < array.length; i++)
	    System.out.print(array[i] + ",");
	System.out.println(")");
    }
  
    // Outputs the contents of a two-dimensional array by calling the show() method for one dimensional arrays.
  
    public static void show(double[][] array) {
	System.out.print("( ");
    
	for (int i = 0; i < array.length; i++)
	    show(array[i]);
    
	System.out.println(")");
    }
  
    // Generates and returns an array of doubles consisting of only the number 1.
  
    public static double[] allOnes(int size) {
	double[] ones = new double[size];
	for (int i = 0; i < size; i++)
	    ones[i] = 1;
    
	return ones;
    }
  
    // Receives an array of doubles and returns the arithmetic mean of the values as a double
  
    public static double mean(double[] a) {
	return sum(a) / a.length;
    }

} 
