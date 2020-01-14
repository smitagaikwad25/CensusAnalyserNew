package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public float areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public String getState() {
        return state;
    }

}
