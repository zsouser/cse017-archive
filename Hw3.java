/** 
 * Zach Souser
 * zrs212
 * Description: A main method that implements string checking through the Check class and demonstrates polymorphism and inheritance
 * Homework 3:     DEADLINE: October 16 , 2009
 */

public class Hw3 {
  public static void main(String [] arg){
    PreferredCustomer pf = new PreferredCustomer(923456,new Name("Robert","Jones"), 
                                                 new Address("120 Burrell Blvd","Bethlehem","PA","18051"), 
                                                 "484-515-7787",10);
    Name n = new Name("Mary","Jones");
    Address a =new Address("1091 Oak Drive","Allentown", "PA", "18103");
    Customer c = new Customer(923456,new Name("Robert","Jones"), new Address("120 Burrell Blvd","Bethlehem","PA","18051"), "484-515-7787");
    Customer c1 = new Customer(123456,n,a,"555-777-8900");
    System.out.println(pf.equals(n));  //false
    System.out.println(a.equals(pf));  //false 
    System.out.println(c.equals(pf));  //false 
    System.out.println(pf.equals(new PreferredCustomer(923456,new Name("Robert","Jones"), new Address("120 Burrell Blvd","Bethlehem","PA","18051"),"484-515-7787",10))); 
    // true     
    PreferredCustomer pf1 = new PreferredCustomer(c1,30); // should throw you out of the program Ð invalid customer id.  
  }
} 

class Check{
  public static void err(boolean good,String msg)
  {
    if(!good){
      System.out.println(msg);
      System.exit(2);
    }
  }
}

class PreferredCustomer extends Customer {
  int discount;
  public PreferredCustomer(int i, Name n, Address a, String p, int d) {
    super(i,n,a,p);
    setDiscount(d);
  }
  
  public PreferredCustomer(Customer c, int d) {
    super(c);
    setDiscount(d);
  }
  
  public void setDiscount(int d) {
    Check.err(discount <= 100, "Invalid Discount");
    this.discount = d;
  }
  
  public int getDiscount() {
    return this.discount;
  }
    
  public void setId(int i) {
    super.setId(i);
    Check.err(i >= 900000 && i <= 999999,"Invalid ID for Preferred Customer");
  }
  
  public int compareTo(PreferredCustomer pc) {
    return getId() - pc.getId() + super.compareTo(pc);
  }
  
  public boolean equals(PreferredCustomer pc) {
    return pc.getDiscount() == getDiscount() && pc.getId() == getId() && pc.getName().equals(getName()) &&
      pc.getAddress().equals(getAddress()) && pc.getPhone().equals(getPhone());
  }
}

class Customer {
  int id;
  Name name;
  Address address;
  String phone;
  
  public Customer(int i, Name n, Address a, String p) {
    setId(i);
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
    this.name = new Name(n);
  }
  
  public void setAddress(Address a) {
    this.address = new Address(a);
  }
  
  public void setPhone(String p) {
    String errStr = "Invalid phone number";
    for (int i = 0; i < p.length(); i++) {
      if (i < 3 || (i > 3 && i < 7) || i > 7) Check.err(p.charAt(i) >= '0' && p.charAt(i) <= '9', errStr);
      if (i == 3 || i == 7) Check.err(p.charAt(i) == '-', errStr);
    }
    this.phone = p;
  }
  
  public int getId() {
    return this.id;
  }
  
  public Name getName() {
    return this.name;
  }
  
  public Address getAddress() {
    return this.address;
  }
  
  public String getPhone() {
    return this.phone;
  }
  
  public int compareTo(Customer c) {
    return getId() - c.getId() + getName().compareTo(c.getName()) + getAddress().compareTo(c.getAddress()) + getPhone().compareTo(c.getPhone());
  }
  
  public boolean equals(Customer c) {
    if (!c.getClass().getName().equals("Customer")) return false;
    return (getId() == c.getId() && getName().equals(c.getName()) && getAddress().equals(c.getAddress()) && getPhone().equals(c.getPhone()));
  }
  
  public String toString() {
    return getId() + "\n" + getName() + "\n" + getAddress() + "\n" + getPhone();
  }
}

class Address {
  String street;
  String city;
  String state;
  String zip;
  
  public Address(String str, String c, String sta, String z) {
    setStreet(str);
    setCity(c);
    setState(sta);
    setZip(z);
  }
  
  public Address(Address clone) {
    this(clone.getStreet(),clone.getCity(),clone.getState(),clone.getZip());
  }
  
  public void setStreet(String s) {
    for (int i = 0; i < s.length(); i++) {
      Check.err((s.charAt(i) >= 'A' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == ' ', "Invalid Street");
    }
    this.street = s;
  }
  
  public void setCity(String s) {
      for (int i = 0; i < s.length(); i++) {
      Check.err((s.charAt(i) >= 'A' && s.charAt(i) <= 'z') || s.charAt(i) == ' ', "Invalid City");
    }
    this.city = s;
  }
  
  public void setState(String s) {
    for (int i = 0; i < 2; i++) {
      Check.err(s.charAt(i) >= 'A' && s.charAt(i) < 'Z',"Invalid State");
    }
    this.state = s;
  }
  
  public void setZip(String s) {
    String errStr = "Invalid Zip Code";
    for (int i = 0; i < s.length(); i++) {
      if (i < 5 || i > 5) Check.err(s.charAt(i) >= '0' && s.charAt(i) <= '9', errStr);
      if (i == 5) Check.err(s.charAt(i) == '-', errStr);
    }
    this.zip = s;
  }
  
  public String getStreet() {
    if (this.street == null) return "";
    return this.street;
  }
  
  public String getCity() {
    if (this.city == null) return "";
    return this.city;
  }
  
  public String getState() {
    if (this.state == null) return "";
    return this.state;
  }
  
  public String getZip() {
    if (this.zip == null) return "";
    return this.zip;
  }
  
  public int compareTo(Address a) {
    return (getStreet().compareTo(a.getStreet())) +
      (getCity().compareTo(a.getCity())) +
      (getState().compareTo(a.getState())) +
      (getZip().compareTo(a.getZip()));
  }
  
  public boolean equals(Address a) {
    return (getStreet().equals(a.getStreet())) && (getCity().equals(a.getCity())) && (getState().equals(a.getState())) && (getZip().equals(a.getZip()));
  }
  
  public String toString() {
    return this.getStreet() + "\n" + this.city + ", " + this.state;
  }
}
  
class Name {
  String fName, lName;
  
  public Name(String f, String l) {
    setFname(f);
    setLname(l);
  }
  
  public Name(Name n) {
    this(n.getFname(),n.getLname());
  }
  
  public void setFname(String s) {
    for (int i = 0; i < s.length(); i++) {
      Check.err(s.charAt(i) <= 'z' && s.charAt(i) >= 'A', "Invalid First Name");
    }
    this.fName = s;
  }
  
  public void setLname(String s) {
      for (int i = 0; i < s.length(); i++) {
      Check.err(s.charAt(i) <= 'z' && s.charAt(i) >= 'A', "Invalid Last Name");
    }
    this.lName = s;
  }
  
  public String getFname() {
    if (this.fName == null) return "";
    return this.fName;
  }
  
  public String getLname() {
    if (this.lName == null) return "";
    return this.lName;
  }
  
  public int compareTo(Name n) {
    return getFname().compareTo(n.getFname()) + getLname().compareTo(n.getLname());
  }
  
  public boolean equals(Name n) {
    return getFname().equals(n.getFname()) && getLname().equals(n.getLname());
  }
  
  public String toString() {
    return getFname() + " " + getLname();
  }
}

