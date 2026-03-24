package org.vaskozlov.lab2.stub;

import org.vaskozlov.lab2.real.Function;

public class Log3Stub implements Function {
    private final LnStub ln = new LnStub();
    @Override
    public double compute(double x, double eps) {
        if (x <= 0) throw new IllegalArgumentException("log3(x) только x > 0");
        return ln.compute(x, eps) / ln.compute(3.0, eps);
    }
}
