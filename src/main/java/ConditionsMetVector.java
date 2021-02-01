public class ConditionsMetVector {
  public boolean calculateRule0(double[] xCoordinates, double[] yCoordinates, double LENGTH1) {
    //issue#2
    return false;
  }

  public boolean calculateRule1(double[] xCoordinates, double[] yCoordinates, double RADIUS1) {
    //issue#3
    return false;
  }

  public boolean calculateRule2(double[] xCoordinates, double[] yCoordinates, double EPSILON) {
    //issue#4
    return false;
  }

  public boolean calculateRule3(double[] xCoordinates, double[] yCoordinates, double AREA1) {
    //issue#5
    return false;
  }

// . There exists at least one set of Q PTS consecutive data points that lie in more than QUADS
// quadrants.
// (2 ≤ Q PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)
  public boolean calculateRule4(double[] xCoordinates, double[] yCoordinates, int Q_PTS) {
    //issue#6

    //check special cases
    if(xCoordinates.length < Q_PTS && Q_PTS>0) return false;

    //iterate over all datapoints
    for(int i = 0; i<(xCoordinates.length-1)-Q_PTS; i++){

      //check all subset of length Q_PTS
      boolean visitedQuads = new boolean[4];//visited QUADS
      for(int j = i; j<i+Q_PTS; j++){
        //check which quadrant the point is in
        if(xCoordinates[j] >= 0){
          if(yCoordinates[j] >= 0){
            visitedQuads[0] = true;
          }else if(xCoordinates[j]==0 && yCoordinates[j]<0){ //the point lies on the negative y-axis
            visitedQuads[2] = true;
          }else{
            visitedQuads[3] = true;
          }
        }else{
          if(yCoordinates[j] >= 0){
            visitedQuads[1] = true;
          }else{
            visitedQuads[2] = true;
          }
        }
      }

      //check if 1 ≤ QUADS ≤ 3 is visited, if true subset found - terminate
      int count = 0;
      for(int k = 0; k<visitedQuads.length;k++){
        if(visitedQuads[k]==true) count++;
      }
      if(count>=1 && count<=3) return true;
    }
    return false;
  }

  public boolean calculateRule5(double[] xCoordinates, double[] yCoordinates) {
    //issue#7
    return false;
  }

  public boolean calculateRule6(double[] xCoordinates, double[] yCoordinates, int N_PTS, double DIST) {
    //issue#8
    return false;
  }

  public boolean calculateRule7(double[] xCoordinates, double[] yCoordinates, int K_PTS, double LENGTH1) {
    //issue#9
    return false;
  }

  public boolean calculateRule8(double[] xCoordinates, double[] yCoordinates, int A_PTS, int B_PTS, double RADIUS1) {
    //issue#10
    return false;
  }

  public boolean calculateRule9(double[] xCoordinates, double[] yCoordinates, int C_PTS, int D_PTS, double EPSILON) {
    //issue#11
    return false;
  }

  public boolean calculateRule10(double[] xCoordinates, double[] yCoordinates, int E_PTS, int F_PTS, double AREA1) {
    //issue#12
    return false;
  }

  public boolean calculateRule11(double[] xCoordinates, double[] yCoordinates, int G_PTS) {
    //issue#13
    return false;
  }

  public boolean calculateRule12(double[] xCoordinates, double[] yCoordinates, int K_PTS, double LENGTH1, double LENGTH2) {
    //issue#14
    return false;
  }

  public boolean calculateRule13(double[] xCoordinates, double[] yCoordinates, int A_PTS, int B_PTS, double RADIUS1, double RADIUS2) {
    //issue#15
    return false;
  }

  public boolean calculateRule14(double[] xCoordinates, double[] yCoordinates, int E_PTS, int F_PTS, double AREA1, double AREA2) {
    //issue#16
    return false;
  }

  public void createCmv(
    double[] xCoordinates,
    double[] yCoordinates,
    Parameters parameters,
    boolean[] cmv //the createCmv-method sets elements in this array
    ){
      //#issue17
    }

}
