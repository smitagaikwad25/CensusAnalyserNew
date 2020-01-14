package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaSateCodeCSV {

    @CsvBindByName(column = "StateName ", required = true)
    public String StateName ;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    public String getStateCode() {
        return StateCode;
    }

    @Override
    public String toString() {
        return "IndiaSateCodeCSV{" +
                "StateName='" + StateName + '\'' +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }
}
