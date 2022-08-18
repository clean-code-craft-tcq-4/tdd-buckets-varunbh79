package Test_Driven_Ranges;


import java.util.Arrays;

public class AnalogToDigitalConverter {

    public String getConversionReadingsInRange(Integer[] input,
                                               Integer sensorMinReading, Integer sensorMaxReading, Integer minAmp, Integer maxAmp)
    {
        if (!validateEmptyOrNullInput(input)) {
            return null;
        }
        Integer[] readingsInAmps = computeConversionBasedOnInputBits(input,sensorMinReading,sensorMaxReading,minAmp,maxAmp);
        ChargingCurrentAnalyzer chargingCurrentAnalyzer = new ChargingCurrentAnalyzer();
        return chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(readingsInAmps);
    }

    public Integer[] computeConversionBasedOnInputBits(Integer[] input,
                                                       Integer sensorMinReading, Integer sensorMaxReading, Integer minAmp, Integer maxAmp) {


        for (Integer reading : input) {
            if (validateOutofRangeInputs(reading, sensorMinReading, sensorMaxReading)) {
                throw new RuntimeException("Out of Range Input Found !!!");
            }
        }
        return transformReadingsToAmp(input, sensorMinReading, sensorMaxReading, minAmp, maxAmp);
    }

    private Integer[] transformReadingsToAmp(Integer[] input, Integer sensorMinReading, Integer sensorMaxReading, Integer minAmp, Integer maxAmp) {
        return Arrays.stream(input)
                .map(digit -> convertReadingsToAmps(digit, sensorMinReading, sensorMaxReading, minAmp, maxAmp))
                .toArray(Integer[]::new);
    }

    public boolean validateOutofRangeInputs(Integer value, Integer minReading, Integer maxReading) {

        return (value < minReading || value > maxReading);

    }

    private Integer convertReadingsToAmps(Integer input, Integer minReading, Integer maxReading, Integer minAmp,
                                          Integer maxAmp) {
        float resVal = (float) (input - minReading) * (maxAmp - minAmp) / (maxReading - minReading) + minAmp;
        return Math.round(resVal);
    }

    private boolean validateEmptyOrNullInput(Integer[] integers) {

        return integers != null && integers.length != 0;
    }


}
