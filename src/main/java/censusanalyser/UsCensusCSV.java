package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {
    @CsvBindByName(column = "StateId", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Housingunits", required = true)
    public float housingunits;

    @CsvBindByName(column = "Totalarea", required = true)
    public float totalarea;

    @CsvBindByName(column = "Waterarea", required = true)
    public float waterarea;

    @CsvBindByName(column = "Landarea", required = true)
    public float landarea;

    @CsvBindByName(column = "PopulationDensity", required = true)
    public float populationDensity;

    @CsvBindByName(column = "HousingDensity", required = true)
    public float housingDensity;

    @Override
    public String toString() {
        return "UsCensusCSV{" +
                "stateId='" + stateId + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingunits=" + housingunits +
                ", totalarea=" + totalarea +
                ", waterarea=" + waterarea +
                ", landarea=" + landarea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}


