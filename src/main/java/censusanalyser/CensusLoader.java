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

public class CensusLoader {

    Map<String,CensusDAO> censusCsvMap = new HashMap<>();

    public <E> Map<String,CensusDAO> LoadCensusData(Class<E> censusCSV, String... csvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator= csvBuilder.getCSVIterator(reader,censusCSV);
            Iterable<E> csvIterable = () -> csvIterator;

            if (censusCSV.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(),false).map(IndiaCensusCSV.class::cast)
                        .forEach(censusCsv -> censusCsvMap.put(censusCsv.getState(),new CensusDAO(censusCsv)));
            }else  if (censusCSV.getName().equals("censusanalyser.UsCensusCSV")){
                StreamSupport.stream(csvIterable.spliterator(),false).map(UsCensusCSV.class::cast)
                        .forEach(censusCsv -> censusCsvMap.put(censusCsv.getState(),new CensusDAO(censusCsv)));
            }
            if (csvFilePath.length == 1 ) return censusCsvMap;
             this.loadIndiaStateCodeData(censusCsvMap,csvFilePath[1]);
             return censusCsvMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }

    private int loadIndiaStateCodeData(Map<String,CensusDAO> censusCsvMap, String csvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaSateCodeCSV> csvIterator = csvBuilder.getCSVIterator(reader, IndiaSateCodeCSV.class);
            Iterable<IndiaSateCodeCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .filter(csvState -> censusCsvMap.get(csvState.StateName) != null )
                    .forEach(csvState ->  censusCsvMap.get(csvState.StateName).stateId = csvState.StateCode);
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
