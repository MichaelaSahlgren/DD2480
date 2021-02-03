import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PreliminaryUnlockingMatrixTests {
    PreliminaryUnlockingMatrix pum;

    @BeforeEach
    public void setUp() throws Exception {
        pum = new PreliminaryUnlockingMatrix();
    }
    
    @Test
    @DisplayName("Calculate Distance valid")
    void createPumValidTest() {
        boolean[][] matrix = new boolean[15][15];
        boolean[] cmv = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        LogicalConnectorMatrix lcm = new LogicalConnectorMatrix();
        
        pum.createPum(cmv, lcm, matrix);
        boolean[][] correctMatrix = {
            {false, false, true, false, true, true, true, true, true, true, true, true, true, true, true},
            {false, true, true, true, true,  true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {false, true, true, true, true,  true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
        };
        assertTrue(Arrays.deepEquals(matrix, correctMatrix));
    }
}
