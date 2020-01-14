package censusanalyser;

public class IndiaStateCodeDAO {

    public String StateName ;
    public String StateCode;

    public String getStateCode() {
        return StateCode;
    }

    public IndiaStateCodeDAO(IndiaSateCodeCSV indiaSateCodeCSV) {
        StateName = indiaSateCodeCSV.StateName;
         StateCode = indiaSateCodeCSV.StateCode;
    }

}
