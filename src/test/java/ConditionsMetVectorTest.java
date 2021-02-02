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

}
