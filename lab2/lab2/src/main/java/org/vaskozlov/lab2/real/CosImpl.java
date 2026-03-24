package org.vaskozlov.lab2.real;

public class CosImpl implements Function {
    private final SinImpl sin;
    public CosImpl(SinImpl sin) { this.sin = sin; }
    @Override
    public double compute(double x, double eps) {
        return sin.compute(Math.PI / 2 - x, eps);
    }
}