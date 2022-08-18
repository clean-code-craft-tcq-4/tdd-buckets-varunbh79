package Test_Driven_Ranges;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AnalogToDigitalConverterTest {

    private static AnalogToDigitalConverter digitalConverter;

    @BeforeClass
    public static void initializeTestSetup() {
        digitalConverter = new AnalogToDigitalConverter();
    }

    @Test
    public void testOutOfRangeValue_1() throws Exception{

        boolean result = digitalConverter.validateOutofRangeInputs(5000,0 , 4094);
        assertTrue(result);
    }

    @Test
    public void testOutOfRangeValue_2() throws Exception{

        boolean result = digitalConverter.validateOutofRangeInputs(-9000,0 , 4094);
        assertTrue(result);
    }


    @Test
    public void test10BitSensorReadings() {

        Integer[] readings = {120, 1090, 140, 130};
        int maxSensorReading = 4094, minSensorReading = 0;
        int minAmps = 0, maxAmps = 10;
        Integer[] currentRangeInAmps = digitalConverter.
                computeConversionBasedOnInputBits(readings,minSensorReading,maxSensorReading,
                        minAmps,maxAmps);
        Integer[] expectedResult = {0,3,0,0};
        assertNotNull(currentRangeInAmps);
        assertArrayEquals(currentRangeInAmps,expectedResult);

    }

    @Test
    public void test12BitSensorReadings() {

        Integer[] readings = {120, 990, 140, 130};
        int maxSensorReading = 1023, minSensorReading = 0;
        int minAmps = -15, maxAmps = 10;
        Integer[] currentRangeInAmps = digitalConverter.
                computeConversionBasedOnInputBits(readings,minSensorReading,maxSensorReading,
                        minAmps,maxAmps);
        System.out.println(Arrays.toString(currentRangeInAmps));
        Integer[] expectedResult = {-12, 9, -12, -12};
        assertNotNull(currentRangeInAmps);
        assertArrayEquals(currentRangeInAmps,expectedResult);

    }

    @Test(expected = RuntimeException.class)
    public void testExceptionForOutOfRange12BitSensorReadings() {

        Integer[] readings = {-120, 990, 140, 130};
        int maxSensorReading = 1023, minSensorReading = 0;
        int minAmps = -15, maxAmps = 15;
        digitalConverter.
                computeConversionBasedOnInputBits(readings,minSensorReading,maxSensorReading,
                        minAmps,maxAmps);


    }

    @Test(expected = RuntimeException.class)
    public void testExceptionForOutOfRange10BitSensorReadings() {

        Integer[] readings = {120, -1090, 1400, 4130};
        int maxSensorReading = 4094, minSensorReading = 0;
        int minAmps = 0, maxAmps = 10;
         digitalConverter.
                computeConversionBasedOnInputBits(readings,minSensorReading,maxSensorReading,
                        minAmps,maxAmps);


    }

    @Test
    public void test10BitSensorReadingsRange() {

        Integer[] readings = {120, 1090, 1400, 4030};
        int maxSensorReading = 4094, minSensorReading = 0;
        int minAmps = 0, maxAmps = 10;
        String actualRange = digitalConverter.getConversionReadingsInRange(readings,minSensorReading,maxSensorReading,
                        minAmps,maxAmps);
        String expectedRange = "0-0,1 3-3,2 10-10,1";
        assertEquals(actualRange,expectedRange);

    }

    @Test(expected = RuntimeException.class)
    public void test10BitOutOfRangeSensorReadingsRange() {

        Integer[] readings = {120, 1090,4095,201};
        int maxSensorReading = 4094, minSensorReading = 0;
        int minAmps = 0, maxAmps = 10;
        digitalConverter.getConversionReadingsInRange(readings,minSensorReading,maxSensorReading,
                minAmps,maxAmps);


    }

    @Test
    public void test12BitSensorReadingsRange() {

        Integer[] readings = {120, 1009, 90, 798};
        int maxSensorReading = 1023, minSensorReading = 0;
        int minAmps = -15, maxAmps = 15;
        String actualRange = digitalConverter.getConversionReadingsInRange(readings,minSensorReading,maxSensorReading,
                minAmps,maxAmps);
        String expectedRange = "-12--11,2 8-8,1 15-15,1";
        assertEquals(actualRange,expectedRange);


    }

    @Test(expected = RuntimeException.class)
    public void test12BitOutOfRangeSensorReadingsRange() {

        Integer[] readings = {120, 1024, 90, 798};
        int maxSensorReading = 1023, minSensorReading = 0;
        int minAmps = -15, maxAmps = 15;
         digitalConverter.getConversionReadingsInRange(readings,minSensorReading,maxSensorReading,
                minAmps,maxAmps);

    }


}
