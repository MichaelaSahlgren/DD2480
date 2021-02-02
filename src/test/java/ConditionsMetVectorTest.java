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

}
