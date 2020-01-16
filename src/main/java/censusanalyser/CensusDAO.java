package censusanalyser;

public class CensusDAO {

        public String state;
        public String stateId;
        public int population;
        public int densityPerSqKm;
        public int areaInSqKm;
        public float  populationDensity ;

        public CensusDAO(IndiaCensusCSV indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = (int) indiaCensusCSV.areaInSqKm;
            densityPerSqKm = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
        }

        public CensusDAO(UsCensusCSV usCensusCSV) {
            state = usCensusCSV.state;
            stateId = usCensusCSV.stateId;
            population = usCensusCSV.population;
            populationDensity = usCensusCSV.populationDensity;
            areaInSqKm = (int) usCensusCSV.areaInSqKm;
        }


    public Object getCensusDTO(CensusAnalyser.Country country) {
            if (country.equals(CensusAnalyser.Country.US))
                return new UsCensusCSV(stateId,state,population,populationDensity,areaInSqKm);
            return new IndiaCensusCSV(state,areaInSqKm,densityPerSqKm,population);
    }
}









