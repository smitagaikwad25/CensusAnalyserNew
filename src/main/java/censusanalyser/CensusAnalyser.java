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

    List<CensusDAO> censusCSVIList = null;
    List<IndiaStateCodeDAO> StateCodeCSVList = null;
    List<CensusDAO> UsCensusList = null;

    public CensusAnalyser() {
        this.censusCSVIList = new ArrayList<CensusDAO>();
        this.StateCodeCSVList = new ArrayList<IndiaStateCodeDAO>();
        this.UsCensusList = new ArrayList<CensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> csvList = csvBuilder.getCSVList(reader, IndiaCensusCSV.class);
            int i=0;
             while (i < csvList.size()){
                 this.censusCSVIList.add(new CensusDAO(csvList.get(i)));
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

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<UsCensusCSV> uscsvList = csvBuilder.getCSVList(reader,UsCensusCSV.class);
            uscsvList.stream().filter(UsCensusData -> UsCensusList.add( new CensusDAO(UsCensusData))).collect(Collectors.toList());
            System.out.println(UsCensusList);
            return UsCensusList.size();
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
        List<CensusDAO> SortedCensusOutPut = censusCSVIList.stream().sorted(Comparator.comparing(CensusDAO::getState)).collect(Collectors.toList());
        String json = new Gson().toJson(SortedCensusOutPut);
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingPopulation() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingPopulationWise = censusCSVIList.stream().sorted(Comparator.comparing(CensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingDensity() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingPopulationWise = censusCSVIList.stream().sorted(Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        System.out.println(SortingPopulationWise );
        String json = new Gson().toJson(SortingPopulationWise );
        System.out.println(json);
        return json;
    }

    public String SortCensusDataAccordingArea() throws CensusAnalyserException {
        if (censusCSVIList == null || censusCSVIList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.No_Census_Data);
        }
        List<CensusDAO> SortingAreaWise = censusCSVIList.stream().sorted(Comparator.comparing(CensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
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


