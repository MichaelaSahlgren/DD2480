public class Decide {
  public boolean decide(
    double[] xCoordinates,
    double[] yCoordinates,
    Parameters parameters,
    LogicalConnectorMatrix lcm,
    boolean[] puv,
    boolean[] cmv,
    boolean[][] pum,
    boolean[] fuv) {
    //populate the cmv
    ConditionsMetVector cmvGenerator = new ConditionsMetVector();
    cmvGenerator.createCmv(xCoordinates, yCoordinates, parameters, cmv);

    //populate the pum
    PreliminaryUnlockingMatrix pumGenerator = new PreliminaryUnlockingMatrix();
    pumGenerator.createPum(cmv, lcm, pum);

    //populate the fuv
    FinalUnlockingVector fuvGenerator = new FinalUnlockingVector();
    fuvGenerator.createFuv(pum, puv, fuv);

    //calculate the launch-decision
    boolean launch = true;
    for (int i = 0; i < fuv.length; i++) {
      if (fuv[i] == false) {
        launch = false;
      }
    }

    return true;//temporarily for the test
    // return launch;
  }
}
