import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.vaskozlov.lab1.TaylorArctan;

import static org.junit.jupiter.api.Assertions.*;

class TaylorArctanTest {

    @Test
    void testZero() {
        assertEquals(0.0, TaylorArctan.taylorArctan(0.0), 1e-10);
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 10, 0.00001",
            "-0.5, 10, 0.00001",
            "0.1, 1,  0.01"
    })
    void testSmallValues(double x, int nTerms, double delta) {
        double approx = TaylorArctan.taylorArctan(x, nTerms);
        assertEquals(Math.atan(x), approx, delta);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 500, 0.001",
            "-1.0, 500, 0.001"
    })
    void testBoundaryValues(double x, int nTerms, double delta) {
        double approx = TaylorArctan.taylorArctan(x, nTerms);
        assertEquals(Math.atan(x), approx, delta);
    }

    @Test
    void testOutsideConvergence() {
        assertThrows(IllegalArgumentException.class,
                () -> TaylorArctan.taylorArctan(1.5));
    }
}