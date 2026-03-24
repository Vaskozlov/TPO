package org.vaskozlov.lab2.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.vaskozlov.lab2.real.LnImpl;
import org.vaskozlov.lab2.real.SinImpl;

import static org.junit.jupiter.api.Assertions.*;

class BaseModulesUnitTest {

    private final SinImpl sin = new SinImpl();
    private final LnImpl ln = new LnImpl();

    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({
            "0, 0.0",
            "1.570796, 1.0",
            "3.141593, 0.0",
            "-1.570796, -1.0",
            "6.283185, 0.0"
    })
    @DisplayName("Тест SinImpl")
    void testSinImpl(double x, double expected) {
        double result = sin.compute(x, 1e-8);
        assertEquals(expected, result, 1e-6, "sin(" + x + ")");
    }

    @ParameterizedTest(name = "ln({0}) = {1}")
    @CsvSource({
            "1, 0.0",
            "2.718282, 1.0",
            "7.389056, 2.0",
            "0.5, -0.693147"
    })
    @DisplayName("Тест LnImpl")
    void testLnImpl(double x, double expected) {
        double result = ln.compute(x, 1e-8);
        assertEquals(expected, result, 1e-5, "ln(" + x + ")");
    }
}