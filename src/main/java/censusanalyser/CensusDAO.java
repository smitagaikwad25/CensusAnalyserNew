package censusanalyser;

public class CensusDAO {

        public String state;
        public String stateId;
        public int population;
        public int densityPerSqKm;
        public float areaInSqKm;
        public float  populationDensity ;

        public String getStateId() {
            return stateId;
        }

         public int getPopulation() {
            return population;
        }

        public String getState() {
            return state;
        }

        public int getDensityPerSqKm() {
            return densityPerSqKm;
        }

        public float getAreaInSqKm() {
            return areaInSqKm;
        }

        public float getPopulationDensity() {
            return populationDensity;
        }

        public CensusDAO(IndiaCensusCSV indiaCensusCSV){
            state = indiaCensusCSV.state;
            areaInSqKm = indiaCensusCSV.areaInSqKm;
            densityPerSqKm = indiaCensusCSV.densityPerSqKm;
            population = indiaCensusCSV.population;
        }

        public CensusDAO(UsCensusCSV usCensusCSV) {
            state = usCensusCSV.state;
            stateId = usCensusCSV.stateId;
            population = usCensusCSV.population;
            populationDensity = usCensusCSV.populationDensity;
            areaInSqKm = usCensusCSV.areaInSqKm;
        }


    }

