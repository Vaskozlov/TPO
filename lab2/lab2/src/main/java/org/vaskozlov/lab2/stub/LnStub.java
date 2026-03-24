package org.vaskozlov.lab2.stub;

import org.vaskozlov.lab2.real.Function;

public class LnStub implements Function {
    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) throw new IllegalArgumentException("eps должен быть > 0");
        if (x <= 0) throw new IllegalArgumentException("ln(x) определён только для x > 0");

        if (Math.abs(x - 1) < 0.01) return 0.0;
        if (Math.abs(x - Math.E) < 0.01) return 1.0;
        if (Math.abs(x - Math.E*Math.E) < 0.01) return 2.0;
        if (Math.abs(x - 0.5) < 0.01) return -0.693147;

        return Math.log(x);
    }
}
