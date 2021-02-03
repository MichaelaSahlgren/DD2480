import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class PreliminaryUnlockingMatrixTests {
    @Test
    @DisplayName("LCM all NOTUSED")
    void lcmAllNOTUSED() {
        //using the default lcm - all NOTUSED
        LogicalConnectorMatrix lcm = new LogicalConnectorMatrix();
        PreliminaryUnlockingMatrix pumGenerator = new PreliminaryUnlockingMatrix();
        //set all cmv values to false
        boolean[] cmv = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        boolean[][] pum = new boolean[15][15];
        pumGenerator.createPum(cmv, lcm, pum);
        //check that one of the pum cells is true
        assertTrue(pum[3][4]);
    }

    @Test
    @DisplayName("createPum is correct when LogicalConnectorMatrix is created from file")
    void createPumValidTest() {
        PreliminaryUnlockingMatrix pumGenerator = new PreliminaryUnlockingMatrix();
        boolean[][] matrix = new boolean[15][15];
        boolean[] cmv = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        LogicalConnectorMatrix lcm = new LogicalConnectorMatrix("./inputs/lcm1.txt");
        
        pumGenerator.createPum(cmv, lcm, matrix);
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