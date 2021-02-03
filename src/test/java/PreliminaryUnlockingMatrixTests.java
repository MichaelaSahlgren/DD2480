import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
