/** 
 * Zach Souser
 * zrs212
 * Homework 5
 */

public class Hw5 {
  public static void main(String [] args)   {     
    LinkList list = new LinkList(); 
    list.addFirst("Cat",30);   
    list.addLast("Dog",25);   
    list.addInOrder("Zebra",10);
    System.out.println(list); //Cat 30----->Dog 25----->Zebra 10----->null  
    list.remove("Dog");   //Dog was deleted -- finds first instance of dog  
    System.out.println(list); //Cat 30----->Zebra 10----->null  
    System.out.println(list.contains("Zebra"));    // prints true  
    System.out.println(list.contains(60));      //prints false  
    System.out.println(list.size());     // prints 2  
    System.out.println(list.get("Zebra")); // prints 2 --finds first instance of zebra   
    System.out.println(list.get("Snake")); // prints -1     
    list.addInOrder("Dog",25);  
    System.out.println(list.printDescendingOrder()); //  Zebra 10---->Dog 25---->Cat 30  
    list.removeLast();  //deletes Zebra from the list  
    list.removeFirst(); //deletes Cat from the list      
    System.out.println(list); // Dog 25----->null 
  } 
}

class LinkList{
  private Node headList;
  private class Node{
    private String animal;
    private int num;
    private Node next;
    public Node(){
      animal = "";
      num = 0;
      next = null;
    }
    public Node(String a, int n, Node nxt){
      animal = a;
      num = n;
      next = nxt;
    }
    public String toString(){
      return animal + " " + num;
    }
  }
  public LinkList(){
    headList = null;
  }
  public void addFirst(String a, int n){
    headList = new Node(a,n,headList);
  }
  public void addLast(String a, int n){
    Node position = headList;
    if(headList == null)
      addFirst(a,n);
    else {
      while(position.next != null)
        position = position.next;
      position.next = new Node(a,n,null);
    }
  }
  public void addInOrder(String a, int n){
    if(headList == null ||
       a.compareTo(headList.animal) <0)
      addFirst(a,n);
    else{
      Node position = headList;
      while( position.next != null && 
             a.compareTo(position.next.animal) >= 0)
        position = position.next;
      position.next = new Node(a,n,position.next);
    }
  }
  public String toString(){
    Node position = headList;
    String temp = "";
    if(headList == null)
      return "Empty List";
    while(position != null){
      temp = temp + position + "---->";
      position = position.next;
    }
      return temp + "null";
  }
  public boolean contains(String a){
    if(headList == null)
      return false;
    Node position = headList;
    while(position != null){
      if(position.animal.equals(a))
        return true;
      else
        position = position.next;
    }
    return false;
  }
  public boolean contains(int n) {
    if (headList == null) 
      return false;
    Node position = headList;
    while(position != null){
      if(position.num == n)
        return true;
      else
        position = position.next;
    }
    return false;
  }
  public int size() {
    Node pointer = headList;
    int counter = 0;
    while (pointer.next != null) {
      counter++;
      pointer = pointer.next;
    }
    return counter;
  }
  public int get(String a) {
    Node pointer = headList;
    int counter = 0;
    while (!pointer.animal.equals(a) && pointer.next != null) {
      counter++;
      pointer = pointer.next;
    }
    return counter;
  }
  public void remove(String a) {
    if (headList.animal.equals(a)) removeFirst();
  }
  public void removeFirst() {
    headList = headList.next;
  }
  
  public void removeLast() {
    Node pointer = headList;
    while (pointer.next != null) {
      pointer = pointer.next;
    }
    pointer = null;
  }
  
  public String printDescendingOrder() {
    return printDescendingOrder(headList);
  }
  public String printDescendingOrder(Node pointer) {
    if (pointer.next == null) return pointer.toString();
    return printDescendingOrder(pointer.next);
  }
      
}

    