package org.vaskozlov.lab2.stub;

import org.vaskozlov.lab2.real.Function;

public class CotStub implements Function {
    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) throw new IllegalArgumentException("eps должен быть > 0");

        double sinVal = Math.sin(x);
        if (Math.abs(sinVal) < 1e-8) {
            throw new ArithmeticException(String.format("cot(x) не определён (sin(x)≈0) при x=%.6f", x));
        }

        double modX = x % (2 * Math.PI);
        if (Math.abs(modX) < 0.01) return Double.POSITIVE_INFINITY;
        if (Math.abs(modX - Math.PI/4) < 0.01) return 1.0;
        if (Math.abs(modX - 3*Math.PI/4) < 0.01) return -1.0;

        return Math.cos(x) / sinVal;
    }
}