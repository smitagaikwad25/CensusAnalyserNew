package censusanalyser;

import com.bridgeLab.CSVBuilderException;
import com.bridgeLab.CsvBuilderFactory;
import com.bridgeLab.ICSVBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return getCount(censusCSVIterator);
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
            Iterator<IndiaSateCodeCSV> SateCodeCSVIterator = csvBuilder.getCSVIterator(reader, IndiaSateCodeCSV.class);
            return getCount(SateCodeCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

//    public ArrayList SortIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
//        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
//            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
//            Iterator<IndiaSateCodeCSV> SateCodeCSVIterator = csvBuilder.getCSVIterator(reader, IndiaSateCodeCSV.class);
//            return getSortedData(SateCodeCSVIterator);
//        } catch (IOException e) {
//            throw new CensusAnalyserException(e.getMessage(),
//                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
//        } catch (RuntimeException e) {
//            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
//        }catch (CSVBuilderException e) {
//            throw new CensusAnalyserException(e.getMessage(), e.type.name());
//        }
//    }
//
//    private ArrayList getSortedData(Iterator<IndiaSateCodeCSV> sateCodeCSVIterator) {
//        ArrayList list = new ArrayList();
//        list.add(sateCodeCSVIterator);
//        while (sateCodeCSVIterator.hasNext())
//        {
//
//        }
//        return list;
//    }

    private <E> int getCount(Iterator<E> CSVIterator) {
        Iterable<E> CSVIterable = () -> CSVIterator;
        int NumberOfRecords = (int) StreamSupport.stream(CSVIterable.spliterator(), false).count();
        return NumberOfRecords;
    }

    public JsonArray SortDate(String csvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CsvBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV>  censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return SortData(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private JsonArray SortData(Iterator<IndiaCensusCSV> censusCSVIterator) {
        Iterator<IndiaCensusCSV> csv = censusCSVIterator;
        ArrayList list = new ArrayList();
            while (csv.hasNext()) {
                list.add(csv.next().toString());
            }
            String temp;
                for (int i = 0; i < list.size(); i++) {
                    String input1 = (String) list.get(i);
                    for (int j = 0; j < list.size() - 1; j++) {
                        String input2 = (String) list.get(j);
                        if (input1.compareTo(input2) < 0) {
                            temp = (String) list.get(i);
                           list.set(i,list.get(j));
                           list.set(j,temp);
                        }
                    }
                }
                Gson gson = new GsonBuilder().create();
                JsonArray jsonElements = gson.toJsonTree(list).getAsJsonArray();
                return jsonElements;
    }
}


