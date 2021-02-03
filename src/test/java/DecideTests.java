import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DecideTests {
	@Test
	@DisplayName("PUV all false")
	void puvAllFalse() {
    // If every element in the PUV is false, then every element in the FUV
    // should end up being true, so that the launch decision should be
    // true regardless of all other inputs.
		Decide decider = new Decide();
    double[] xCoordinates = {1, 1, 1};
    double[] yCoordinates = {2, 2, 2};
    Parameters parameters = new Parameters();
    parameters.LENGTH1 = 0;
    parameters.RADIUS1 = 0;
    parameters.EPSILON = 0;
    parameters.AREA1 = 0;
    parameters.Q_PTS = 0;
    parameters.QUADS = 0;
    parameters.DIST = 0;
    parameters.N_PTS = 0;
    parameters.K_PTS = 0;
    parameters.A_PTS = 0;
    parameters.B_PTS = 0;
    parameters.C_PTS = 0;
    parameters.D_PTS = 0;
    parameters.E_PTS = 0;
    parameters.F_PTS = 0;
    parameters.G_PTS = 0;
    parameters.LENGTH2 = 0;
    parameters.RADIUS2 = 0;
    parameters.AREA2 = 0;
    LogicalConnectorMatrix lcm = new LogicalConnectorMatrix();
	//we could set a particular cell of the lcm if we wanted to, although
	//it won't matter for this particular test:
	lcm.matrix[5][10] = LcmValue.ANDD;
    // Set every element of the input PUV to false:
    boolean[] puv = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    boolean[] cmv = new boolean[15];
    boolean[][] pum = new boolean[15][15];
    boolean[] fuv = new boolean[15];
    boolean launch = decider.decide(xCoordinates, yCoordinates, parameters, lcm, puv, cmv, pum, fuv);
		assertTrue(launch);
	}

    @Test
    @DisplayName("FUV validInput")
    void evaluateFUVTest() {
	    Decide decider = new Decide();
	    boolean[]fuv= {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        //should return true as all fuv are true
	    assertTrue(decider.evaluateFUV(fuv));

	    //should return false as atleast one i is not true
        fuv[4]=false;
        assertFalse(decider.evaluateFUV(fuv));

    }


}
