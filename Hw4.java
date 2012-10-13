public class Hw4 {
  public static void main(String arg[]) {
    String params[]={"1011","201","100110","a",null};
    for (int j = 0; j < 5; j++) {
      try {
      System.out.println("The decimal value of the binary number "+params[j]+" is "+parseBinary(params[j]));
      } catch (BinaryFormatException e) {
        System.out.println("Error in binary string: "+e.getMessage());
      }
    }
  }
  
  public static int parseBinary(String param) throws BinaryFormatException {
    if (param == null) throw new BinaryFormatException("null string");
    int tot = 0;
    for (int i = param.length(); i > 0; i--) {
      char c = param.charAt(i - 1);
      if (c == '1') tot += Math.pow(2, param.length() - i);
      else if (c != '0') throw new BinaryFormatException("'"+c+"' is not a binary digit");
    }
    return tot;
  } 
}

class BinaryFormatException extends Exception {
  private String message;
  public BinaryFormatException(String msg) {
    this.message = msg;
  }
  
  public String getMessage() {
    return "Error in binary string: " + this.message;
  }
}
