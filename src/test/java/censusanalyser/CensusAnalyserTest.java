package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH12 = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INDIA_CENSUS_CSV_FILE_PATH123 = "./src/test/resources/IndiaStateCensusData1.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE = "./src/main/resources/IndiaStateCode.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE1 = "./src/test/resources/IndiaStateCode.txt";
    private static final String INDIA_STATE_CODE_CSV_FILE123 = "./src/test/resources/IndiaSateCode1.csv";
    private static final String EMPTY_CSV_FILE = "./src/test/resources/EmptyCSVFile.csv";
    private static final String EMPTY_STATE_CODE_CSV_FILE = "./src/test/resources/EmptyStateCodeCsv.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH12);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH123);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH123);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateode_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE1);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE123);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE123);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensus_WhenCorrect_ShouldReturnSortedOutPut() {
       try {
           CensusAnalyser censusAnalyser = new CensusAnalyser();
           censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
           String sortedOutput = censusAnalyser.SortDataOfCensunCSv();
           IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
           Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
       } catch (CensusAnalyserException e) {
           e.printStackTrace();
       }
    }

    @Test
    public void givenIndiaCensus_WhenSorted_ReturnLastSortedOutPut() {
    try {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedOutput = censusAnalyser.SortDataOfCensunCSv();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
        Assert.assertEquals("West Bengal", censusCSV[28].state);
    } catch (CensusAnalyserException e) {
       e.printStackTrace();
    }
    }

    @Test
    public void givenIndiaCensus_WhenSortAccordingPopulation_ShouldReturnSortedOutPut() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
                String sortedOutput = censusAnalyser.SortCensusDataAccordingPopulation();
                IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
                Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            } catch (CensusAnalyserException e) {
                e.printStackTrace();
            }
        }

    @Test
    public void givenIndiaCensus_WhenSortAccordingPopulation_ShouldReturnSortedleastPopulationState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH); String sortedOutput = censusAnalyser.SortCensusDataAccordingPopulation();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCSV[28].state);
            } catch (CensusAnalyserException e) {
                e.printStackTrace();
            }
    }


    @Test
    public void givenIndiaCensus_WhenSortAccordingDensity_ShouldReturnSortedOutPut() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedOutput = censusAnalyser.SortCensusDataAccordingDensity();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensus_WhenSortAccordingDensity_ShouldReturnSortedLeastDensityState() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedOutput = censusAnalyser.SortCensusDataAccordingDensity();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[28].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensus_WhenSortAccordingAreaInSqKm_ShouldReturnSortedOutPut() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedOutput = censusAnalyser.SortCensusDataAccordingArea();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensus_WhenSortAccordingAreaInSqkm_ShouldReturnLeastAreaInSqKm() throws CensusAnalyserException {
       try {
           CensusAnalyser censusAnalyser = new CensusAnalyser();
           censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
           String sortedOutput = censusAnalyser.SortCensusDataAccordingArea();
           IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
           Assert.assertEquals("Goa", censusCSV[28].state);
       } catch (CensusAnalyserException e) {
           e.printStackTrace();
       }
    }

    @Test
    public void givenIndiaCensus_WhenNullOrEmpty_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(EMPTY_CSV_FILE);
            String sortedOutput = censusAnalyser.SortDataOfCensunCSv();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.No_Census_Data, e.type);

        }
    }

    @Test
    public void givenIndiaCensus_WhenNotCorrect_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            String sortedOutput = censusAnalyser.SortDataOfCensunCSv();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenCorrect_ShouldReturnSortedOutPut() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE);
            String sortedOutput = censusAnalyser.SortSateCode();
            IndiaSateCodeCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaSateCodeCSV[].class);
            Assert.assertEquals("AD", censusCSV[0].getStateCode());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WhenSort_ReturnLastSortedOutPut()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE);
            String sortedOutput = censusAnalyser.SortSateCode();
            IndiaSateCodeCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaSateCodeCSV[].class);
            Assert.assertEquals("WB", censusCSV[36].getStateCode());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WhenEmptyOrNull_ReturnException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateCodeData(EMPTY_STATE_CODE_CSV_FILE);
            String sortedOutput = censusAnalyser.SortSateCode();
            IndiaSateCodeCSV[] censusCSV = new Gson().fromJson(sortedOutput, IndiaSateCodeCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.No_Census_Data, e.type);
        }
    }
}

