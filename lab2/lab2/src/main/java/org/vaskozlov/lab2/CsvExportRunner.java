package org.vaskozlov.lab2;

import org.vaskozlov.lab2.real.*;
import org.vaskozlov.lab2.stub.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CsvExportRunner {

    private static final double START = -2 * Math.PI;
    private static final double END   =  2 * Math.PI;
    private static final double STEP  = 0.05;
    private static final double EPS   = 1e-8;

    private static final String OUTPUT_DIR = "csv_exports";

    public static void main(String[] args) {
        new File(OUTPUT_DIR).mkdirs();

        System.out.println("Запуск экспорта CSV по всем этапам интеграции...\n");

        System.out.println("Этап 0 — все заглушки");
        exportStage("stage0_all_stubs",
                new SinStub(), new CosStub(), new SecStub(), new CotStub(),
                new LnStub(), new Log3Stub(), new Log5Stub(), new Log10Stub());

        System.out.println("Этап 1 — SinImpl + заглушки");
        exportStage("stage1_sin_real",
                new SinImpl(), new CosStub(), new SecStub(), new CotStub(),
                new LnStub(), new Log3Stub(), new Log5Stub(), new Log10Stub());

        System.out.println("Финальный этап — полная реальная система");
        exportStage("final_full_system",
                null, null, null, null, null, null, null, null);

        System.out.println("\nВсе CSV-файлы успешно сохранены в папке ./" + OUTPUT_DIR);
        System.out.println("Теперь можно запустить plot_csv.py для визуализации графиков.");
    }

    private static void exportStage(String stagePrefix, Function sin, Function cos, Function sec, Function cot,
                                    Function ln, Function log3, Function log5, Function log10) {

        SystemFunction system;

        if (sin == null) {
            system = new SystemFunction();
        } else {
            system = new SystemFunction(sin, cos, sec, cot, ln, log3, log5, log10);
        }

        List<String> modules = Arrays.asList("f", "sin", "cos", "cot", "ln", "log10");

        for (String module : modules) {
            String filename = OUTPUT_DIR + "/" + stagePrefix + "_" + module + ".csv";
            try {
                system.exportToCsv(module, START, END, STEP, EPS, filename);
                System.out.println(module.toUpperCase() + " → " + filename);
            } catch (Exception e) {
                System.err.println("Ошибка при выгрузке " + module + ": " + e.getMessage());
            }
        }
        System.out.println();
    }
}