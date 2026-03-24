package org.vaskozlov.lab2;

import lombok.Getter;
import org.vaskozlov.lab2.real.*;
import org.vaskozlov.lab2.stub.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
public class SystemFunction {

    private final Function sin;
    private final Function cos;
    private final Function sec;
    private final Function cot;
    private final Function ln;
    private final Function log3;
    private final Function log5;
    private final Function log10;

    public SystemFunction() {
        SinImpl realSin = new SinImpl();
        CosImpl realCos = new CosImpl(realSin);
        LnImpl realLn = new LnImpl();

        this.sin = realSin;
        this.cos = realCos;
        this.sec = new SecImpl(realCos);
        this.cot = new CotImpl(realSin, realCos);
        this.ln = realLn;
        this.log3 = new LogNImpl(realLn, 3.0);
        this.log5 = new LogNImpl(realLn, 5.0);
        this.log10 = new LogNImpl(realLn, 10.0);
    }

    public SystemFunction(Function sin, Function cos, Function sec, Function cot,
                          Function ln, Function log3, Function log5, Function log10) {
        this.sin = sin;
        this.cos = cos;
        this.sec = sec;
        this.cot = cot;
        this.ln = ln;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public double compute(double x, double eps) {
        if (eps <= 0) {
            throw new IllegalArgumentException("eps должен быть положительным");
        }

        if (x <= 0) {
            double secV = sec.compute(x, eps);
            double cotV = cot.compute(x, eps);
            double cosV = cos.compute(x, eps);
            return (((Math.pow(secV, 3) / secV) * cotV) - Math.pow(cosV, 3)) * secV;
        } else {
            if (Math.abs(x - 1.0) < 1e-9) {
                return 0.0;
            }
            double l3 = log3.compute(x, eps);
            double l5 = log5.compute(x, eps);
            double l10 = log10.compute(x, eps);
            return (Math.pow(Math.pow(l3, 2), 3) * Math.pow(l5, 3)) * (l10 / l5);
        }
    }

    public void exportToCsv(String moduleName, double start, double end, double step, double eps, String filename) {
        if (step <= 0) throw new IllegalArgumentException("step должен быть > 0");

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("X,Result_" + moduleName.toUpperCase());

            for (double x = start; x <= end; x += step) {
                double result;
                try {
                    result = switch (moduleName.toLowerCase()) {
                        case "f" -> compute(x, eps);
                        case "sin" -> sin.compute(x, eps);
                        case "cos" -> cos.compute(x, eps);
                        case "sec" -> sec.compute(x, eps);
                        case "cot" -> cot.compute(x, eps);
                        case "ln" -> ln.compute(x, eps);
                        case "log3" -> log3.compute(x, eps);
                        case "log5" -> log5.compute(x, eps);
                        case "log10" -> log10.compute(x, eps);
                        default -> throw new IllegalArgumentException("Неизвестный модуль: " + moduleName);
                    };
                } catch (Exception e) {
                    result = Double.NaN;
                }
                writer.printf("%.6f,%.10f%n", x, result);
            }
            System.out.println("Файл сохранён: " + filename);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }
}