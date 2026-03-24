package org.vaskozlov.lab2.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.vaskozlov.lab2.SystemFunction;
import org.vaskozlov.lab2.real.*;
import org.vaskozlov.lab2.stub.*;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SystemFunctionIntegrationTest {

    private static final double EPS = 1e-8;

    @ParameterizedTest(name = "[Trig] x = {0}")
    @CsvSource({"-0.7854", "-3.1416", "-0.01"})
    @DisplayName("Тригонометрическая ветка (x ≤ 0)")
    void testTrigBranch(double x) {
        SystemFunction sys = new SystemFunction();
        double result = sys.compute(x, EPS);
        assertTrue(Double.isFinite(result));
    }

    @ParameterizedTest(name = "[Log] x = {0}")
    @CsvSource({"8.0", "10.0", "2.71828", "0.5"})
    @DisplayName("Логарифмическая ветка (x > 0)")
    void testLogBranch(double x) {
        SystemFunction sys = new SystemFunction();
        double result = sys.compute(x, EPS);
        assertTrue(Double.isFinite(result));
    }

    @ParameterizedTest
    @MethodSource("provideIntegrationStages")
    @DisplayName("Поэтапная интеграция bottom-up")
    void testStepByStep(String stage, SystemFunction system, double x) {
        double result = system.compute(x, EPS);
        assertTrue(Double.isFinite(result));
    }

    private static Stream<Arguments> provideIntegrationStages() {
        double x = -Math.PI / 4;
        return Stream.of(
                Arguments.of("Этап 0 — все заглушки",
                        new SystemFunction(new SinStub(), new CosStub(), new SecStub(), new CotStub(),
                                new LnStub(), new Log3Stub(), new Log5Stub(), new Log10Stub()), x),
                Arguments.of("Этап 1 — SinImpl + заглушки",
                        new SystemFunction(new SinImpl(), new CosStub(), new SecStub(), new CotStub(),
                                new LnStub(), new Log3Stub(), new Log5Stub(), new Log10Stub()), x),
                Arguments.of("Финал — полностью реальная система",
                        new SystemFunction(), x)
        );
    }

    @Test
    @DisplayName("Проверка исключений")
    void testExceptions() {
        SystemFunction sys = new SystemFunction();

        assertThrows(ArithmeticException.class, () -> sys.compute(0.0, EPS));
        assertThrows(ArithmeticException.class, () -> sys.compute(-Math.PI / 2, EPS));
        assertThrows(IllegalArgumentException.class, () -> sys.compute(1.0, 0.0));
    }

    @Test
    @DisplayName("Тест exportToCsv")
    void testExport(@TempDir Path tempDir) {
        SystemFunction sys = new SystemFunction();
        String file = "/Users/vaskozlov/test_f.csv";
        sys.exportToCsv("f", -2.0, 2.0, 0.1, EPS, file);
        assertTrue(new File(file).exists());
    }
}