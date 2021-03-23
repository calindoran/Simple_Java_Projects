public class Calculator{

    public Calculator(){

    }

  /*
  This program is a basic calculator using java classes, objects and methods.
  */

    public int add(int a, int b){
        int product = a + b;
        return product;
    }

    public int subtract(int a, int b){
        int product = a - b;
        return product;
    }

    public int multiply(int a, int b){
        int product = a * b;
        return product;
    }

    public int divide(int a, int b){
        int product = a / b;
        return product;
    }

    public int modulo(int a, int b){
        int product = a % b;
        return product;
    }

    public static void main (String[] args){

        Calculator myCalculator = new Calculator();
        System.out.println(myCalculator.add(5,7));
        System.out.println(myCalculator.subtract(45,11));

    }

}