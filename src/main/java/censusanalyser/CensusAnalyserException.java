package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        UNABLE_TO_PARSE, No_Census_Data, INCORRECT_COUNTRY, CENSUS_FILE_PROBLEM
    }

    ExceptionType type;

    public CensusAnalyserException(String mesage, String name) {
        super(mesage);
        this.type = ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
