import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
}
