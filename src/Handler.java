import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Handler {
    // -o задаёт путь для результатов
    // -p задает префикс имен выходных файлов
    // -a режим добавления в существующие файлы (по умолчанию - файлы перезаписываются)
    // -s краткая статистика (количество элементов в исходящие файлы)
    // -f полная статистика (для чисел: кол-во, мин, макс, сумма, среднее, для строк: кол-во, макс длина, мин длина.)
    // -ip расположение инпут файлов (нет в задании)
    // -info вывод информации по опциям
    public static SelectedOptions handlerRequest(String[] options) {
        SelectedOptions selectedOptions = new SelectedOptions();
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals("-info")) {
                if (!selectedOptions.isConditionInfo()) {
                    try (FileReader fileReader = new FileReader("readme.txt", StandardCharsets.UTF_8);
                         Scanner scanner = new Scanner(fileReader)) {
                        while (scanner.hasNextLine()) {
                            System.out.println(scanner.nextLine());
                        }
                        selectedOptions.setConditionInfo(true);
                    } catch (Exception e) {
                        System.out.println("!Файл с информацией (readme.txt) не доступен, возможно перемещен или удалён");
                    }
                }
            } else if (options[i].equals("-o")) {
                if (selectedOptions.isConditionPathOutput()) {  //на случай если условие задают более 1 раза во входных аргументах
                    System.out.println("!!Путь выходных файлов указан более 1 раза, будет взят первый аргумент");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            selectedOptions.setPathOutput(options[i + 1].replaceAll("\\\\", "\\\\"));
                            selectedOptions.setConditionPathOutput(true);
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка опции \"-o\" при чтении пути или путь отсутствует, будет использован корневой каталог.");
                    }
                }
            } else if (options[i].equals("-p")) {
                if (selectedOptions.isConditionPrefix()) {  //на случай если условие задают более 1 раза во входных аргументах
                    System.out.println("!Префикс входных файлов указан более 1 раза, будет взят первый аргумент");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            selectedOptions.setPrefixOutput(options[i + 1]);
                            selectedOptions.setConditionPrefix(true);
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка при чтении префикса выходных файлов или он отсутствует, префикс использован не будет");
                    }
                }

            } else if (options[i].equals("-ip")) {
                if (selectedOptions.isConditionPathInput()) {  //на случай если условие задают более 1 раза во входных аргументах
                    System.out.println("!Путь входных файлов указан более 1 раза, будет взят первый аргумент");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            selectedOptions.setPathInput(options[i + 1].replaceAll("\\\\", "\\\\"));
                            selectedOptions.setConditionPathInput(true);
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка опции \"-ip\" при чтении пути или он отсутствует, будет использован корневой каталог.");
                    }
                }
            } else if (options[i].equals("-a")) {
                if (!selectedOptions.isConditionRewrite()) {
                    System.out.println("!Условие по записи в существующие файлы задано более 1 раза");
                } else {
                    selectedOptions.setConditionRewrite(false);
                }
            } else if (options[i].equals("-f")) {
                if (selectedOptions.isConditionStatistic()) {
                    System.out.println("!Условие по статистике указано более 1 раза, будет взят первый аргумент");
                } else {
                    selectedOptions.setShortStatistic(false);
                    selectedOptions.setConditionStatistic(true);
                }
            } else if (options[i].equals("-s")) {
                if (selectedOptions.isConditionStatistic()) {
                    System.out.println("!Условие по статистике указано более 1 раза, будет взят первый аргумент");
                } else {
                    selectedOptions.setShortStatistic(true);
                    selectedOptions.setConditionStatistic(true);
                }
            } else if (options[i].contains(".txt")) {
                selectedOptions.setFileNames(options[i]);
            }
        }
        return selectedOptions;
    }
}
