package org.vaskozlov.lab2.real;

public class SinImpl implements Function {
    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) throw new IllegalArgumentException("eps > 0");
        x = x % (2 * Math.PI);
        double sum = 0.0;
        double term = x;
        int n = 1;
        while (Math.abs(term) > eps) {
            sum += term;
            term = -term * x * x / ((2 * n) * (2 * n + 1));
            n++;
        }
        return sum;
    }
}
