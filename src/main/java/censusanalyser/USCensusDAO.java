package censusanalyser;

public class USCensusDAO {
    public String StateId;
    public String State;
    public float Population;
    public float Housingunits;
    public float Totalarea;
    public float Waterarea;
    public float Landarea;
    public float PopulationDensity;
    public float HousingDensity;

    public USCensusDAO(UsCensusCSV usCensusCSV) {
        StateId = usCensusCSV.stateId;
        State = usCensusCSV.state;
        Population = usCensusCSV.population;
        Housingunits = usCensusCSV.housingunits;
        Totalarea = usCensusCSV.totalarea;
        Waterarea = usCensusCSV.waterarea;
        Landarea = usCensusCSV.landarea;
        PopulationDensity = usCensusCSV.populationDensity;
        HousingDensity = usCensusCSV.housingDensity;
    }
}
