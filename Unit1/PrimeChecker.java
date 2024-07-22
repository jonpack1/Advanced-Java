import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;



/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 01 27, 2024
 * PROJECT NAME: PrimeChecker.java
 * DESCRIPTION: checks prime
 */

public class PrimeChecker {


    public static void main(String[] args) {
        int isPrime = 0;
        int notPrime = 0;

        try {
            Scanner getFile = new Scanner(new File("D:\\CS 2163 - Java\\esclipse workspace\\" +
                    "PrimeChecker\\src\\Prime.txt"));
            while (getFile.hasNextLine()) {
                BigInteger num = getFile.nextBigInteger();
                if (primeChecker(num)) {
                    isPrime++;
                }

            }
            getFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("error");
        }


        System.out.println(isPrime);
    }



    public static boolean primeChecker(BigInteger num) {
            if (num.compareTo(BigInteger.ONE) <= 0) {
                return false;
            }

            BigInteger two = new BigInteger("2");
            if(num.equals(two)){
                return true;
            }

            if(num.mod(two).equals(BigInteger.ZERO)){
                return false;
            }

            BigInteger i = new BigInteger("3");
            BigInteger sqrt = num.sqrt();

            while(i.compareTo(sqrt) <= 0){
                if(num.mod(i).equals(BigInteger.ZERO)){
                    return false;
                }
            }

            return true;
        }
    }