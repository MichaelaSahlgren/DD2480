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
    @DisplayName("LIC #9 tests")
    void licNineTestValid() {
      parameters.EPSILON = 1;
      int C_PTS = 1; //one to minimize data needed to test
      int D_PTS = 1;
      double[] xCoords = {};
      double[] yCoords = {};

      //true TEST cases
        //Testing angle < pi - epsilon
        xCoords = new double[]{1, 1, 0, 2, 0};
        yCoords = new double[]{1, 1, 0, 2, 1};
        boolean trueTest = controller.calculateRule9(xCoords, yCoords, C_PTS, D_PTS, parameters.EPSILON);
        assertTrue(trueTest);

        //Testing angle > pi + epsilon
        xCoords = new double[]{-4, 1, 0, 2, 0};
        yCoords = new double[]{-4, 1, 0, 2, 1};
        trueTest = controller.calculateRule9(xCoords, yCoords, C_PTS, D_PTS, parameters.EPSILON);
        assertTrue(trueTest);

        //false TEST cases
        //Testing angle does not meet condition
        xCoords = new double[]{0, 1, 0, 2, 0};
        yCoords = new double[]{4, 1, 0, 2, -4};
        boolean falseTest = controller.calculateRule9(xCoords, yCoords, C_PTS, D_PTS, parameters.EPSILON);
        assertTrue(falseTest);

        //Testing too few points
        xCoords = new double[]{1, -1};
        yCoords = new double[]{1, -2};
        falseTest = controller.calculateRule9(xCoords, yCoords, C_PTS, D_PTS, parameters.EPSILON);
        assertFalse(falseTest);

        //Testing if the points coincide, they should be ignored --> return false
        xCoords = new double[]{1, 1, -3};
        yCoords = new double[]{1, 1, -3};
        falseTest = controller.calculateRule9(xCoords, yCoords, C_PTS, D_PTS, parameters.EPSILON);
        assertFalse(falseTest);

    }

}
