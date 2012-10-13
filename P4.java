import java.util.Scanner;
import java.io.*;
/**
 * Zach Souser
 * zrs212
 * Program 4
 * Program Description: Loads an array of Customers and PreferredCustomers and updates them with transactions
 */
public class P4{
  public static void main(String [] arg){
    CustomerDatabase current = new CustomerDatabase();
    try{
      current.backup("cust.bak"); //file will be empty
      current.readCustomers("cust.txt");
      current.printCustomers(); 
      current.printCustomers("output.txt"); //see output.txt
      current.backup("cust.bak"); //see cust.bak
      current.processTransactions("trans.txt"); //see trans.txt 
      current.printCustomers();
      current.readCustomers("cust.bak");
      current.printCustomers(); 
      System.out.println(current.findCust(
                                          new PreferredCustomer(987654, 
                                                       new Name("Mary", "Singleton"), 
                                                       new Address("1091 Newgate", "Allentown", "PA" ,"18103"), 
                                                       "610-555-9999",
                                                       50)
                                         )
                        );
      
      System.out.println(current.findCust(
                                          new Customer(876549, 
                                                       new Name("Mary", "Jones"), 
                                                       new Address("21st Street", "Allentown", "PA" ,"18103"), 
                                                       "610-395-7385")
                                         )
                        );
    }    
    catch(CustomerNotFoundException e){
      System.out.println(e.getMessage());
    }
  }
}

//// CustomerDatabase Class

class CustomerDatabase {
  private Scanner inputStream;
  private PrintWriter outputStream;
  private Customer [] data;
  private int numAccts;
  /**
   * No-Arg Constructor
   * Initializes the instance variables
   */
  public CustomerDatabase() {
    inputStream = new Scanner(System.in);
    outputStream = new PrintWriter(System.out);
    numAccts = 0;
    data = new Customer[100];
  }
  /** Read Customers
    * Parameter: filename of the customers data file
    * Preconditions: fName represents the filename of a legitimate file 
    * Postconditions: data is filled with Customers and PreferredCustomers, and numAccts is the number of Customers and PreferredCustomers in data
    */
  public void readCustomers(String fName) {
    Customer[] custs = new Customer[100];
    try { inputStream = new Scanner(new File(fName)); } catch (java.io.FileNotFoundException e) { System.out.println(e); }
    while (inputStream.hasNextLine()) {
      Scanner line = new Scanner(inputStream.nextLine());
      line.useDelimiter(",");
      int id = 0, d = 0;
      String fn = null,ln = null,str = null,cit = null,sta = null,zip = null,p = null;
      if (line.hasNextInt()) id = line.nextInt();
      if (line.hasNext()) fn = new String(line.next());
      if (line.hasNext()) ln = new String(line.next());
      if (line.hasNext()) str = new String(line.next());
      if (line.hasNext()) cit = new String(line.next());
      if (line.hasNext()) sta = new String(line.next());
      if (line.hasNext()) zip = new String(line.next());
      if (line.hasNext()) p = new String(line.next());
      if (line.hasNext()) d = line.nextInt();
      if (d == 0) data[numAccts] = new Customer(id,new Name(fn,ln),new Address(str,cit,sta,zip),p);
      else data[numAccts] = new PreferredCustomer(id,new Name(fn,ln),new Address(str,cit,sta,zip),p,d);
      numAccts++;
    }
  }
  /** Process Transactions
   * Parameter: filename of the transactions file
   * Preconditions: fName represents a legitimate file
   * Postconditions: data is updated with differences between the transactions file and the information in data when the IDs match
   */
  public void processTransactions(String fName) throws CustomerNotFoundException {
    try {
      inputStream = new Scanner(new File(fName));
    } catch (java.io.FileNotFoundException e) { throw new CustomerNotFoundException("File could not be opened"); }
    
    int i = 0;
    while (inputStream.hasNextLine()) {
      Customer temp;
      Scanner line = new Scanner(inputStream.nextLine());
      line.useDelimiter(",");
      int id = 0, d = 0;
      String fn = null,ln = null,str = null,cit = null,sta = null,zip = null,p = null;
      if (line.hasNextInt()) id = line.nextInt();
      if (line.hasNext()) fn = new String(line.next());
      if (line.hasNext()) ln = new String(line.next());
      if (line.hasNext()) str = new String(line.next());
      if (line.hasNext()) cit = new String(line.next());
      if (line.hasNext()) sta = new String(line.next());
      if (line.hasNext()) zip = new String(line.next());
      if (line.hasNext()) p = new String(line.next());
      if (line.hasNext()) d = line.nextInt();
      if (d == 0) temp = new Customer(id,new Name(fn,ln),new Address(str,cit,sta,zip),p);
      else temp = new PreferredCustomer(id,new Name(fn,ln),new Address(str,cit,sta,zip),p,d);
      for (int j = 0; j < numAccts; j++) {
        if (data[j].getId() == temp.getId()) {
         if (!data[j].equals(temp))  data[j] = temp;
        }
      }
      i++;
    }
  }
  /** Print Customers No-Arg
    * prints customers to the System.out output stream
    * Preconditions: none
    * Postconditions: the contents of data are outputted to the screen
    */
  public void printCustomers() {
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        System.out.println(data[i]);
        System.out.println();
      }
    }
  }
  /** Print Customers
    * Prints the customers list to an output file - duplicate of printCustomer() that outputs to a file
    */
  public void printCustomers(String fName) {
    try {
      outputStream = new PrintWriter(fName);
    } catch (FileNotFoundException e) { System.out.println(e); }
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        outputStream.println(data[i]);
        outputStream.println();
      }
    }
    outputStream.close();
  }
  /** Backup
    * Backs up the current data into a backup file
    * Precondition: fName is a legitimate file name
    * Postcondition: the contents of fName will be a string returned by a helper function
    */
  public void backup(String fName) {
    try {
      outputStream = new PrintWriter(fName);
    } catch (FileNotFoundException e) { System.out.println(e); }
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        outputStream.println(backup(data[i]));
      }
    }
    outputStream.close();
  }
  /** Backup Helper Function
    * Creates a backup-formatted text string and returns it based on a customer object
    * Postcondition: returns a string formatted with all values for all customers separated by commas
    */
  private String backup(Customer c) {
    String text = new String(c.getId()+","+c.getName().getFname()+","+c.getName().getLname()+","+
      c.getAddress().getStreet()+","+c.getAddress().getCity()+","+c.getAddress().getState()+","+
      c.getAddress().getZip()+","+c.getPhone()+",");
    if (c instanceof PreferredCustomer) text += ((PreferredCustomer)c).getDiscount()+",";
    return text;
  }
  /** Find Customer 
    * Finds a customer in the data array and returns it
    * Parameter: a non-null Customer is passed in as a search parameter
    * Postcondition: returns a Customer that matches the search criteria.
    */
  public Customer findCust(Customer param) throws CustomerNotFoundException {
    for (int i = 0; i < numAccts; i++) 
      if (data != null && data[i].equals(param)) return data[i];
    throw new CustomerNotFoundException("Invalid Search");
  }
}

