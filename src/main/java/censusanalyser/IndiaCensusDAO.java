package censusanalyser;

public class IndiaCensusDAO {

    public String state;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;

    public int getPopulation() {
        return population;
    }

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV){
         state = indiaCensusCSV.state;
         areaInSqKm = indiaCensusCSV.areaInSqKm;
         densityPerSqKm = indiaCensusCSV.densityPerSqKm;
         population = indiaCensusCSV.population;
    }
}
