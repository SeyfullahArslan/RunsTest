/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runstest;

/**
 *
 * @author Seyf
 */
public class RunsTest {

    
    private String testString;
    // önem seviyesini %5 aldığımız için 0.05 için bakılacak zscore 1.96 oluyor
    private double zScoreOfSignificanceLevel = 1.96;
    private int R;
    private int N0;
    private int N1;
    private int N2;

    public RunsTest(String testString) {
        this.testString = testString;
    }

    public void start() {
        this.R = getRuns(this.testString);
        this.N1 = getN1(this.testString);
        this.N2 = getN2(this.testString);
        this.N0 = N1 + N2;
        double expectedRuns = getExpectedRuns(N0, N1, N2);
        double variance = getVariance(N0, N1, N2);
        double standartDeviation = Math.sqrt(variance);

        System.out.println("Z score for %5 significance level: " + zScoreOfSignificanceLevel);
        System.out.println("N1 (0) : " + N1);
        System.out.println("N2 (1) : " + N2);
        System.out.println("N0 (Total N) : " + N0);
        System.out.println("Runs in String : " + R);
        System.out.println("Expected Runs : " + expectedRuns);
        System.out.println("Variance of String : " + variance);
        System.out.println("Standart Deviation of String : " + standartDeviation);

        String result = isRejected(R, expectedRuns, standartDeviation) ? "Absolute value of Zresult > 1.96 \nNON RANDOM" : "Absolute value of Zresult <= 1.96 \nRANDOM";
        System.out.println("Test Result : " + result);
    }

    private int getRuns(String testString) {
        int r = 1;
        for (int i = 0; i < testString.length() - 1; i++) {
            if (testString.charAt(i) != testString.charAt(i + 1)) {
                r++;
            }
        }
        return r;
    }

    private int getN1(String testString) {
        int n1 = 0;
        for (int i = 0; i < testString.length(); i++) {
            if (testString.charAt(i) == '0') {
                n1++;
            }
        }
        return n1;
    }

    private int getN2(String testString) {
        int n2 = 0;
        for (int i = 0; i < testString.length(); i++) {
            if (testString.charAt(i) == '1') {
                n2++;
            }
        }
        return n2;
    }

    private double getExpectedRuns(int n0, int n1, int n2) {
        return ((2 * n1 * n2) / n0) + 1;
    }

    private double getVariance(int n0, int n1, int n2) {
        return ((2 * n1 * n2) * (2 * n1 * n2 - n0)) / ((Math.pow(n0, 2)) * (n0 - 1));
    }

    private boolean isRejected(int runs, double expectedRuns, double standartDeviation) {
        double Zresult = (runs - expectedRuns) / standartDeviation;
        System.out.println("Zresult : " + Zresult);
        System.out.println("Absolute value of Zresult : " + Math.abs(Zresult));
        return Math.abs(Zresult) > zScoreOfSignificanceLevel;
    }
}
