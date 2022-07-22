package Test_Driven_Ranges;

import java.time.temporal.ValueRange;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ChargingCurrentStatistics {



    public Map<ValueRange,Integer> getCurrentRangeMapBasedOnInput(Integer[] inputSample) {
        List<Integer> sortedCurrentRange = sortCurrentRange(inputSample);
        return storeReadingsBasedOnRange(sortedCurrentRange);
    }

    public String getFinalResult(Integer[] integers) {

        Map<ValueRange,Integer> currentRangeMap = getCurrentRangeMapBasedOnInput(integers);
        String result = currentRangeMap.entrySet().stream()
                .map(rangeMap-> {
                    String val = rangeMap.getKey().getMinimum() + "-" + rangeMap.getKey().getMaximum()
                            + "," + rangeMap.getValue();
                    return val;
                } ).collect(Collectors.joining(" "));
        return  result;
    }

    private List<Integer> sortCurrentRange(Integer[] integers) {
        List<Integer> integerList = Arrays.asList(integers);
        Collections.sort(integerList);
        return integerList;
    }

    private Map<ValueRange,Integer> storeReadingsBasedOnRange(List<Integer> integerList) {
        int initialCounter = 1;
        Map<ValueRange, Integer> readingRangeMap = new HashMap<>();
        List<Integer> intermediateResult = new ArrayList<>();
        int intermediateCounter = 1, previousData = integerList.get(0);
        intermediateResult.add(integerList.get(0));
        while (initialCounter < integerList.size()) {
            if ((previousData == integerList.get(initialCounter)) || (previousData + 1 == integerList.get(initialCounter))) {
                    intermediateResult.add(integerList.get(initialCounter));
                    previousData = integerList.get(initialCounter);
                    intermediateCounter++;
            } else {
                    if (!intermediateResult.isEmpty()) {
                        readingRangeMap.put(ValueRange.of(intermediateResult.get(0), intermediateResult.get(intermediateResult.size() - 1)), intermediateCounter);
                        intermediateResult.clear();
                        intermediateCounter = 1;
                    }
                    previousData = integerList.get(initialCounter);
                    intermediateResult.add(integerList.get(initialCounter));
            }
            initialCounter++;
        }
        if (!intermediateResult.isEmpty()) {
            readingRangeMap.put(ValueRange.of(intermediateResult.get(0), intermediateResult.get(intermediateResult.size() - 1)), intermediateCounter);
        }
        return readingRangeMap;
    }

}
