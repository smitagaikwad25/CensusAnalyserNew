package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String,CensusDAO> censusCSVMap = new HashMap<>();

    public  enum Country{
        INDIA,US
    }

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public <E> int loadCensusData (String... csvFilePath) throws CensusAnalyserException {

      censusCSVMap= new AdapterFactory().getCensusData(country, csvFilePath);
        return censusCSVMap.size();

    }

    public String sortDataOfCensunCSv() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List SortedCensusOutPut = censusCSVMap.values().stream()
                .sorted(Comparator.comparing(censusInFo -> censusInFo.state))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        String json = new Gson().toJson(SortedCensusOutPut);
        System.out.println(json);
        return json;
    }

    public String sortCensusDataAccordingPopulation() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List SortingPopulationWise = censusCSVMap.values().stream()
                .sorted(Comparator.comparing(censusInFo -> censusInFo.population))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(SortingPopulationWise);
        String json = new Gson().toJson(SortingPopulationWise );
        return json;
    }

    public String sortcensusdataaccordingdensity() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List SortingAccordingDensity= censusCSVMap.values().stream()
                .sorted(Comparator.comparing(censusInFo -> censusInFo.densityPerSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(SortingAccordingDensity);
        String json = new Gson().toJson(SortingAccordingDensity);
        System.out.println(json);
        return json;
    }

    public String sortCensusDataAccordingArea() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List SortingAccordingArea= censusCSVMap.values().stream()
                .sorted(Comparator.comparing(censusInFo -> censusInFo.areaInSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(SortingAccordingArea);
        System.out.println(SortingAccordingArea);
        String json = new Gson().toJson(SortingAccordingArea);
        return json;
    }

    public String sortSateCode() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List SortedStateWise = censusCSVMap.values().stream()
                .sorted(Comparator.comparing(censusInFo -> censusInFo.stateId))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        String json = new Gson().toJson(SortedStateWise);
        System.out.println(json);
        return json;
    }
}


