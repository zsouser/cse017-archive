/* CSE 17 
 * Zach Souser
 * zrs212
 * Program Description: To process credit card numbers and determine their type and validity using the Luhn Check
 * Program #1   
 * DEADLINE: September 7, 2009
 */ 

import java.util.Scanner;
public class P1 
{
    public void P1() { }
  
    public static void main(String[] args) {
    
	// Process input
    
	long number = 0L;
	Scanner keyboard = new Scanner(System.in);
	System.out.print("Please enter a credit card number for validation: ");
    
	if (keyboard.hasNextLong()) 
	    number = keyboard.nextLong();
	else {
	    System.out.println("Invalid entry.");
	    System.exit(0);
	}
    
	keyboard.nextLine(); // clear the buffer
    
	// Convert number into an array of single digit numbers
    
	if (!(number > 1000000000000L && number < 9999999999999999L)) {
	    System.out.println("Invalid entry. You must enter a 13-16 digit number.");
	    System.exit(0);
	}
    
	// process each digit from right to left
    
	int odds = 0;
	int evens = 0;
	int digit = 0;
	int i = 0;
	int type = 0; 
	boolean keepGoing = true;
    
	while (number != 0) { 
	    i++;
	    switch ((int)number)
		{
		case 37: type = 1; break;
		case 4: type = 2; break;
		case 5: type = 3; break;
		case 6: type = 4; break;
		default: type = 0; break;
		} 
      
	    digit = (int)(number % 10);
	    number -= digit;
	    number /= 10;
	    if (i % 2 == 1) odds += digit;
	    else {
		digit *= 2;
		if (digit >= 10) 
		    digit = digit % 10 + digit / 10;
		evens += digit;
	    }
	}
    
	if (((odds + evens) % 10 == 0) && type > 0) {
	    System.out.print("The credit card number you entered is valid. It is ");
	    switch (type) {
	    case 1: System.out.println("an American Express."); break;
	    case 2: System.out.println("a Visa."); break;
	    case 3: System.out.println("a MasterCard."); break;
	    case 4: System.out.println("a Discover."); break;
	    }
	}
    
	else 
	    System.out.println("The credit card number you entered is invalid.");
    }
}
