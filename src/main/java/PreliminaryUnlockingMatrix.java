public class PreliminaryUnlockingMatrix {
    public void createPum(
        boolean[] cmv,
        LogicalConnectorMatrix lcm,
        boolean[][] pum //createPum populates the values in pum
    ) {
        //issue#19
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                pum[i][j] = lcm.calculate(i, j, cmv[i], cmv[j]);
            }
        }
    }
}
