package censusanalyser;

import com.bridgeLab.CSVBuilderException;
import com.bridgeLab.CsvBuilderFactory;
import com.bridgeLab.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String,CensusDAO> censusCSVMap = new HashMap<>();

    public  enum Country{
        INDIA,US
    }

    private Country country;

    public <E> int loadCensusData (Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return this.loadIndiaCensusData(IndiaCensusCSV.class,csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return this.loadUSCensusData(UsCensusCSV.class, csvFilePath);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
        }
    }

    public int loadIndiaCensusData(Class csvClass,String... csvFilePath) throws CensusAnalyserException {
      censusCSVMap =  new CensusLoader().LoadCensusData(IndiaCensusCSV.class,csvFilePath);
      return censusCSVMap.size();
    }
    public int loadUSCensusData(Class csvClass,String... csvFilePath) throws CensusAnalyserException {
        censusCSVMap =new CensusLoader().LoadCensusData(UsCensusCSV.class,csvFilePath);
        return censusCSVMap.size();

    }

    public String SortDataOfCensunCSv() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List <CensusDAO> sortedList = censusCSVMap.values().stream().collect(Collectors.toList());
        List<CensusDAO> SortedCensusOutPut = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getState)).collect(Collectors.toList());

        String json = new Gson().toJson(SortedCensusOutPut);
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingPopulation() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List <CensusDAO> sortedList = censusCSVMap.values().stream().collect(Collectors.toList());
        List<CensusDAO> SortingPopulationWise = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        return json;
    }

    public String SortCensusDataAccordingDensity() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List <CensusDAO> sortedList = censusCSVMap.values().stream().collect(Collectors.toList());
        List<CensusDAO> SortingPopulationWise = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingArea() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List <CensusDAO> sortedList = censusCSVMap.values().stream().collect(Collectors.toList());
        List<CensusDAO> SortingAreaWise = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingAreaWise);
        String json = new Gson().toJson(SortingAreaWise);
        System.out.println(json);
        return json;
    }

    public String SortSateCode() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List <CensusDAO> sortedList = censusCSVMap.values().stream().collect(Collectors.toList());
        List<CensusDAO> sortedSatecode = sortedList.stream().sorted(Comparator.comparing(CensusDAO::getStateId)).collect(Collectors.toList());
        System.out.println(sortedSatecode);
        String json = new Gson().toJson(sortedSatecode);
        System.out.println(json);
        return json;
    }
}


