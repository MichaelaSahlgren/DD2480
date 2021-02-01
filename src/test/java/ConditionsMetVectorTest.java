import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionsMetVectorTest {
    ConditionsMetVector controller;
    Parameters parameters;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new ConditionsMetVector();
        parameters = new Parameters();
    }

    @Test
    @DisplayName("LIC #0 is correct for no coordinates")
    void licZeroReturnsFalseWhenCoordinatesIsEmpty(){
        parameters.LENGTH1 = 0;
        double[] xCoordinates = {};
        double[] yCoordinates = {};

        assertFalse(controller.calculateRule0(xCoordinates, yCoordinates, parameters.LENGTH1));
    }
    @Test
    @DisplayName("LIC #0 is correct for a single coordinate")
    void licZeroReturnsFalseWhenThereOnlyIsOneCoordinate(){
        parameters.LENGTH1 = 0;
        double[] xCoordinates = {1};
        double[] yCoordinates = {1};

        assertFalse(controller.calculateRule0(xCoordinates, yCoordinates, parameters.LENGTH1));
    }
    @Test
    @DisplayName("LIC #0 is correct for two coordinates")
    void licZeroReturnsTrueWhenInputConcistsOfTwoCoordinates(){
        parameters.LENGTH1 = 1;
        double[] xCoordinates = {1, 10};
        double[] yCoordinates = {1, 10};

        assertTrue(controller.calculateRule0(xCoordinates, yCoordinates, parameters.LENGTH1));
    }
    @Test
    @DisplayName("LIC #0 is correct when conditions are not met")
    void licZeroReturnsFalseWhenNoConsecutiveCoordinatesAreFurtherThanLengthApart(){
        parameters.LENGTH1 = 100;
        double[] xCoordinates = {1, 3, 3, 5, -3};
        double[] yCoordinates = {1, 3, 0, 10, 12};

        assertFalse(controller.calculateRule0(xCoordinates, yCoordinates, parameters.LENGTH1));
    }
    @Test
    @DisplayName("LIC #0 is correct when conditions are met")
    void licZeroReturnsTrueWhenTwoConsecutiveCoordinatesAreFurtherThanLengthApart(){
        parameters.LENGTH1 = 10;
        double[] xCoordinates = {1, 3, 3, 5, -3};
        double[] yCoordinates = {1, 3, 0, 10, 12};

        assertTrue(controller.calculateRule0(xCoordinates, yCoordinates, parameters.LENGTH1));
    }

    @Test
    @DisplayName("LIC #2 tests")
    void licTwoTestValid() {

        parameters.EPSILON = 1;

        //true Test cases
        //check if the LIC2 condition is met
        double[] xCoords = {1, -1, 0, -1, 1};
        double[] yCoords = {2, -2, 0, -2, 2};

        Boolean trueTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
        assertTrue(trueTest);

        //if the points coincide, they should be ignored but not break the function
        xCoords = new double[]{1, 1, -1, 0, -1, 1};
        yCoords = new double[]{2, 2, -2, 0, -2, 2};
        trueTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
        assertTrue(trueTest);

        //false TEST cases
        //if there are less than 3 points
        xCoords = new double[]{1, -1};
        yCoords = new double[]{1, -2};
        Boolean falseTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
        assertFalse(falseTest);

        //if the points coincide, they should be ignored
        xCoords = new double[]{1, 1, -3};
        yCoords = new double[]{1, 1, -2.5};
        falseTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
        assertFalse(falseTest);

        //check if the LIC2 condition is not met
        xCoords = new double[]{0, 3, 6};
        yCoords = new double[]{0, -1, 0};
        falseTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
        assertFalse(falseTest);

    }

    @Test
    @DisplayName("LIC #4 tests")
    void licFourTestValid() {
        parameters.Q_PTS = 2;
        parameters.QUADS = 2;

        //true Test cases
        //returns true when there are Q_PTS consecutive data points in QUADS quadrants at beginning of array
        double[] xCoords = {1, -1, 1, -1, 1};
        double[] yCoords = {1, 1, 0, -2, 2};

        assertTrue(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //returns true when there are Q_PTS consecutive data points in QUADS quadrants in middle of array
        xCoords = new double[]{1, 2, 0, -1, 1};
        yCoords = new double[]{1, 1, 1, -2, 2};

        assertTrue(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //returns true when there are Q_PTS consecutive data points in QUADS quadrants at the end of array
        xCoords = new double[]{1, 2, 0, -1};
        yCoords = new double[]{1, 1, 1, -2};

        assertTrue(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //false TEST cases
        //returns false when xCoords.length < Q_PTS
        xCoords = new double[] {1};
        yCoords = new double[] {1};

        assertFalse(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //returns false when y.Coords.length < Q_PTS
        xCoords = new double[] {1,1,1};
        yCoords = new double[] {};

        assertFalse(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //returns false when QUADS is out of range
        xCoords = new double[] {1,2,3};
        yCoords = new double[] {1,3,4};
        int tmpQUADS = 5;

        assertFalse(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, tmpQUADS));

        //returns false when no subset meets the conditions of LIC4
        xCoords = new double[] {1,2,3};
        yCoords = new double[] {1,3,4};

        assertFalse(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, parameters.QUADS));

        //returns false when the data points lies in too few QUADS
        xCoords = new double[] {1,-1,0};
        yCoords = new double[] {1,1,0};
        tmpQUADS = 3;

        assertFalse(controller.calculateRule4(xCoords, yCoords, parameters.Q_PTS, tmpQUADS));

    }

    @Test
    @DisplayName("LIC #5 returns true when there exist two consecutive data points where the second xCoordinate is less than the first one")
    void licFiveReturnsTrueWhenAllXCoordinatesAreNotIncreasing(){
        double[] xCoordinates = {1, 3, 3, 5, -3, 12};
        double[] yCoordinates = {1, 3, 0, 10, 12, 12};

        assertTrue(controller.calculateRule5(xCoordinates, yCoordinates));
    }
    @Test
    @DisplayName("LIC #5 returns false when there does not exist two consecutive data points where the second xCoordinate is less than the first one")
    void licFiveReturnsFalseWhenAllXCoordinatesAreIncreasing(){
        double[] xCoordinates = {1, 3, 3, 5, 7, 10};
        double[] yCoordinates = {1, 3, 0, 10, 12, 12};

        assertFalse(controller.calculateRule5(xCoordinates, yCoordinates));
    }
}
