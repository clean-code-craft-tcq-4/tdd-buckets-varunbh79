package Test_Driven_Ranges;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChargingCurrentAnalyzerTest {

    private static ChargingCurrentAnalyzer chargingCurrentAnalyzer;

    @BeforeClass
    public static void initializeTestSetup() {
        chargingCurrentAnalyzer = new ChargingCurrentAnalyzer();
    }


    @Test
    public void testReadingsForEmptyAndNullInputs() {

        Integer[] inputSample1 = {};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample1);
        assertNull(actualResult);

        Integer[] inputSample2 = null;
        String actualResult2 = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample2);
        assertNull(actualResult2);
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
    public void testReadingsForConsecutiveSeries() {

        Integer[] inputSample = {3,3,4,5,6,7,7,8,9,10,10};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "3-10,11";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForUnsortedSeries() {

        Integer[] inputSample = {2,1,1,4,5,3,3,7,6,6,8};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "1-8,11";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForDisjointSeries() {

        Integer[] inputSample = {3,3,4,5,8,9,10,12,13};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "3-5,4 8-10,3 12-13,2";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForNegativeNumberSeries() {

        Integer[] inputSample = {-2,0,-1,-3,-4};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "-4-0,5";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForSegregatedMixedNumberSeries() {

        Integer[] inputSample = {-2,0,-1,-3,-4,4,5,3,2,7,6};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "-4-0,5 2-7,6";
        assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testReadingsForDiscreteMixedNumberSeries() {

        Integer[] inputSample = {-2,0,-1,-3,-4,2,4,5,3,-11,-9,-10,24,21,22,23};
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsByRange(inputSample);
        String expectedResult = "-11--9,3 -4-0,5 2-5,4 21-24,4";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testReadingsForTwelveBitInput() {

        Integer[] inputSample = {250,502,603,4095};
        String expectedResult = "1-2,3 11-11,1";
        String actualResult = chargingCurrentAnalyzer.getChargingCurrentStatisticsThroughA2DConverter(inputSample);
        assertEquals(actualResult,expectedResult);

    }


}
