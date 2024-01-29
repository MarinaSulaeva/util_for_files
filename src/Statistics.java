import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Comparator;
import java.util.List;

public class Statistics {

    public static void getStatistisc(List<BigDecimal> floats, List<BigInteger> integers, List<String> strings, String prefix, boolean isShortStatistic, boolean conditionStatistic) {
        if (!isShortStatistic & conditionStatistic) {
            getShortStatistics(floats, integers,strings, prefix);
            getFullStatistics(floats, integers,strings, prefix);
        }
        if (isShortStatistic & conditionStatistic) {
            getShortStatistics(floats, integers,strings, prefix);
        }
    }
    private static void getShortStatistics(List<BigDecimal> floats, List<BigInteger> integers, List<String> strings, String prefix) {
        if (!floats.isEmpty()) {
            System.out.println("количество элементов, записанных в файл " + prefix + "floats.txt " + floats.size());
        }
        if (!integers.isEmpty()) {
            System.out.println("количество элементов, записанных в файл " + prefix + "integers.txt " + integers.size());
        }
        if (!strings.isEmpty()) {
            System.out.println("количество элементов, записанных в файл " + prefix + "integers.txt " + strings.size());
        }
    }

    private static void getFullStatistics(List<BigDecimal> floats, List<BigInteger> integers, List<String> strings, String prefix) {
        if (!floats.isEmpty()) {
            BigDecimal minFloat = floats.stream().min(BigDecimal::compareTo).get();
            BigDecimal maxFloat = floats.stream().max(BigDecimal::compareTo).get();
            BigDecimal summFloat = floats.stream().reduce(BigDecimal::add).get();
            BigDecimal avgFloat = summFloat.divide(new BigDecimal(floats.size()), MathContext.DECIMAL128);
            System.out.println("статистика по файлу " + prefix + "floats.txt: минимальное число - " + minFloat +
                    ", максимальное число - " + maxFloat +
                    ", сумма - " + summFloat +
                    ", среднее число - " + avgFloat);
        }
        if (!integers.isEmpty()) {
            BigInteger minInteger = integers.stream().min(BigInteger::compareTo).get();
            BigInteger maxInteger = integers.stream().max(BigInteger::compareTo).get();
            BigInteger sumInteger = integers.stream().reduce(BigInteger::add).get();
            BigDecimal avgInteger = new BigDecimal(sumInteger).divide(new BigDecimal(integers.size()), MathContext.DECIMAL128);
            System.out.println("статистика по файлу " + prefix + "integers.txt: минимальное число - " + minInteger +
                    ", максимальное число - " + maxInteger +
                    ", сумма - " + sumInteger +
                    ", среднее число - " + avgInteger);
        }
        if (!strings.isEmpty()) {
            Comparator<String> comparator = Comparator.comparingInt(String::length);
            int size = strings.size();
            int minLengthString = strings.stream().min(comparator).get().length();
            int maxLengthString = strings.stream().max(comparator).get().length();
            System.out.println("статистика по файлу " + prefix + "strings.txt: количество строк в файле - " + size +
                    ", количество символов с самой короткой строке - " + minLengthString +
                    ", количество символов с самой длинной строке - " + maxLengthString);
        }
    }
}
