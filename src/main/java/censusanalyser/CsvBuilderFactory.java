package censusanalyser;

public class CsvBuilderFactory {

    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder();
    }
}
