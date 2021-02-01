import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GeometryTests {

    @BeforeEach
    public void setUp() throws Exception {

    }

	@Test
	@DisplayName("Calculate Distance valid")
	void calculateDistanceValid() {

        double[] xCoordinates = {15, 35};
        double[] yCoordinates = {20, 5};
        double expectedLength = 25;
        double distanceBetweenOneAndTwo= Geometry.calculateDistance(xCoordinates[0],yCoordinates[0],xCoordinates[1],yCoordinates[1]);
        assertEquals(expectedLength,distanceBetweenOneAndTwo ,"Distance calculated correct");
    }

    @Test
    @DisplayName("Calculate Distance invalid")
    void calculateDistanceInvalid() {

        double[] xCoordinates = {3, 4};
        double[] yCoordinates = {0, 0};
        double notExpectedLength = 5;
        double distanceBetweenOneAndTwo= Geometry.calculateDistance(xCoordinates[0],yCoordinates[0],xCoordinates[1],yCoordinates[1]);
        assertNotEquals(notExpectedLength,distanceBetweenOneAndTwo ,"Distance calculated correct");

    }

    @Test
    @DisplayName("Calculate angle valid")
    void calculateAngleValid() {

        double[] xCoordinates = {0, 0, 20};
        double[] yCoordinates = {0, 20,20};
        double ExpectedAngle = Math.PI/2;
        double angle= Geometry.calculateAngle(xCoordinates[1],yCoordinates[1],xCoordinates[2],yCoordinates[2],xCoordinates[0],yCoordinates[0]);
        //vertex stays the same but other points flip places
        double flippedAngle= Geometry.calculateAngle(xCoordinates[1],yCoordinates[1],xCoordinates[0],yCoordinates[0],xCoordinates[2],yCoordinates[2]);

        assertAll("Should return address of Oracle's headquarter",
                () -> assertEquals(ExpectedAngle,angle ,0.0000000001f),
                () -> assertEquals(ExpectedAngle,flippedAngle ,0.0000000001f)
        );


    }


    @Test
    @DisplayName("Calculate angle invalid")
    void calculateAngleInvalid() {

        double[] xCoordinates = {0, 0, 20};
        double[] yCoordinates = {0, 20,20};
        double notExpectedAngle = Math.PI/2;
        //an invalid vertex is used and therefore should not give same results as valid test
        double angle= Geometry.calculateAngle(xCoordinates[0],yCoordinates[0],xCoordinates[1],yCoordinates[1],xCoordinates[2],yCoordinates[2]);

        assertNotEquals(notExpectedAngle,angle ,0.0000000001f);

    }

    @Test
    @DisplayName("Calculate angle returns correct area for simple triangle")
    void calculateTriangleAreaValid1() {
        double[] xCoordinates = {0, 10, 20};
        double[] yCoordinates = {0, 20, 0};
        double expectedArea = 200;
        assertEquals(
            Geometry.calculateTriangleArea(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]), 
            expectedArea
        );
    }
    @Test
    @DisplayName("Calculate angle returns correct area for twisted triangle")
    void calculateTriangleAreaValid2() {
        double[] xCoordinates = {-4, 10, -17};
        double[] yCoordinates = {-5, 15, 5};
        double expectedArea = 200;
        assertEquals(
            Geometry.calculateTriangleArea(xCoordinates[0], yCoordinates[0], xCoordinates[1], yCoordinates[1], xCoordinates[2], yCoordinates[2]), 
            expectedArea
        );
    }

    
}
