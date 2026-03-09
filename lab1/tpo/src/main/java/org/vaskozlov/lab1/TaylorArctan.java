package org.vaskozlov.lab1;

public class TaylorArctan {
    public static double taylorArctan(double x, int nTerms) {
        if (Math.abs(x) > 1.0) {
            throw new IllegalArgumentException(
                    "Taylor series for arctan(x) converges only for |x| <= 1");
        }

        double sum = 0.0;
        double sign = 1.0;

        for (int k = 0; k < nTerms; k++) {
            double term = sign * Math.pow(x, 2 * k + 1) / (2 * k + 1);
            sum += term;
            sign = -sign;
        }

        return sum;
    }

    public static double taylorArctan(double x) {
        return taylorArctan(x, 10);
    }
}