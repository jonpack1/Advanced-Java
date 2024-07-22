import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 01 26, 2024
 * PROJECT NAME: PasswordChecker.java
 * DESCRIPTION: checks password
 */
public class PasswordChecker {



    public static void main(String[] args){

        Scanner get = new Scanner(System.in);
        int notGood = 0;
        int good = 0;

       try{
           Scanner getFile = new Scanner(new File
                   ("D:\\CS 2163 - Java\\esclipse workspace\\PasswordChecker\\src\\Passwords.txt"));
           while(getFile.hasNextLine()){
               String pw = getFile.nextLine();
               boolean valid = passwordChecker(pw);
               if(!valid){
                   notGood++;
               }else{
                   good++;
               }
           } getFile.close();
       } catch(FileNotFoundException e){
           System.err.println("error");
       }




        //boolean valid = true;

//        System.out.println(" please enter your password");
//        String pw = get.nextLine();
//        valid = passwordChecker(pw);
//        if(valid) System.out.println("nicely done");
//
//        while(!valid){
//                System.out.println("that password is not valid");
//                System.out.println("wanna try again?");
//                pw = get.nextLine();
//                valid = passwordChecker(pw);
//                if(valid) System.out.println("nicely done");
//            }


        System.out.println(good);
    }

    public static boolean  passwordChecker(String pw){
        boolean twelve = false;
        boolean space = false;
        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        if(pw.length()>=12) twelve = true;
        if(pw.contains(" ")) space = true;


        for (int i = 0; i < pw.length(); i++) {

            if(Character.isUpperCase(pw.charAt(i))) upper = true;
            if(Character.isLowerCase(pw.charAt(i))) lower = true;
            if(Character.isDigit(pw.charAt(i))) digit = true;
            if(!Character.isLetterOrDigit(pw.charAt(i))) special = true;



        }
//
//        if(!twelve) System.out.println("missing 12");
//        if(space) System.out.println("no space");
//        if(!upper) System.out.println("missing upper");
//        if(!lower) System.out.println("missing lower");
//        if(!digit) System.out.println("missing digit");
//        if(!special) System.out.println("missing special");

        return twelve && !space && upper && lower && digit && special;





    }



}
