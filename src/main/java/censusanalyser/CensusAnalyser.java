package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> indiaCensusCSVIterable = () -> censusCSVIterator;
            int NumberOfRecords = (int) StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).count();
            return  NumberOfRecords ;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalArgumentException e){
            throw  new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
            try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
                CsvToBeanBuilder<IndiaSateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaSateCodeCSV.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<IndiaSateCodeCSV> csvToBean = csvToBeanBuilder.build();
                Iterator<IndiaSateCodeCSV> SateCodeCSVIterator = csvToBean.iterator();
                Iterable<IndiaSateCodeCSV> indiaSateCodeCSVIterable = () -> SateCodeCSVIterator;
                int NumberOfSateRecords = (int) StreamSupport.stream(indiaSateCodeCSVIterable.spliterator(), false).count();
                return  NumberOfSateRecords ;
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (IllegalArgumentException e){
                throw  new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }catch (RuntimeException e){
                throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
    }
}

