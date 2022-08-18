package Test_Driven_Ranges;

import java.util.Arrays;


public class ChargingCurrentAnalyzer {


    public String getChargingCurrentStatisticsByRange(Integer[] integers) {
       if(validateNumberSeries(integers)) {
           ChargingCurrentStatistics currentStatistics = new ChargingCurrentStatistics();
           String rangeResult = currentStatistics.getFinalResult(integers);
           printChargingCurrentReadings(rangeResult);
           return rangeResult;
       }
       return null;
    }

    private boolean validateNumberSeries(Integer[] integers) {
        return integers != null && integers.length != 0 ;
    }


    public void printChargingCurrentReadings(String result) {
        System.out.println("Ranges  Readings");
        String[] arrRes = result.split(" ");
        Arrays.stream(arrRes).forEach(System.out::println);
    }


}
