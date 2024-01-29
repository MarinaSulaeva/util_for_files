import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {
    public static List<String> readInputFilesToList(SelectedOptions selectedOptions) {
        List<String> stringsInput = new ArrayList<>();
        Scanner catchScanner = new Scanner(System.in);
        for (int i = 0; i < selectedOptions.getFileNames().size(); i++) {
            String path = selectedOptions.getFileNames().get(i);
            if (!selectedOptions.getPathInput().isEmpty()) {
                path = selectedOptions.getPathInput() + "\\" + selectedOptions.getFileNames().get(i);
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
                System.out.println("!Данный файл с именем \"" + selectedOptions.getFileNames().get(i) + "\" не найден, могли возникнуть " +
                        "проблемы с пробелами в названии файла или спецсимволами при считывании во время запуска. " +
                        "Попробуйте ввести название файла в строке ниже ещё раз (с расширением и пробелом в названии файла если он есть). Для выхода из программы введите \"exit\"");
                String correctFileNameOrExit = catchScanner.nextLine();
                if (correctFileNameOrExit.equals("exit")) {
                    System.out.println("---Выход из программы---");
                    System.exit(999);
                }
                selectedOptions.getFileNames().add(i, correctFileNameOrExit);
                selectedOptions.getFileNames().remove(i + 1);
                i = i - 1;
                System.out.println("--- Если файл не удаётся найти, попробуйте при перезапуске программы указать директорию входных файлов с помощью опции -ip ---");
            }
        }
        catchScanner.close();
        return stringsInput;
    }

    public static List<String> readFileToList(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine())!= null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName + " не найден");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
