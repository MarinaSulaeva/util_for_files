import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("--- Для вызова справки по опциям введите -info при вызове программы ---");
        SelectedOptions selectedOptions = Handler.handlerRequest(args);
        List<String> stringsInput = MyFileReader.readInputFilesToList(selectedOptions);

        OutputFile outputFile = new OutputFile();
        outputFile.distributeAmongFiles(stringsInput);
        outputFile.writeListsToFile(selectedOptions);


        if (!selectedOptions.isConditionRewrite()) {
            outputFile.fillListsFromFiles();
        }
        Statistics.getStatistisc(outputFile.getFloats(), outputFile.getIntegers(), outputFile.getStrings(), selectedOptions.getPrefixOutput(), selectedOptions.isShortStatistic(), selectedOptions.isConditionStatistic());

    }


    public static void writeListToFile(List<?> list, String fileName, boolean conditionRewrite) throws IOException {
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
}