public class PreliminaryUnlockingMatrix {
    public void createPum(
    boolean[] cmv,
    LogicalConnectorMatrix lcm,
    boolean[][] pum //createPum populates the values in pum
    ) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (lcm.matrix[i][j] == LcmValue.ANDD) {
                    pum[i][j] = cmv[i] && cmv[j];
                } else if (lcm.matrix[i][j] == LcmValue.ORR) {
                    pum[i][j] = cmv[i] || cmv[j];
                } else {
                    //it must be NOTUSED
                    pum[i][j] = true;
                }
            }
        }
    }
}
