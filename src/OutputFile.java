import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OutputFile {
    private List<BigDecimal> floats;
    private List<BigInteger> integers;
    private List <String> strings;
    private String floatFileName;
    private String integerFileName;
    private String stringsFileName;

    public OutputFile() {
        floats = new ArrayList<>();
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        floatFileName = "";
        integerFileName = "";
        stringsFileName = "";
    }

    public List<BigDecimal> getFloats() {
        return floats;
    }

    public void setFloats(BigDecimal input) {
        floats.add(input);
    }

    public List<BigInteger> getIntegers() {
        return integers;
    }

    public void setIntegers(BigInteger input) {
        integers.add(input);
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(String input) {
        strings.add(input);
    }

    public void distributeAmongFiles(List<String> stringsInput) {
        for (int i = 0; i < stringsInput.size(); i++) {
            if (stringsInput.get(i).matches("(\\+|\\-)?\\d+(\\.|,)\\d+((E|e)\\+|(E|e)\\-|(E|e))?\\d+")) {

                this.setFloats(new BigDecimal(stringsInput.get(i)));
            } else if (stringsInput.get(i).matches("(\\+|\\-)?\\d+")) {
                this.setIntegers(new BigInteger(stringsInput.get(i)));
            } else if (stringsInput.get(i).isBlank()) {
                continue;
            } else {
                this.setStrings(stringsInput.get(i));
            }
        }
    }

    public void writeListsToFile(SelectedOptions selectedOptions) throws IOException {
        String pathSlash = "\\";

        if (selectedOptions.getPathOutput().isBlank()) {
            pathSlash = "";
        }
        if (!floats.isEmpty()) {
            floatFileName = selectedOptions.getPathOutput() + pathSlash + selectedOptions.getPrefixOutput() + "floats.txt";
            Main.writeListToFile(floats, floatFileName, selectedOptions.isConditionRewrite());
        }
        if (!integers.isEmpty()) {
            integerFileName = selectedOptions.getPathOutput() + pathSlash + selectedOptions.getPrefixOutput() + "integers.txt";
            Main.writeListToFile(integers, integerFileName, selectedOptions.isConditionRewrite());
        }
        if (!strings.isEmpty()) {
            stringsFileName = selectedOptions.getPathOutput() + pathSlash + selectedOptions.getPrefixOutput() + "strings.txt";
            Main.writeListToFile(strings, stringsFileName, selectedOptions.isConditionRewrite());
        }
    }

    public void fillListsFromFiles() {
        if (!floats.isEmpty()) {
            floats.clear();
            floats.addAll(MyFileReader.readFileToList(floatFileName).stream().map(string -> new BigDecimal(string)).toList());
        }
        if (!integers.isEmpty()) {
            integers.clear();
            integers.addAll(MyFileReader.readFileToList(integerFileName).stream().map(integer -> new BigInteger(integer)).toList());
        }
        if (!strings.isEmpty()) {
            strings.clear();
            strings.addAll(MyFileReader.readFileToList(stringsFileName));
        }
    }
}
