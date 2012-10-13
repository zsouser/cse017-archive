public class P3 {
 
  public static void main(String [] arg){     
    Name n = new Name("Mary","Jones");   
    Name n2 = new Name("Robert","Smith"); 
    Name n3 = new Name(n2);   
    n3.setFname("Henry");   
    n2.setLname("Bridal");  
    System.out.println(n2); 
    Address a =new Address("1091 Oak Drive","Allentown", "PA", "18103"); 
    Address a2 =new Address("1216 Brown Street","Newtown", "NJ", "18111"); 
    Address a3 = new Address(a2);   
    a3.setZip("19999-8888");    
    a2.setStreet("4444 Yellow Drive");  
    a2.setCity("Oldtown");    
    a2.setState("MA");    
    System.out.println(a2); 
    Customer c = new Customer(555666,n,a,"610-432-7775"); 
    Customer c2 = new Customer(c);   
    c2.setId(999777);  
    c2.setPhone("999-999-8888"); 
    Customer c3 = new Customer(777778,n3,a3,"999-666-5555"); 
    Customer c4 = new Customer(c3.getId(),c3.getName(),c3.getAddress(),c3.getPhone());
    System.out.println();  
    System.out.println(c);  
    System.out.println();  
    System.out.println(c2);
    System.out.println();   
    System.out.println(c3); 
    System.out.println();   
    System.out.println(c.equals(c2));
    System.out.println(n.equals(n2));
    System.out.println(a2.equals(a3));
    System.out.println(c4.equals(c3));
    System.out.println(a.compareTo(a2));
  } 
 
}

class Check {
  public static void err(boolean good,String msg) {
    if(!good) {
      System.out.println(msg);
      System.exit(2);
    }
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
  