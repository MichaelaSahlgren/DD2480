import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GeometryTests {

    @Test
    @DisplayName("Calculate Distance valid")
    void calculateDistanceValid() {

        double[] xCoordinates = {15, 35};
        double[] yCoordinates = {20, 5};
        double expectedLength = 25;
        double distanceBetweenOneAndTwo = Geometry.calculateDistance(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1]);
        assertEquals(expectedLength, distanceBetweenOneAndTwo, "Distance calculated correct");
    }

    @Test
    @DisplayName("Calculate Distance invalid")
    void calculateDistanceInvalid() {

        double[] xCoordinates = {3, 4};
        double[] yCoordinates = {0, 0};
        double notExpectedLength = 5;
        double distanceBetweenOneAndTwo = Geometry.calculateDistance(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1]);
        assertNotEquals(notExpectedLength, distanceBetweenOneAndTwo, "Distance calculated correct");

    }

    @Test
    @DisplayName("Calculate angle valid")
    void calculateAngleValid() {

        double[] xCoordinates = {0, 0, 20};
        double[] yCoordinates = {0, 20, 20};
        double ExpectedAngle = Math.PI / 2;
        double angle = Geometry.calculateAngle(xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2], xCoordinates[0], yCoordinates[0]);
        //vertex stays the same but other points flip places
        double flippedAngle = Geometry.calculateAngle(xCoordinates[1], yCoordinates[1], xCoordinates[0], yCoordinates[0], xCoordinates[2], yCoordinates[2]);

        assertAll("Valid calculateAngle test",
                () -> assertEquals(ExpectedAngle, angle, 0.0000000001f),
                () -> assertEquals(ExpectedAngle, flippedAngle, 0.0000000001f)
        );

    }


    @Test
    @DisplayName("Calculate angle invalid")
    void calculateAngleInvalid() {

        double[] xCoordinates = {0, 0, 20};
        double[] yCoordinates = {0, 20, 20};
        double notExpectedAngle = Math.PI / 2;
        //an invalid vertex is used and therefore should not give same results as valid test
        double angle = Geometry.calculateAngle(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

        assertNotEquals(notExpectedAngle, angle, 0.0000000001f);

    }

    @DisplayName("Calculate points inside circle true")
    void calculatePointsFitInsideCircleTrue() {
        double[] xCoordinates = {0, 0, 1};
        double[] yCoordinates = {0, 1, 1};
        //these three points should fit inside a circle of radius 3
        boolean fits = Geometry.checkIfPointsFitInCircle(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2], 3);

        assertTrue(fits);
    }

    @Test
    @DisplayName("Calculate points inside circle false")
    void calculatePointsFitInsideCircleFalse() {
        double[] xCoordinates = {0, 0, 1};
        double[] yCoordinates = {0, 1, 1};
        //these three points should not fit inside a circle of radius 0.5
        boolean fits = Geometry.checkIfPointsFitInCircle(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2], 0.5);

        assertFalse(fits);
    }

    @Test
    @DisplayName("Calculate triangle area normal case")
    void calculateTriangleAreaNormal() {

        double[] xCoordinates = {0, 1, 0};
        double[] yCoordinates = {0, 1, 1};
        double expectedArea = 0.5;
        double area = Geometry.calculateTriangleArea(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

        assertEquals(expectedArea, area, 0.0000000001f);

    }

    @Test
    @DisplayName("Calculate triangle area edge case")
    void calculateTriangleAreaEdgeCase() {

        double[] xCoordinates = {0, 1, 0};
        double[] yCoordinates = {0, 1, 0};
        double expectedArea = 0;
        double area = Geometry.calculateTriangleArea(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]);

        assertEquals(expectedArea, area, 0.0000000001f);

    }

}
