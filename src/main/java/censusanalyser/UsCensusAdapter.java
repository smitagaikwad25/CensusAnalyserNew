package censusanalyser;

import java.util.Map;

public class UsCensusAdapter extends CensusAdapter{

    @Override
    public <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> stringCensusDAOMap = super.loadCensusData(UsCensusCSV.class, csvFilePath[0]);
        return stringCensusDAOMap;
    }
}
