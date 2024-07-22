/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 01 19, 2024
 * PROJECT NAME: Pack_MagicBall.java
 * DESCRIPTION: This is a program that predicts the future.
 */

import java.util.Random;
import java.util.Scanner;

public class Pack_MagicBall {


    public static void main(String[] args){

        int numberOfQuestions = 0;

        Scanner get = new Scanner(System.in);

        boolean control = true;

        //while loop controlled by a boolean
        while(control) {

            System.out.println("Enter your question. ");

            String s1 = get.nextLine();


            if (s1 == null || s1.isEmpty()) {

                System.out.println("Number of questions: " +numberOfQuestions);
                control = false;

            } else {

                Random randomNum = new Random(); //random to iterate through random responses

                int choice = randomNum.nextInt(0, 20);

                switch(choice){ //switch statement to display random responses
                    case 0:
                        System.out.println("You shall not pass. ");
                        break;
                    case 1:
                        System.out.println("That's not a good idea. ");
                        break;
                    case 2:
                        System.out.println("Sorry we are out of croissants :(");
                        break;
                    case 3:
                        System.out.println("But wait theres more! if you call now its just $19.99. ");
                        break;
                    case 4:
                        System.out.println("Could you repeat that?");
                        break;
                    case 5:
                        System.out.println("Its not the right time. ");
                        break;
                    case 6:
                        System.out.println("You are not ready. ");
                        break;
                    case 7:
                        System.out.println("Take a chance! ");
                        break;
                    case 8:
                        System.out.println("It will be worth it. ");
                        break;
                    case 9:
                        System.out.println("Just keep swimming. ");
                        break;
                    case 10:
                        System.out.println("Nicely done! ");
                        break;
                    case 11:
                        System.out.println("Sometimes taking the low road is a good option. ");
                        break;
                    case 12:
                        System.out.println("Dont take no for an answer. ");
                        break;
                    case 13:
                        System.out.println("Yes 27 percent interest on a camaro is a fantastic deal. ");
                        break;
                    case 14:
                        System.out.println("a robot that rakes up your leaves and then eats them. ");
                        break;
                    case 15:
                        System.out.println("No the interest rates on homes will not be going down ever. ");
                        break;
                    case 16:
                        System.out.println("Nobody messes with the Zohan. ");
                        break;
                    case 17:
                        System.out.println("You did not do the dishes, like you were asked ");
                        break;
                    case 18:
                        System.out.println("sounds like a catch 47 situation. ");
                        break;
                    case 19:
                        System.out.println("you new friend is waiting outside for you. ");
                        break;
                    case 20:
                        System.out.println("Lifes like a box of apples. ");
                        break;
                }
                numberOfQuestions++;
            }

        }



    }

}
