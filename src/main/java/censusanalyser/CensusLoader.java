package censusanalyser;

import com.bridgeLab.CSVBuilderException;
import com.bridgeLab.CsvBuilderFactory;
import com.bridgeLab.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusLoader {

List<CensusDAO> censusCsvLoadList = new ArrayList<CensusDAO>();;

    public <E> List<CensusDAO> LoadCensusData(String csvFilePath, Class<E> censusCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<E> csvList = csvBuilder.getCSVList(reader,censusCSV);

            if (censusCSV.getName().equals("censusanalyser.IndiaCensusCSV")) {
                csvList.stream().filter(CensusData -> censusCsvLoadList .add(new CensusDAO((IndiaCensusCSV) CensusData))).collect(Collectors.toList());
            }else  if (censusCSV.getName().equals("censusanalyser.UsCensusCSV")){
                csvList.stream().filter(CensusData -> censusCsvLoadList .add(new CensusDAO((UsCensusCSV) CensusData))).collect(Collectors.toList());
            }
            System.out.println(censusCsvLoadList );
            return censusCsvLoadList ;
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
