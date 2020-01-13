package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {
    @CsvBindByName(column = "StateId", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Totalarea", required = true)
    public float areaInSqKm;

    @CsvBindByName(column = "PopulationDensity", required = true)
    public float  populationDensity ;

}


