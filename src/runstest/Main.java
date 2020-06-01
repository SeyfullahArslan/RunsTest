/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runstest;

import java.util.Scanner;

/**
 *
 * @author Seyf
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Test Edilecek Stringi Giriniz : ");
        String testString = scanner.next();

        RunsTest runsTest = new RunsTest(testString);
        runsTest.start();
    }

}
