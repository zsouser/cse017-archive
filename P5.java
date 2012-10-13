public class P5{
    public static void main(String arg[]){
	double x[]={2,6,7.4,3,9,18,27.8,4,5,67};
	double y[]={3,5,6,2,3,5.6,87.2,92,42};
	sort(x);
	sort(y);
    
	System.out.println(x[0]+"^"+4+" = "+superPow(x[0],4));
	System.out.println(
			   "Expect:   0.125  1    243     327684         0.979708");
	System.out.println(
			   "        "+ superPow(2,-3) + "   " + superPow(176.8,0)+
			   "   "+superPow(3,5)+ "   "+ superPow(2,15)
			   + "    " +superPow(0.9999,205));
    }
    // recursively initiates the sorting algorithm
    private static void sort(double x[]){
	display(x,"The unsorted array");
	sort(x,0,x.length-1); 
	display(x,"The sorted array");
    }
    // recursively sorts through the array, comparing first and last values and rearranging them in the array if necessary
    private static void sort(double x[],int first, int last) {
	if (x[first] > x[last]) {
	    double temp = x[last];
	    x[last] = x[first];
	    x[first] = temp;
	}
	if (last > first) {
	    sort(x,first+1,last);
	    sort(x,first,last-1);
	}
    }
    // Displays the contents of an array in a formatted fashion
    private static void display(double x[],String label){
	System.out.println(label);
	for(int j=0;j<x.length;j++){
	    if(j>0)
		System.out.print(", ");
	    System.out.print(x[j]);
	}
	System.out.println();
    }
  
    /**
     * if n<0 take 1/x^n
     * if n is odd,  x^(2k+1) is x x^2k
     * if n is even x^2k is (x^2)^k
     */
    private static double superPow(double x, int n) {
	if (n < 0) return (1/superPow(x,-n));
	else if (n == 0) return 1.0;
	else if (n == 2) return x*x;
	else if (n % 2 == 1) return x*superPow(x,n-1);
	return superPow(superPow(x,2),n/2);
    }
}
      
