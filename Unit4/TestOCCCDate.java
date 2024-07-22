import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 03 04, 2024
 * PROJECT NAME: TestOCCCDate.java
 * DESCRIPTION: TestOCCCDate
 */
public class TestOCCCDate {



    public static void main(String[] args) {


        OCCCDate today = new OCCCDate(05, 03, 2024);

        System.out.println(today.toString(OCCCDate.FORMAT_US,OCCCDate.STYLE_NUMBERS,OCCCDate.SHOW_DAY_NAME));



        OCCCDate February = new OCCCDate(29, 02, 2022);

        System.out.println(February.toString(OCCCDate.FORMAT_EURO,OCCCDate.STYLE_NUMBERS,OCCCDate.SHOW_DAY_NAME));



        OCCCDate January = new OCCCDate(365, 01, 2022);

        System.out.println(January.toString(OCCCDate.FORMAT_US,OCCCDate.STYLE_NAMES,OCCCDate.SHOW_DAY_NAME));



        OCCCDate Kirk = new OCCCDate(22, 03, 2233);

        System.out.println(Kirk.toString(OCCCDate.FORMAT_EURO,OCCCDate.STYLE_NAMES,OCCCDate.SHOW_DAY_NAME));



    }

}




