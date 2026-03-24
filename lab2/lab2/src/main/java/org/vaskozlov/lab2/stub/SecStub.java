package org.vaskozlov.lab2.stub;

import org.vaskozlov.lab2.real.Function;

public class SecStub implements Function {
    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) throw new IllegalArgumentException("eps должен быть > 0");

        double cosVal = Math.cos(x); // используем реальный cos только для определения области
        if (Math.abs(cosVal) < 1e-8) {
            throw new ArithmeticException(String.format("sec(x) не определён (cos(x)≈0) при x=%.6f", x));
        }

        double modX = Math.abs(x % (2 * Math.PI));
        if (Math.abs(modX) < 0.01) return 1.0;
        if (Math.abs(modX - Math.PI) < 0.01) return -1.0;

        return 1.0 / cosVal;
    }
}
