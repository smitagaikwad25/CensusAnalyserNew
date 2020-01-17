package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaSateCodeCSV {

    @CsvBindByName(column = "StateName ", required = true)
    public String stateName ;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaSateCodeCSV{" +
                "stateName='" + stateName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
