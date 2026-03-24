import argparse
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pathlib import Path

def plot_csv_file(csv_path: str, save: bool = True, show: bool = True):
    """
    Визуализирует график из одного CSV-файла, сгенерированного SystemFunction.exportToCsv().
    Поддерживает NaN (точки, где выбрасывалось исключение).
    """
    csv_path = Path(csv_path)
    
    if not csv_path.exists():
        print(f"Ошибка: Файл {csv_path} не найден!")
        return
    
    df = pd.read_csv(csv_path)
    
    if len(df.columns) != 2:
        print("Ошибка: CSV должен содержать ровно 2 колонки (X, Result_...)")
        return
    
    x_col = df.columns[0]
    y_col = df.columns[1]
    
    module_name = y_col.replace("Result_", "").strip()
    if module_name.lower() == "f":
        module_name = "f(x) — система функций"
    else:
        module_name = f"{module_name}(x)"
    
    x = df[x_col].values
    y = pd.to_numeric(df[y_col], errors="coerce").values
    
    plt.figure(figsize=(12, 7))
    
    plt.plot(x, y, "b-", linewidth=2.5, label=module_name)
    
    nan_mask = np.isnan(y)
    if nan_mask.any():
        plt.plot(
            x[nan_mask],
            np.zeros_like(x[nan_mask]),
            "ro",
            markersize=5,
            label="Не определено (исключение)"
        )
    
    plt.title(f"График модуля: {module_name}", fontsize=16, pad=20)
    plt.xlabel("x", fontsize=14)
    plt.ylabel(module_name, fontsize=14)
    plt.grid(True, alpha=0.3)
    plt.axhline(0, color="black", linewidth=0.8, alpha=0.5)
    plt.axvline(0, color="red", linestyle="--", alpha=0.5, label="x = 0 (граница веток)")
    plt.ylim(-25, 25)
    
    plt.legend(fontsize=12)
    plt.tight_layout()
    
    if save:
        output_path = csv_path.with_suffix(".png")
        plt.savefig(output_path, dpi=300, bbox_inches="tight")
        print(f"График сохранён: {output_path}")
    
    if show:
        plt.show()
    else:
        plt.close()


def main():
    parser = argparse.ArgumentParser(
        description="Визуализатор графиков из CSV (лабораторная №2)"
    )
    parser.add_argument(
        "path",
        help="Путь к CSV-файлу ИЛИ к папке с CSV-файлами"
    )
    parser.add_argument(
        "--no-show",
        action="store_true",
        help="Не показывать графики на экране (только сохранить PNG)"
    )
    parser.add_argument(
        "--no-save",
        action="store_true",
        help="Не сохранять PNG-файлы"
    )
    
    args = parser.parse_args()
    
    path = Path(args.path)
    
    if path.is_file():
        plot_csv_file(
            path,
            save=not args.no_save,
            show=not args.no_show
        )
    elif path.is_dir():
        csv_files = list(path.glob("*.csv"))
        if not csv_files:
            print("В папке не найдено CSV-файлов!")
            return
        
        print(f"🔍 Найдено {len(csv_files)} CSV-файлов. Обрабатываем...")
        for csv_file in csv_files:
            print(f"\n{csv_file.name}")
            plot_csv_file(
                csv_file,
                save=not args.no_save,
                show=not args.no_show
            )
    else:
        print("Укажите существующий файл или папку!")


if __name__ == "__main__":
    main()
