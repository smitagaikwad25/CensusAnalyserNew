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

    public String getState() {
        return state;
    }

    public UsCensusCSV(String stateId, String state, int population, float areaInSqKm, float populationDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.populationDensity = populationDensity;
    }
}


