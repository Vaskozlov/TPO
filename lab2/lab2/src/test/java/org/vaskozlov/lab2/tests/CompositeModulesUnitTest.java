package org.vaskozlov.lab2.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.vaskozlov.lab2.real.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeModulesUnitTest {

    private final SinImpl sin = new SinImpl();
    private final CosImpl cos = new CosImpl(sin);
    private final SecImpl sec = new SecImpl(cos);
    private final CotImpl cot = new CotImpl(sin, cos);
    private final LnImpl ln = new LnImpl();
    private final LogNImpl log3 = new LogNImpl(ln, 3);
    private final LogNImpl log5 = new LogNImpl(ln, 5);
    private final LogNImpl log10 = new LogNImpl(ln, 10);

    @ParameterizedTest
    @CsvSource({"0.7854, 1.0", "1.5708, 0.0", "3.1416, -1.0"})
    @DisplayName("Тест CosImpl")
    void testCos(double x, double expected) {
        assertEquals(expected, cos.compute(x, 1e-8), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({"0, 1.0", "3.1416, -1.0"})
    @DisplayName("Тест SecImpl")
    void testSec(double x, double expected) {
        assertEquals(expected, sec.compute(x, 1e-8), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({"0.7854, 1.0", "2.3562, -1.0"})
    @DisplayName("Тест CotImpl")
    void testCot(double x, double expected) {
        assertEquals(expected, cot.compute(x, 1e-8), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({"1, 0.0", "8, 0.90309"})
    @DisplayName("Тест Log10Impl")
    void testLog10(double x, double expected) {
        assertEquals(expected, log10.compute(x, 1e-8), 1e-5);
    }
}