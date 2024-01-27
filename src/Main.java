import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // -o нужно уметь задавать путь для результатов
        // -p задает префикс имен выходных файлов
        // -a режим добавления в существующие файлы (по умолчанию - файлы перезаписываются)
        // -s краткая статистика (количество элементов в исходящие файлы)
        // -f полная статистика (для чисел: кол-во, мин, макс, сумма, среднее, для строк: кол-во, макс длина, мин длина.)
        // -ip расположение инпут файлов (нет в задании)
        // -info вывод информации по опциям

        String[] options = args;

        String pathOutput = "";

        String prefixOutput = "";

        boolean isShortStatistic = true;

        String pathInput = "";

        boolean conditionPathOutput = false;
        boolean conditionPrefix = false;
        boolean conditionRewrite = true;
        boolean conditionStatistic = false;
        boolean conditionPathInput = false;
        boolean conditionInfo = false;

        List<String> fileNames = new ArrayList<>();

        System.out.println("--- Для вызова справки по опциям введите -info при вызове программы ---");

        for (int i = 0; i < options.length; i++) {
            if (options[i].equals("-info")) {
                if (!conditionInfo) {
                    try (FileReader fileReader = new FileReader("readme.txt", StandardCharsets.UTF_8);
                         Scanner scanner = new Scanner(fileReader)) {
                        while (scanner.hasNextLine()) {
                            System.out.println(scanner.nextLine());
                        }
                        conditionInfo = true;
                    } catch (Exception e) {
                        System.out.println("!Файл с информацией (readme.txt) не доступен, возможно перемещен или удалён");
                    }
                }
            } else if (options[i].equals("-o")) {
                if (conditionPathOutput) {  //на случай если условие задают 2 раза во входных аргументах
                    System.out.println("!Такое условие уже задано: " + "conditionPathOutput");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            pathOutput = options[i + 1].replaceAll("\\|", " ").replaceAll("\\\\", "\\\\");
                            conditionPathOutput = true;
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка при чтении пути или путь отсутствует");
                        e.printStackTrace();
                    }
                }
            } else if (options[i].equals("-p")) {
                if (conditionPrefix) {  //на случай если условие задают 2 раза во входных аргументах
                    System.out.println("!Такое условие уже задано: " + "conditionPrefix");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            prefixOutput = options[i + 1].replaceAll("\\|", " ");
                            conditionPrefix = true;
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка при чтении префикса выходных файлов или он отсутствует");
                        e.printStackTrace();
                    }
                }

            } else if (options[i].equals("-ip")) {
                if (conditionPathInput) {  //на случай если условие задают 2 раза во входных аргументах
                    System.out.println("!Такое условие уже задано: " + "conditionPathInput");
                } else {
                    try {
                        if (options[i + 1].charAt(0) != '-') {
                            pathInput = options[i + 1].replaceAll("\\|", " ").replaceAll("\\\\", "\\\\");
                            conditionPathInput = true;
                        }
                    } catch (Exception e) {
                        System.out.println("!Ошибка при чтении пути входных файлов или он отсутствует");
                        e.printStackTrace();
                    }
                }
            } else if (options[i].equals("-a")) {
                if (!conditionRewrite) {
                    System.out.println("!Условие по записи уже задано");
                } else {
                    conditionRewrite = false;
                }
            } else if (options[i].equals("-f")) {
                if (conditionStatistic) {
                    System.out.println("!Условие по статистике уже задано");
                } else {
                    isShortStatistic = false;
                    conditionStatistic = true;
                }
            } else if (options[i].equals("-s")) {
                if (conditionStatistic) {
                    System.out.println("!Условие по статистике уже задано");
                } else {
                    isShortStatistic = true;
                    conditionStatistic = true;
                }
            } else if (options[i].contains(".txt")) {
                fileNames.add(options[i].replaceAll("\\|", " "));
            }
        }

        System.out.println("pathOutput: " + pathOutput);
        System.out.println("prefixOutput: " + prefixOutput);
        System.out.println("isShortStatistic: " + isShortStatistic);
        System.out.println("pathInput: " + pathInput);
        System.out.println("rewrite: " + conditionRewrite);
        System.out.println(fileNames);

        //блок по чтению
        List<String> stringsInput = new ArrayList<>();
        Scanner catchScanner = new Scanner(System.in);
        for (int i = 0; i < fileNames.size(); i++) {
            String path = fileNames.get(i);
            if (!pathInput.isEmpty()) {
                path = pathInput + "\\" + fileNames.get(i);
            }
            try (FileReader fileReader = new FileReader(path, StandardCharsets.UTF_8);
                 Scanner scanner = new Scanner(fileReader)) {
                while (scanner.hasNextLine()) {
                    String string = scanner.nextLine();
                    if (!string.isEmpty()) {
                        stringsInput.add(string.trim());
                    }
                }
            } catch (IOException ioException) {
                System.out.println("!Данный файл с именем \"" + fileNames.get(i) + "\" не найден, могли возникнуть " +
                        "проблемы с пробелами в названии файла или спецсимволами при считывании во время запуска. " +
                        "Попробуйте ввести название файла ещё раз (включая расширение) :");
                fileNames.add(i, catchScanner.nextLine());
                fileNames.remove(i + 1);
                i = i - 1;
                System.out.println("Если файл всё ещё не найден, попробуйте при запуске программы указать директорию с помощью опции -ip");
            }
        }
        catchScanner.close();

        System.out.println(stringsInput);
        List<BigDecimal> floats = new ArrayList<>();
        List<BigInteger> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < stringsInput.size(); i++) {
            if (stringsInput.get(i).matches("(\\+|\\-)?\\d+(\\.|,)\\d+((E|e)\\+|(E|e)\\-|(E|e))?\\d+")) {

                floats.add(new BigDecimal(stringsInput.get(i)));
            } else if (stringsInput.get(i).matches("(\\+|\\-)?\\d+")) {
                integers.add(new BigInteger(stringsInput.get(i)));
            } else if (stringsInput.get(i).isBlank()) {
                continue;
            } else {
                strings.add(stringsInput.get(i));
            }
        }
        String floatFileName = "";
        String integerFileName = "";
        String stringsFileName = "";

        if (!floats.isEmpty()) {
            floatFileName = pathOutput + "\\" + prefixOutput + "floats.txt";
            writeListToFile(floats, floatFileName, conditionRewrite);
        }
        if (!integers.isEmpty()) {
            integerFileName = pathOutput + "\\" + prefixOutput + "integers.txt";
            writeListToFile(integers, integerFileName, conditionRewrite);
        }
        if (!strings.isEmpty()) {
            stringsFileName = pathOutput + "\\" + prefixOutput + "strings.txt";
            writeListToFile(strings, stringsFileName, conditionRewrite);
        }
        List<BigDecimal> floatsAll = new ArrayList<>();
        List<BigInteger> integersAll = new ArrayList<>();
        List<String> stringsAll = new ArrayList<>();
        if (conditionRewrite) {
            Statistics.getStatistisc(floats, integers, strings, prefixOutput, isShortStatistic, conditionStatistic);
        } else {
            if (!floats.isEmpty()) {
                floatsAll.addAll(readFileToList(floatFileName).stream().map(string -> new BigDecimal(string)).toList());
            }
            if (!integers.isEmpty()) {
                integersAll.addAll(readFileToList(integerFileName).stream().map(integer -> new BigInteger(integer)).toList());
            }
            if (!strings.isEmpty()) {
                stringsAll.addAll(readFileToList(stringsFileName));
            }
            Statistics.getStatistisc(floatsAll, integersAll, stringsAll, prefixOutput, isShortStatistic, conditionStatistic);
        }
    }


    private static void writeListToFile(List<?> list, String fileName, boolean conditionRewrite) throws IOException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName, !conditionRewrite);
        } catch (IOException e) {
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            file.createNewFile();
            fileWriter = new FileWriter(fileName, !conditionRewrite);
        }
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Object o : list) {
                writer.write(o.toString() + "\n");
            }
        }
    }

    private static List<String> readFileToList(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine())!= null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл" + fileName + "не найден");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}