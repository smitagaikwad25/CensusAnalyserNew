package censusanalyser;

public class IndiaCensusDAO {
    public int areaInSqKm;
    public String state;
    public int densityPerSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV){
         state = indiaCensusCSV.state;
         areaInSqKm = indiaCensusCSV.areaInSqKm;
         densityPerSqKm = indiaCensusCSV.densityPerSqKm;
         population = indiaCensusCSV.population;
    }
}
