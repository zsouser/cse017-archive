/** Zach Souser
 * zrs212
 * Program 6
 * LinkList applied to Customers
 * 10/30/2009
 */

public class P6 {
    public static void main(String [] arg){     
	LinkList list = new LinkList();   
	list.addInOrder(new PreferredCustomer(987654, new Name("Mary", "Singleton"),  
					      new Address("1091 Newgate", "Allentown", "MA" ,"18103"),
					      "610-555-9999",50));
	list.addInOrder(new Customer(776549, new Name("Robert", "Carlton"),
				     new Address("21st Street", "Allentown", "PA" ,"18103"),
				     "610-395-7385"));  
	list.addInOrder(new PreferredCustomer(988345, new Name("John", "Singleton"), 
					      new Address("1091 Newgate", "Allentown", "NJ" ,"99567-321"),
					      "610-555-9999",50));   
	list.addInOrder(new Customer(876549, new Name("Harry", "Jones"), 
				     new Address("21st Street", "Allentown", "AL" ,"44556"),
				     "610-395-7385"));  
	list.print(list.NAME);   
	list.print(list.ZIP);  
	list.print(list.ID); 
	list.print(list.STATE);  
	list.delete(987654);   
	list.print(list.NAME); 
	list.print(list.ZIP);  
	list.print(list.ID); 
	list.print(list.STATE);    
    }  
}
/** LinkList class
 * List of customers linked by various sort orders
 */
class LinkList {
    private Node headList;
    public final int NAME = 0;
    public final int ZIP = 1;
    public final int ID = 2;
    public final int STATE = 3;
    public class Node {
	private Customer cust;
	Node[] sort;
	public Node() {
	    sort = new Node[4];
	}
	public Node(Customer c) {
	    cust = c.clone();
	    sort = new Node[4];
	}
	public int compareTo(Node n, int flag) {
	    Customer c = n.cust;
	    if (cust != null && c != null) {
		if (flag == NAME) 
		    return c.getName().compareTo(cust.getName());
		else if (flag == ZIP) 
		    return c.getAddress().getZip().compareTo(cust.getAddress().getZip());
		else if (flag == ID) 
		    return c.getId() - cust.getId();
		else if (flag == STATE) 
		    return c.getAddress().getState().compareTo(cust.getAddress().getState());
	    }
	    return -1;
	}
        
    }
  
    public LinkList() {
	headList = new Node();
    }
  
    public void addInOrder(Customer c) {
	Node temp = new Node(c);
	add(temp,NAME);
	add(temp,ZIP);
	add(temp,ID);
	add(temp,STATE);
    }
  
    public void add(Node temp, int flag) {
	Node position = headList;
	while (position.sort[flag] != null && position.sort[flag].compareTo(temp,flag) <= 0) 
	    position = position.sort[flag];
	temp.sort[flag] = position.sort[flag];
	position.sort[flag] = temp;
    }
  
    public void print(int flag) {
	Node position = headList;
	while (position.sort[flag] != null) {
	    System.out.println(position.cust + "\n\n");
	    position = position.sort[flag];
	}
    }
  
    public void delete(int id) {
	Check.err(headList != null, "HeadList is null");
	Node previous = headList;
	while (previous.sort[ID].cust.getId() != id && previous != null) 
	    previous = previous.sort[ID];
	Node current = previous.sort[ID];
	previous.sort[ID] = current.sort[ID];
    }
      
}
/** Check class
 * Allows any method to check a boolean statement and halt the program.
 */
class Check {
    public static void err(boolean good,String msg) {
	if(!good) {
	    System.out.println(msg);
	    System.exit(2);
	}
    }
}
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
    public PreferredCustomer clone() {
	return new PreferredCustomer(this);
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
    public Customer clone() {
	return new Customer(this);
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

  