//// CustomerNotFoundException class

class CustomerNotFoundException extends Exception {
  String errStr;
  public CustomerNotFoundException(String msg) {
    this.errStr = msg;
  }
  public String getMessage() {
    return this.errStr;
  }
}

//// Check class

class Check {
  public static void err(boolean good,String msg) {
    if(!good) {
      System.out.println(msg);
      System.exit(2);
    }
  }
}

//// PreferredCustomer and Customer, etc.

/** Class PreferredCustomer
  * extension of Customer with a discount
  * ID must start with a 9
  */
class PreferredCustomer extends Customer {
  private int discount;
  public PreferredCustomer(int i, Name n, Address a, String p, int d) {
    super(i,n,a,p);
    setDiscount(d);
  }
  public PreferredCustomer(Customer c, int d) {
    super(c);
    setDiscount(d);
  }
  public PreferredCustomer(PreferredCustomer pc) {
    this(pc.getId(),pc.getName(),pc.getAddress(),pc.getPhone(),pc.getDiscount());
  }
  public void setDiscount(int d) {
    Check.err(d > 0 && d < 100,"Invalid Discount");
    discount = d;
  }
  public int getDiscount() {
    return discount;
  }
  public boolean equals(PreferredCustomer c) {
    return super.equals(c) && c.getDiscount() == discount;
  }
  public int compareTo(PreferredCustomer c) {
    return super.compareTo(c) + discount - c.getDiscount();
  }
  public String toString() {
    return super.toString() + "\n" + discount;
  }
}
/** Customer Class
  * Stores an ID, name, address, and phone number for all customers
  */
