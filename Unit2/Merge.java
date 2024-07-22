/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 06, 2024
 * PROJECT NAME: Main.java
 * DESCRIPTION: natural merge
 * worked with carlos, trace, kierra, nassir darryl, nurlan, luke
 */


import java.util.*;

import java.io.*;

public class Merge {



    public static void main(String[] args) throws IOException {



        String infile1 = (args.length > 0) ? args[0] : prompt("Enter first filename");

        String infile2 = (args.length > 1) ? args[1] : prompt("Enter second filename");

        String outputFileName = (args.length > 2) ? args[2] : prompt("Enter output filename");





        try {

            Scanner getFile1 = new Scanner(new File(infile1));

            int size1 = getFile1.nextInt();

            int[] array1 = new int[size1];

            for (int i = 0; i < array1.length; i++) {

                array1[i] = getFile1.nextInt();

            }

            getFile1.close(); // Close the Scanner after use



            Scanner getFile2 = new Scanner(new File(infile2));

            int size2 = getFile2.nextInt();

            int[] array2 = new int[size2];

            for (int i = 0; i < array2.length; i++) {

                array2[i] = getFile2.nextInt();

            }

            getFile2.close(); // Close the Scanner after use





            //java.io.PrintWriter output = new java.io.PrintWriter(outputFileName);

            //int[] array1 = readFile(fileName1);

            //int[] array2 = readFile(fileName2);



            //merge(array1);

            //merge(array2);

            mergeSort(array1, 0, array1.length - 1);

            mergeSort(array2, 0, array2.length - 1);

            int[] mergedArray = mergeArrays(array1, array2);

            displayArray(mergedArray);

            writeToFile(outputFileName, mergedArray);





        }

        catch (FileNotFoundException e) {

            System.err.println("File not found: " + e.getMessage());



        }}

    private static String prompt(String message) {

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();

    }



    private static int[] readFile(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String countStr = br.readLine();

            int count = Integer.parseInt(countStr);



            int[] numbersArray = new int[count];



            for (int i = 0; i < count && (numbersArray[i] = Integer.parseInt(br.readLine())) != -1 ; i++) { }



            return numbersArray;

        } catch (IOException e) {

            System.out.println("Error reading file: " + fileName);

            return new int[0];  // Return empty array if an exception occurred.

        }

    }



    private static void mergeSort(int[] array, int l, int r) {

        if (l < r) {

            int m = (l + r) / 2;



            mergeSort(array, l, m);

            mergeSort(array , m + 1, r);



            merge(array, l, m, r);

        }

    }



    private static void merge(int[] array, int l, int m, int r) {

        int n1 = m - l + 1;

        int n2 =  r - m;



        int[] L = new int[n1];

        int[] R = new int[n2];



        for (int i = 0; i < n1; ++i) {

            L[i] = array[l + i];

        }

        for (int j = 0; j < n2; ++j) {

            R[j] = array[m + 1+ j];

        }



        int i = 0, j = 0;



        int k = l;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {

                array[k] = L[i];

                i++;

            } else {

                array[k] = R[j];

                j++;

            }

            k++;

        }



        while (i < n1) {

            array[k] = L[i];

            i++;

            k++;

        }



        while (j < n2) {

            array[k] = R[j];

            j++;

            k++;

        }

    }

    private static int[] mergeArrays(int[] array1, int[] array2) {

        int[] mergedArray = new int[array1.length + array2.length];



        int i = 0, j = 0;

        while (i + j < mergedArray.length) {

            if (i == array1.length || (j != array2.length && array2[j] <= array1[i])) {

                mergedArray[i+j] = array2[j++];  // Copy item from array2 and increment j

            } else {

                mergedArray[i+j] = array1[i++];  // Copy item from array1 and increment i

            }

        }



        return mergedArray;

    }



    private static void writeToFile(String fileName, int[] numbers) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (int number : numbers) {

                bw.write(number + " ");  // Write each number on a separate line.

            }

        } catch (IOException e) {

            System.out.println("Error writing to file: " + fileName);

        }

    }

    public static void displayArray(int [] theArray,int start, int stop){
        for (int i = start; i < stop ; i++) {
            if(i%10 == 0){
                System.out.println();
            }
            System.out.printf("%7d", theArray[i]);

        }
        System.out.println();
        System.out.println();

    }

    public static void displayArray(int [] theArray){
        if(theArray.length <= 200){
            displayArray(theArray, 0, theArray.length);
        }
        else{
            displayArray(theArray, 0, 100);
            displayArray(theArray, theArray.length-100, theArray.length);
        }

    }

}