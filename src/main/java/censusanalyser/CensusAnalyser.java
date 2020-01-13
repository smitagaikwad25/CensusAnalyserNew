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

    List<CensusDAO> censusCSVList = null;
    List<IndiaStateCodeDAO> StateCodeCSVList = null;
    List<CensusDAO> UsCensusList = null;

    public CensusAnalyser() {
        this.censusCSVList = new ArrayList<CensusDAO>();
        this.StateCodeCSVList = new ArrayList<IndiaStateCodeDAO>();
        this.UsCensusList = new ArrayList<CensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
      censusCSVList = new CensusLoader().LoadCensusData(csvFilePath,IndiaCensusCSV.class);
     return censusCSVList.size();
    }
    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
         censusCSVList = new CensusLoader().LoadCensusData(csvFilePath,UsCensusCSV.class);
        return censusCSVList.size();

    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<IndiaSateCodeCSV> csvList = csvBuilder.getCSVList(reader, IndiaSateCodeCSV.class);
            csvList.stream().filter(stateCensusData -> StateCodeCSVList.add( new IndiaStateCodeDAO(stateCensusData))).collect(Collectors.toList());
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
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortedCensusOutPut = censusCSVList.stream().sorted(Comparator.comparing(CensusDAO::getState)).collect(Collectors.toList());
        String json = new Gson().toJson(SortedCensusOutPut);
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingPopulation() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingPopulationWise = censusCSVList.stream().sorted(Comparator.comparing(CensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingDensity() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingPopulationWise = censusCSVList.stream().sorted(Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingArea() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingAreaWise = censusCSVList.stream().sorted(Comparator.comparing(CensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
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


