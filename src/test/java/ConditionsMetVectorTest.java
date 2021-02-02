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
}
