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
    @DisplayName("LIC #1 tests")
    void licOneTestValid() {

      // True test
      parameters.RADIUS1 = 2.3;

      double[] xCoords = {2, 5, 0};
      double[] yCoords = {4, 1, 1};

      Boolean trueTest = controller.calculateRule1(xCoords,yCoords,parameters.RADIUS1);
      assertTrue(trueTest);

      // False test
      parameters.RADIUS1 = 2.85;

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
