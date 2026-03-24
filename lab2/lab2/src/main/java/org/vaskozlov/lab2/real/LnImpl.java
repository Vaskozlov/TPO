package org.vaskozlov.lab2.real;

public class LnImpl implements Function {
    @Override
    public double compute(double x, double eps) {
        if (x <= 0) throw new IllegalArgumentException("ln(x) только x > 0");
        if (Math.abs(x - 1) < 1e-9) return 0.0;
        double u = (x - 1) / (x + 1);
        double sum = 0.0;
        double power = u;
        int k = 0;
        while (Math.abs(power / (2 * k + 1)) > eps) {
            sum += power / (2 * k + 1);
            power *= u * u;
            k++;
        }
        return 2 * sum;
    }
}

