package org.vaskozlov.lab2.real;

public class SecImpl implements Function {
    private final CosImpl cos;
    public SecImpl(CosImpl cos) { this.cos = cos; }
    @Override
    public double compute(double x, double eps) {
        double c = cos.compute(x, eps);
        if (Math.abs(c) < 1e-8) throw new ArithmeticException("sec(x) не определён");
        return 1.0 / c;
    }
}
