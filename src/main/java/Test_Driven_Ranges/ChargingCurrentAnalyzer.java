package Test_Driven_Ranges;

import java.util.Arrays;


public class ChargingCurrentAnalyzer {


    public String getChargingCurrentStatisticsByRange(Integer[] integers) {
       ChargingCurrentStatistics currentStatistics = new ChargingCurrentStatistics();
       String rangeResult = currentStatistics.getFinalResult(integers);
       printChargingCurrentReadings(rangeResult);
       return rangeResult;
    }



    public void printChargingCurrentReadings(String result) {
        String[] arrRes = result.split(" ");
        Arrays.stream(arrRes).forEach(System.out::println);
    }


}
