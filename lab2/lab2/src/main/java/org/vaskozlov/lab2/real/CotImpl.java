package org.vaskozlov.lab2.real;

/**
 * Реализация функции cot(x) = cos(x) / sin(x)
 * Использует базовые модули Sin и Cos (как требуется в структуре лабораторной)
 */
public class CotImpl implements Function {

    private final SinImpl sin;
    private final CosImpl cos;

    /**
     * Конструктор для внедрения зависимостей (DI)
     */
    public CotImpl(SinImpl sin, CosImpl cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double compute(double x, double eps) {
        if (eps <= 0) {
            throw new IllegalArgumentException("eps должен быть положительным");
        }

        double sinValue = sin.compute(x, eps);
        double cosValue = cos.compute(x, eps);

        if (Math.abs(sinValue) < 1e-10) {
            throw new ArithmeticException(
                    String.format("cot(x) не определён (sin(x) ≈ 0) при x = %.6f", x)
            );
        }

        return cosValue / sinValue;
    }
}
