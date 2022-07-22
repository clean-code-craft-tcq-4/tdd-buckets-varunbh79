package Test_Driven_Ranges;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChargingCurrentAnalyzerTest {

    private static ChargingCurrentAnalyzer chargingCurrentAnalyzer;

    @BeforeClass
    public static void initializeTestSetup() {
        chargingCurrentAnalyzer = new ChargingCurrentAnalyzer();
    }

    @Test
    public void testReadingsForMinimumRange() {

        Integer[] inputSample = {3,4};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "3-4,2";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForRepeatedSeries() {

        Integer[] inputSample = {2,2,4,3};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "2-4,4";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForDisjointSeries() {

        Integer[] inputSample = {3,3,4,5,8,9,10};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "3-5,4 8-10,3";
        assertEquals(actualResult,expectedResult);
    }


}
