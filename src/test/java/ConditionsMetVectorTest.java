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
    @DisplayName("LIC #1 test invalid inputs")
    void licOneInvalidInputTest() {

        // RADIUS1 < 0 invalid
        parameters.RADIUS1 = -1;
        double[] xCoords = {3, 1, 2};
        double[] yCoords = {1, 2, 3};

        boolean falseTest = controller.calculateRule1(xCoords,yCoords,parameters.RADIUS1);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #1 valid tests")
    void licOneTestValid() {

        // True test, points have same x-vlaue i.e no angle
        parameters.RADIUS1 = 2;

        double[] xCoords = {1, 1, 1};
        double[] yCoords = {0, 4, 5};

        Boolean trueTest = controller.calculateRule1(xCoords,yCoords,parameters.RADIUS1);
        assertTrue(trueTest);

        // True test, at least one point cannot be contaied within the circle
        parameters.RADIUS1 = 1.5;

        xCoords = new double[]{-2, 2, 3};
        yCoords = new double[]{1, 3, 2};

        trueTest = controller.calculateRule1(xCoords,yCoords,parameters.RADIUS1);
        assertTrue(trueTest);

        // False test, all points contained within the circle
        parameters.RADIUS1 = 3;

        Boolean falseTest = controller.calculateRule1(xCoords,yCoords,parameters.RADIUS1);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #2 tests")
    void licTwoTestValid() {

        parameters.EPSILON = 1;

        //true Test cases
        //check if the LIC2 condition is met
        double[] xCoords = {1, -1, 0, -1, 1};
        double[] yCoords = {2, -2, 0, -2, 2};

        boolean trueTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
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
        boolean falseTest = controller.calculateRule2(xCoords, yCoords, parameters.EPSILON);
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
    @DisplayName("LIC #3 testing if enough points were inputed")
    void licThreeEnoughPoints(){
        parameters.AREA1 = 0;
        double[] xCoordinates = {0, 0};
        double[] yCoordinates = {1, 1};

        assertFalse(controller.calculateRule3(xCoordinates, yCoordinates, parameters.AREA1));
    }

    @Test
    @DisplayName("LIC #3 testing with AREA1 of zero")
    void licThreeAreaOfZero(){
        parameters.AREA1 = 0;
        double[] xCoordinates = {0, 1, 1};
        double[] yCoordinates = {0, 1, 0};

        assertTrue(controller.calculateRule3(xCoordinates, yCoordinates, parameters.AREA1));
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

    @Test
    @DisplayName("LIC #6 valid when N_PTS = NUMPOINTS")
    void licSixValidWhenN_PTSEqualsNUMPOINTS() {

        // True test, all points futher than DIST from the line
        parameters.N_PTS = 6;
        parameters.DIST = 1;

        double[] xCoords = {-2, -2, -2, -4, 0, 2};
        double[] yCoords = {-4, -2, 0, 1, 2, 0};

        Boolean trueTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertTrue(trueTest);

        // True test, one point futher than DIST from the line
        parameters.DIST = 4;

        trueTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertTrue(trueTest);

        // False test, no points futher than DIST from the line
        parameters.DIST = 5;

        Boolean falseTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #6 invalid when N_PTS > NUMPOINTS")
    void licSixValidWhenN_PTSLargerThanNUMPOINTS() {
        // Invalid input N_PST > NUMPOINTS
        parameters.N_PTS = 7;
        parameters.DIST = 1;

        double[] xCoords = {-2, -2, -2, -4, 0, 2};
        double[] yCoords = {-4, -2, 0, 1, 2, 0};

        Boolean falseTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #6 valid when N_PTS < NUMPOINTS")
    void licSixValidWhenN_PTSLessThanNUMPOINTS() {
        // True test, at least one point futher than DIST from the line
        parameters.N_PTS = 3;
        parameters.DIST = 1;

        double[] xCoords = {-2, -2, -2, -4, 0, 2};
        double[] yCoords = {-4, -2, 0, 1, 2, 0};

        Boolean trueTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertTrue(trueTest);

        // False test, no point further than DIST from the line
        parameters.N_PTS = 3;
        parameters.DIST = 10;

        xCoords = new double[]{-4, -3, 0, -2, -2};
        yCoords = new double[]{-1, 0, 2, -2, -4};

        Boolean falseTest = controller.calculateRule6(xCoords, yCoords, parameters.N_PTS, parameters.DIST);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #7 Valid test")
    void licSevenTestValid() {

        parameters.LENGTH1=3;
        parameters.K_PTS=2;

        double[] xCoords = {1, -1, 0, 5, 1};
        double[] yCoords = {2, -2, 0, 8, 2};

        //should return true as there is a valid LIC7 condition match
        assertTrue(controller.calculateRule7(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1));

        parameters.K_PTS=1;
        xCoords = new double[]{1, 1, 5};
        yCoords = new double[]{1, 1, 8};
        //should return true as there is a valid LIC7 condition match
        assertTrue(controller.calculateRule7(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1));

        //should return false as there are no sets of points with K-points in between them with length >3
        xCoords = new double[]{1, 1, -1};
        yCoords = new double[]{1, 1, -1};

        assertFalse(controller.calculateRule7(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1));

    }

    @Test
    @DisplayName("LIC #7 InvalidInputs")
    void licSevenTestInvalidInputs() {

        parameters.LENGTH1=3;
        parameters.K_PTS=1;

        double[] xCoords = {1, -1};
        double[] yCoords = {2, -2};
        //should return false as the input is invalid.. NUMPOINTS is less than 3
        assertFalse(controller.calculateRule7(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1));

        parameters.LENGTH1=1;
        parameters.K_PTS=3;
        xCoords = new double[]{1, 1,5, -1};
        yCoords = new double[]{1, 1,6,-1};
        //should return false as K_PTS> NUMPOINTS-2
        assertFalse(controller.calculateRule7(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1));

    }

    @Test
    @DisplayName("LIC #9 tests")
    void licNineTestValid() {
        parameters.EPSILON = 1;
        parameters.C_PTS = 1; //one to minimize data needed to test
        parameters.D_PTS = 1;
        double[] xCoords = {};
        double[] yCoords = {};

        //true TEST cases
        //returns false when the angle meets the condition angle < pi - epsilon in LIC9
        xCoords = new double[]{1, 1, 0, 2, 0};
        yCoords = new double[]{1, 1, 0, 2, 1};
        assertTrue(controller.calculateRule9(xCoords, yCoords, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON));

        //returns true when the angle meets the condition angle > pi + epsilon in LIC9
        parameters.EPSILON = 0;
        xCoords = new double[]{4, 1, 0, 2, -4};
        yCoords = new double[]{0, 1, 0, 2, -2};
        assertTrue(controller.calculateRule9(xCoords, yCoords, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON));

        //false TEST cases
        //returns false when the angle does not meet the conditions of LIC9
        parameters.EPSILON = 1;
        xCoords = new double[]{0, 1, 0, 2, 0};
        yCoords = new double[]{4, 1, 0, 2, -4};
        assertFalse(controller.calculateRule9(xCoords, yCoords, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON));

        //returns false when there are too few vertices
        xCoords = new double[]{1, -1};
        yCoords = new double[]{1, -2};
        assertFalse(controller.calculateRule9(xCoords, yCoords, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON));

        //returns false when any of the three vertices are the same
        xCoords = new double[]{1, 0, 1, 1, -1, 1};
        yCoords = new double[]{1, 0, 1, 1, -1, 1};
        assertFalse(controller.calculateRule9(xCoords, yCoords, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON));
    }
  
    @Test
    @DisplayName("LIC #10 valid tests")
    void licTenTestValid(){
        parameters.E_PTS = 2;
        parameters.F_PTS = 3;
        parameters.AREA1 = 8;
        double[] xCoordinates = {1, 3, 3, 4,  7,  10, 0, -2};
        double[] yCoordinates = {1, 3, 0, 10, 12, 12, 0, -2};
        assertTrue(controller.calculateRule10(xCoordinates, yCoordinates, parameters.E_PTS, parameters.F_PTS, parameters.AREA1));

        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 29;
        assertTrue(controller.calculateRule10(xCoordinates, yCoordinates, parameters.E_PTS, parameters.F_PTS, parameters.AREA1));
    }

    @Test
    @DisplayName("LIC #10 invalid tests")
    void licTenTestInvalid(){
        //Test not enough input
        parameters.E_PTS = 3;
        parameters.F_PTS = 1;
        parameters.AREA1 = 0;
        double[] xCoordinates = {1, 3, 3, 5,  7,  10};
        double[] yCoordinates = {1, 3, 0, 10, 12, 12};
        assertFalse(controller.calculateRule10(xCoordinates, yCoordinates, parameters.E_PTS, parameters.F_PTS, parameters.AREA1));

        //AREA1 to large
        parameters.E_PTS = 2;
        parameters.F_PTS = 1;
        parameters.AREA1 = 19;
        assertFalse(controller.calculateRule10(xCoordinates, yCoordinates, parameters.E_PTS, parameters.F_PTS, parameters.AREA1));
    }

    @Test
    @DisplayName("LIC #11 invalid inputs")
    void licElevenTestInvalidInputs() {

        // G_PTS < 1
        parameters.G_PTS = 0;
        double[] xCoords = {0,0,0};
        double[] yCoords = {0,0,0};

        boolean falseTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertFalse(falseTest);

        // G_PTS > NUMPOINTS-2
        parameters.G_PTS = 4;
        falseTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertFalse(falseTest);

        // NUMPOINTS < 3
        xCoords = new double[]{0,0};
        yCoords = new double[]{0,0};
        falseTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #11 valid tests")
    void licElevenValidTest() {

        parameters.G_PTS = 1;
        double[] yCoords = {0,0,0};

        // True test, x2-x1 < 0
        double[] xCoords = {3,2,1};
        boolean trueTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertTrue(trueTest);

        // False test, x2-x1 > 0
        xCoords = new double[]{1,2,3};
        boolean falseTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertFalse(falseTest);

        // False test, x2-x1 = 0
        xCoords = new double[]{1,1,1};
        falseTest = controller.calculateRule11(xCoords, yCoords, parameters.G_PTS);
        assertFalse(falseTest);
    }

    @Test
    @DisplayName("LIC #12 Valid test")
    void licTwelveTestValid() {

        parameters.LENGTH1=3;
        parameters.LENGTH2=7;
        parameters.K_PTS=2;

        double[] xCoords = {1, -1, 0, 5, 1};
        double[] yCoords = {2, -2, 0, 8, 2};

        //should return true as there is a valid LIC12 condition match
        assertTrue(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        parameters.K_PTS=1;
        parameters.LENGTH2=10;
        xCoords = new double[]{1, 1, 5};
        yCoords = new double[]{1, 1, 8};
        //should return true as there is a valid LIC12 condition match
        assertTrue(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        //should return false as there are no sets of points with K-points in between them with distance>Length1
        xCoords = new double[]{1, 1, -1};
        yCoords = new double[]{1, 1, -1};

        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        parameters.LENGTH2=0.5;
        //should return false as condition2 is never fullfilled
        xCoords = new double[]{1, -1, 0, 5, 1};
        yCoords = new double[]{2, -2, 0, 8, 2};

        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        parameters.LENGTH1=10;
        //should return false as condition1 is never fullfilled
        xCoords = new double[]{1, -1, 0, 5, 1};
        yCoords = new double[]{2, -2, 0, 8, 2};

        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));
    }

    @Test
    @DisplayName("LIC #12 InvalidInputs")
    void licTwelveTestInvalidInputs() {

        parameters.LENGTH1=3;
        parameters.LENGTH2=10;
        parameters.K_PTS=0;

        double[] xCoords = {1, -5};
        double[] yCoords = {2, -5};
        //should return false as the input is invalid.. NUMPOINTS is less than 3
        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        parameters.K_PTS=3;
        xCoords = new double[]{1, 1,5, -1};
        yCoords = new double[]{1, 1,6,-1};
        //should return false as K_PTS> NUMPOINTS-2
        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));

        parameters.LENGTH2=-2;
        xCoords = new double[]{1, -1, 0, 5, 1};
        yCoords = new double[]{2, -2, 0, 8, 2};
        //should return false as Length2 is negative
        assertFalse(controller.calculateRule12(xCoords,yCoords,parameters.K_PTS,parameters.LENGTH1,parameters.LENGTH2));
    }
  
    @Test
    @DisplayName("LIC #14 valid inputs")
    void licFourteenTestValidInputs() {
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 1;
        parameters.AREA2 = 5;

        //true Test cases
        //returns true when one triangle meets the conditions for LIC14
        double[] xCoords = {0, 1, 2, 5, 0};
        double[] yCoords = {0, 0, 0, 0, 2};

        assertTrue(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

        //returns true when more than one triangle meets the conditions for LIC14
        xCoords = new double[] {1, 0, 2, 5, 0, 0};
        yCoords = new double[] {0, 0, 0, 0, 2, 5};

        assertTrue(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

        //false TEST cases
        //returns false when the first condition for LIC14 isn't met
        xCoords = new double[] {0, 0, 1, 0, 0};
        yCoords = new double[] {0, 0, 0, 2, 1};

        assertFalse(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

        //returns false when the second condition for LIC14 isn't met
        xCoords = new double[] {1, 0, 5, 0, 1};
        yCoords = new double[] {1, 0, 1, 2, 5};

        assertFalse(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

    }
    @Test
    @DisplayName("LIC #14 invalid inputs")
    void licFourteenTestInvalidInputs() {
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.AREA1 = 1;
        parameters.AREA2 = 5;

        //false TEST cases
        //returns false when there aren't the same number of x-coordinates and y-coordinates
        double[] xCoords = {1,2};
        double[] yCoords = {1};

        assertFalse(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

        //returns false when xCoords.length < 5 (or yCoords.length < 5)
        xCoords = new double[] {1};
        yCoords = new double[] {1};

        assertFalse(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));

        //returns false when area = 0
        xCoords = new double[] {0, 0, 0, 0, 0};
        yCoords = new double[] {0, 0, 0, 0, 0};

        assertFalse(controller.calculateRule14(xCoords, yCoords, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2));
    }
}
