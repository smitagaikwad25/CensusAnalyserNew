package censusanalyser;

import com.bridgeLab.CSVBuilderException;
import com.bridgeLab.CsvBuilderFactory;
import com.bridgeLab.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List<IndiaCensusDAO> censusCSVIList = null;

    public CensusAnalyser() {
        this.censusCSVIList = new ArrayList<IndiaCensusDAO>();
    }

   List<IndiaSateCodeCSV> SateCodeCSVList = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> csvList = csvBuilder.getCSVList(reader, IndiaCensusCSV.class);
            int i=0;
             while (i < csvList.size()){
                 this.censusCSVIList.add(new IndiaCensusDAO(csvList.get(i)));
                 i++;
             }
             return censusCSVIList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            SateCodeCSVList = csvBuilder.getCSVList(reader, IndiaSateCodeCSV.class);
            return SateCodeCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String SortDataOfCensunCSv() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
            IndiaCensusDAO temp;
                for (int i = 0; i < censusCSVIList.size(); i++) {
                    IndiaCensusDAO input1 = censusCSVIList.get(i);
                    for (int j = 0; j < censusCSVIList.size() - 1; j++) {
                        IndiaCensusDAO input2 = censusCSVIList.get(j);
                        if (input1.state.compareTo(input2.state) < 0) {
                            temp = censusCSVIList.get(i);
                            censusCSVIList.set(i,censusCSVIList.get(j));
                            censusCSVIList.set(j,temp);
                        }
                    }
                }
                String json = new Gson().toJson(censusCSVIList);
                System.out.println(json);
                return json;
    }

    public String SortSateCode() throws CensusAnalyserException {
        if (SateCodeCSVList == null || SateCodeCSVList .size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        Object sortedSatecode = SateCodeCSVList.stream().sorted(Comparator.comparing(IndiaSateCodeCSV::getStateCode)).collect(Collectors.toList());
        System.out.println(sortedSatecode);
        String json = new Gson().toJson(sortedSatecode);
        System.out.println(json);
        return json;
    }
}


