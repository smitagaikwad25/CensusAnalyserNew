package censusanalyser;

import com.bridgeLab.CSVBuilderException;
import com.bridgeLab.CsvBuilderFactory;
import com.bridgeLab.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    @Override
    public <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> stringCensusDAOMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        this.loadIndiaStateCodeData(stringCensusDAOMap,csvFilePath[1]);
        return stringCensusDAOMap;
    }

    public int loadIndiaStateCodeData(Map<String,CensusDAO> censusCsvMap, String csvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaSateCodeCSV> csvIterator = csvBuilder.getCSVIterator(reader, IndiaSateCodeCSV.class);
            Iterable<IndiaSateCodeCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .filter(csvState -> censusCsvMap.get(csvState.stateName) != null )
                    .forEach(csvState ->  censusCsvMap.get(csvState.stateName).stateId = csvState.stateCode);
            return  censusCsvMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }


}
