import java.math.BigInteger;
import java.util.Scanner;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 14, 2024
 * PROJECT NAME: RecursionHW.java
 * DESCRIPTION: RecursionHW
 */
public class RecursionHW {

    public static void main(String[] args) {


        BigInteger bg = BigInteger.valueOf(0);


        //commandline input
        if (args.length > 0) {
            bg =  new BigInteger(args[0]);
        } else {
            Scanner get = new Scanner(System.in);
            // ask the user for input
            System.out.print("Enter value: ");
            bg = get.nextBigInteger();
        }

        //calculate the factorial of bg and store in result
        BigInteger result = factorial(bg);


        System.out.println("Factorial of : " + bg + " is "+ result);


    }

    public static BigInteger factorial(BigInteger n){
        if(n.equals(BigInteger.ONE)){                   //base case if 1 no more factorial
            System.out.println("factorial(1) = 1");
            return BigInteger.ONE;
        }else{
            System.out.println("factorial(" + n + ") = " + n + " * factorial(" + n.subtract(BigInteger.ONE) + ")");
            BigInteger result = n.multiply(factorial(n.subtract(BigInteger.ONE)));
            return result;
            // n * (n-1) called recursively
        }
        }
    }



