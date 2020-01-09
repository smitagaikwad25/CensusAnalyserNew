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
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVIList = csvBuilder.getCSVList(reader, IndiaCensusCSV.class);
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
            List<IndiaSateCodeCSV> SateCodeCSVList = csvBuilder.getCSVList(reader, IndiaSateCodeCSV.class);
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

    public String SortDate(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV>  censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return SortDataofCensunCSv(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private String SortDataofCensunCSv(Iterator<IndiaCensusCSV> censusCSVIterator) {
        Iterator<IndiaCensusCSV> csv = censusCSVIterator;
        List<IndiaCensusCSV> list = new ArrayList();
            while (csv.hasNext()) {
                list.add(csv.next());
            }
            IndiaCensusCSV temp;
                for (int i = 0; i < list.size(); i++) {
                    IndiaCensusCSV input1 = list.get(i);
                    for (int j = 0; j < list.size() - 1; j++) {
                        IndiaCensusCSV input2 = list.get(j);
                        if (input1.state.compareTo(input2.state) < 0) {
                            temp = list.get(i);
                           list.set(i,list.get(j));
                           list.set(j,temp);
                        }
                    }
                }
                String json = new Gson().toJson(list);
                System.out.println(json);
                return json;
    }

    public String SortSateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaSateCodeCSV>  SateCodeCSVIterator = csvBuilder.getCSVIterator(reader, IndiaSateCodeCSV .class);
            return SortSateCode(SateCodeCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private String SortSateCode(Iterator<IndiaSateCodeCSV> sateCodeCSVIterator) {
        Iterator<IndiaSateCodeCSV> csv = sateCodeCSVIterator;
        ArrayList list = new ArrayList();
        while (csv.hasNext()) {
            list.add(csv.next());
        }
        Object sortedSatecode = list.stream().sorted(Comparator.comparing(IndiaSateCodeCSV::getStateCode)).collect(Collectors.toList());
        System.out.println(sortedSatecode);
        String json = new Gson().toJson(sortedSatecode);
        System.out.println(json);
        return json;
    }
}


