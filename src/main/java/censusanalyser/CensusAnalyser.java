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
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List<IndiaCensusDAO> censusCSVIList = null;
    List<IndiaStateCodeDAO> StateCodeCSVList = null;

    public CensusAnalyser() {
        this.censusCSVIList = new ArrayList<IndiaCensusDAO>();
        this.StateCodeCSVList = new ArrayList<IndiaStateCodeDAO>();
    }

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
            List<IndiaSateCodeCSV> csvList = csvBuilder.getCSVList(reader, IndiaSateCodeCSV.class);
            int i=0;
            while (i< csvList.size()){
                this.StateCodeCSVList.add(new IndiaStateCodeDAO(csvList.get(i)));
                i++;
            }
            return StateCodeCSVList.size();
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
        List<IndiaCensusDAO> SortedCensusOutPut = censusCSVIList.stream().sorted(Comparator.comparing(IndiaCensusDAO::getState)).collect(Collectors.toList());
        String json = new Gson().toJson(SortedCensusOutPut);
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingPopulation() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<IndiaCensusDAO> SortingPopulationWise = censusCSVIList.stream().sorted(Comparator.comparing(IndiaCensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingDensity() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<IndiaCensusDAO> SortingPopulationWise = censusCSVIList.stream().sorted(Comparator.comparing(IndiaCensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingArea() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<IndiaCensusDAO> SortingAreaWise = censusCSVIList.stream().sorted(Comparator.comparing(IndiaCensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingAreaWise);
        String json = new Gson().toJson(SortingAreaWise);
        System.out.println(json);
        return json;
    }


    public String SortSateCode() throws CensusAnalyserException {
        if (StateCodeCSVList == null || StateCodeCSVList .size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<IndiaStateCodeDAO> sortedSatecode = StateCodeCSVList.stream().sorted(Comparator.comparing(IndiaStateCodeDAO::getStateCode)).collect(Collectors.toList());
        System.out.println(sortedSatecode);
        String json = new Gson().toJson(sortedSatecode);
        System.out.println(json);
        return json;
    }
}