class Customer {
  private int id;
  private Name name;
  private Address address;
  private String phone;
  public Customer(int id, Name n, Address a, String p) {
    setId(id);
    setName(n);
    setAddress(a);
    setPhone(p);
  }
  public Customer(Customer c) {
    this(c.getId(),c.getName(),c.getAddress(),c.getPhone());
  }
  public void setId(int i) {
    Check.err(i >= 100000 && i <= 999999,"Invalid ID");
    this.id = i;
  }
  public void setName(Name n) {
    Check.err(n != null,"Invalid Name");
    this.name = new Name(n);
  }
  public void setAddress(Address a) {
    Check.err(a != null,"Invalid Address");
    this.address = new Address(a);
  }
  public void setPhone(String p) {
    Check.err(p != null,"Invalid Phone Number");
    this.phone = new String(p);
  }
  public int getId() {
    return id;
  }
  public Name getName() {
    return new Name(name);
  }
  public Address getAddress() {
    return new Address(address);
  }
  public String getPhone() {
    return new String(phone);
  }
  public boolean equals(Customer c) {
    if (c == null) return false;
    return (id == c.getId() && name.equals(c.getName()) && address.equals(c.getAddress()) && phone.equals(c.getPhone()));
  }
  public int compareTo(Customer c) {
    if (c == null) return -1;
    if (this.equals(c)) return 0;
    return name.compareTo(c.getName()) + address.compareTo(c.getAddress()) + phone.compareTo(c.getPhone()) + (this.id - c.getId());
  }
  public String toString() {
    return id + "\n" + name + "\n" + address + "\n" + phone;
  }
}
/** Address Class 
  * Stores a street, city, state, and zip
  * With accessor and mutator methods
  */
class Address {
  private String str, cit, sta, zip;
  public Address(String str, String cit, String sta, String zip) {
    setStreet(str);
    setCity(cit);
    setState(sta);
    setZip(zip);
  }
  public Address(Address a) {
    this(a.getStreet(),a.getCity(),a.getState(),a.getZip());
  }
  public void setStreet(String s) {
    Check.err(s != null,"Invalid Street");
    this.str = new String(s);
  }
  public void setCity(String s) {
    Check.err(s != null,"Invalid City");
    this.cit = new String(s);
  }
  public void setState(String s) {
    Check.err(s != null,"Invalid State");
    this.sta = new String(s);
  }
  public void setZip(String s) {
    Check.err(s != null,"Invalid Zip");
    this.zip = new String(s);
  }
  public String getStreet() {
    return new String(this.str);
  }
  public String getCity() {
    return new String(this.cit);
  }
  public String getState() {
    return new String(this.sta);
  }
  public String getZip() {
    return new String(this.zip);
  }
  public boolean equals(Address a) {
    if (a == null) return false;
    return this.str.equals(a.getStreet()) && this.cit.equals(a.getCity()) && this.sta.equals(a.getState()) && this.zip.equals(a.getZip());
  }
  public int compareTo(Address a) {
    if (a == null) return -1;
    if (this.equals(a)) return 0;
    return this.str.compareTo(a.getStreet()) + this.cit.compareTo(a.getCity()) + this.sta.compareTo(a.getState()) + this.zip.compareTo(a.getZip());
  }
  public String toString() {
    return str + "\n" + cit + ", " + sta + " " + zip;
  }
}
/** Name Class
  * Stores a first and last name
  * Accessor and mutator methods
  */
class Name {
  private String fName, lName;
  public Name(String f, String l) {
    setFname(f);
    setLname(l);
  }
  public Name(Name n) {
    this(n.getFname(),n.getLname());
  }
  public void setFname(String f) {
    Check.err(f != null,"Invalid First Name");
    this.fName = new String(f);
  }
  public void setLname(String l) {
    Check.err(l != null,"Invalid Last Name");
    this.lName = new String(l);
  }
  public String getFname() {
    return new String(fName);
  }
  public String getLname() {
    return new String(lName);
  }
  public boolean equals(Name n) {
    if (n == null) return false;
    return (fName.equals(n.getFname()) && lName.equals(n.getLname()));
  }
  public int compareTo(Name n) {
    if (n == null) return -1;
    if (this.equals(n)) return 0;
    return fName.compareTo(n.getFname()) + lName.compareTo(n.getLname());
  }
  public String toString() {
    return fName + " " + lName;
  }
}
