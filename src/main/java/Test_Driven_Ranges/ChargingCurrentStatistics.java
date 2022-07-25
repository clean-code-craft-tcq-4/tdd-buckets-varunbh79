package Test_Driven_Ranges;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChargingCurrentStatistics {


    private Map<ValueRange,Integer> getCurrentRangeMapBasedOnInput(Integer[] inputSample) {
        List<Integer> sortedCurrentRange = sortCurrentRange(inputSample);
        return storeReadingsBasedOnRange(sortedCurrentRange);
    }

    private boolean validateInputSeries(Integer[] integers) {
        return integers != null && integers.length != 0;
    }

    public String getFinalResult(Integer[] integers) {

        if(validateInputSeries(integers)) {
            Map<ValueRange, Integer> currentRangeMap = getCurrentRangeMapBasedOnInput(integers);
            return currentRangeMap.entrySet().stream()
                    .map(ChargingCurrentStatistics::transformMapToString)
                    .collect(Collectors.joining(" "));
        }
       return null;
    }

    private static String transformMapToString(Map.Entry<ValueRange, Integer> rangeMap) {
        return  String.format("%s-%s,%d",rangeMap.getKey().getMinimum()
                ,rangeMap.getKey().getMaximum(),rangeMap.getValue());
    }

    private List<Integer> sortCurrentRange(Integer[] integers) {
        List<Integer> integerList = Arrays.asList(integers);
        Collections.sort(integerList);
        return integerList;
    }

    private Map<ValueRange,Integer> storeReadingsBasedOnRange(List<Integer> integerList) {
        int currentVal = integerList.get(0);
        List<Integer> intermediateResult = new ArrayList<>();
        Map<ValueRange,Integer> rangeMap = new LinkedHashMap<>();
        intermediateResult.add(currentVal);
        for (int i = 1; i < integerList.size(); i++) {
            if((integerList.get(i)-currentVal) <=1){
                currentVal = integerList.get(i);
                intermediateResult.add(currentVal);
            } else {
                currentVal = integerList.get(i);
                rangeMap.put(ValueRange.of(intermediateResult.get(0),intermediateResult.get(intermediateResult.size()-1)),intermediateResult.size());
                intermediateResult.clear();
                intermediateResult.add(currentVal);
            }
        }
        rangeMap.putIfAbsent(ValueRange.of(intermediateResult.get(0),
                intermediateResult.get(intermediateResult.size()-1)),
        intermediateResult.size());
        return rangeMap;
    }



}
