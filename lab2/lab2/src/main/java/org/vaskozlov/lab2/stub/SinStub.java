package org.vaskozlov.lab2.stub;

import org.vaskozlov.lab2.real.Function;

public class SinStub implements Function {
    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) throw new IllegalArgumentException("eps должен быть > 0");

        double modX = x % (2 * Math.PI);
        if (modX < 0) modX += 2 * Math.PI;

        if (Math.abs(modX) < 0.01 || Math.abs(modX - 2*Math.PI) < 0.01) return 0.0;
        if (Math.abs(modX - Math.PI/2) < 0.01) return 1.0;
        if (Math.abs(modX - Math.PI) < 0.01) return 0.0;
        if (Math.abs(modX - 3*Math.PI/2) < 0.01) return -1.0;

        return Math.sin(x);
    }
}
