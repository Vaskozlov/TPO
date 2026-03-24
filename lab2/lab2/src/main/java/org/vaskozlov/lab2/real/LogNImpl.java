package org.vaskozlov.lab2.real;

public class LogNImpl implements Function {  // base передаётся в конструкторе
    private final LnImpl ln;
    private final double base;
    public LogNImpl(LnImpl ln, double base) { this.ln = ln; this.base = base; }
    @Override
    public double compute(double x, double eps) {
        if (x <= 0) throw new IllegalArgumentException("log только x > 0");
        return ln.compute(x, eps) / ln.compute(base, eps);
    }
}
