/** 
 * Zach Souser
 * zrs212
 * 12/02/2009
 * Homework 6
 * Description: Binary Sorted Tree of numbers 
 */
public class Hw6 {
  public static void main (String arg[]) {
    KTree t = new KTree();
    int numbers[] = {4,9,3,5,8,1,2,18,12,-1};
    for (int j=0; j<numbers.length;j++) {
      t.add(numbers[j]);
    }
    System.out.println("The numbers\n"+t+"\nare in a tree of height "+t.height());
  }
}
/**
 * KTree - Binary tree
 */
class KTree{
  public static final int LEFT=Node.LEFT,RIGHT=Node.RIGHT;
  private Node root;
  public KTree() {
    root=null;
  }
  /** 
   * Recursively adds numbers to the tree
   */
  KTree add(int n) {
    if (root==null)
      root=new Node(n);
    else
      add(n,root);
    return this;
  }
  /**
   * Helper recursion method that performs the function of adding numbers to the tree
   */
  private static void add(int n, Node rt) {
    if (n<rt.key) {
      if (rt.child[LEFT] == null) 
        rt.child[LEFT] = new Node(n);
      else
        add(n,rt.child[LEFT]);
    } else {
      if (rt.child[RIGHT] == null) 
        rt.child[RIGHT]=new Node(n);
      else
        add(n,rt.child[RIGHT]);
    }
  }
  /** ToString **/
  public String toString() {
    return toString(root,0);
  }
  /** ToString recursive Helper method **/
  private static String toString(Node rt,int d) {
    String temp = "";
    for (int j=0;j<10-d;j++) {
      temp+="   ";
    }
    if (rt==null) return "";
    else
      return toString(rt.child[LEFT],d+1) + temp + rt + "\n" + toString(rt.child[RIGHT],d+1);
  }
  /** Calls the helper method to find the height of the tree **/
  public int height() {
    return height(root);
  }
  /** Recursively finds the height of the tree **/
  public int height(Node rt) {
    if (rt != null) {
      return 1+max(height(rt.child[RIGHT]),height(rt.child[LEFT]));
    }
    return 0;
  }
  /** Finds the max of the height of two branches of a tree **/
  private int max(int r, int l) {
    if (l > r) return l;
    return r;
  }
      
}
/**
 * Node class
 * A node within a binary tree
 */
class Node {
  public static final int LEFT=0,RIGHT=1;
  public int key;
  Node child[];
  public Node(int n) {
    key = n;
    child = new Node[2];
    child[LEFT] = null;
    child[RIGHT] = null;
  }
  public String toString() {
    return key+" ";
  }
}
