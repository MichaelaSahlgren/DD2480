import java.lang.Math;

public class ConditionsMetVector {
    public boolean calculateRule0(double[] xCoordinates, double[] yCoordinates, double LENGTH1) {
        //issue#2
        return false;
    }

    public boolean calculateRule1(double[] xCoordinates, double[] yCoordinates, double RADIUS1) {
      //issue#3
      double x1;
      double y1;
      double x2;
      double y2;
      double x3;
      double y3;
      double dist12;
      double dist13;
      double dist23;
      double semiParameter;
      double triangleArea;
      double circumradius;

      if (RADIUS1 < 0 || xCoordinates.length < 0 || yCoordinates.length < 0) return false;

      // Loop over all consecutive sets of 3 points to see if any of the sets has
      // at least one point that cannot be contained within RADIUS1
      for (int i = 0; i < xCoordinates.length-2; i++) {
        x1 = xCoordinates[i];
        y1 = yCoordinates[i];
        x2 = xCoordinates[i+1];
        y2 = yCoordinates[i+1];
        x3 = xCoordinates[i+2];
        y3 = yCoordinates[i+2];

        dist12 = Geometry.calculateDistance(x1, y1, x2, y2);
        dist13 = Geometry.calculateDistance(x1, y1, x3, y3);
        dist23 = Geometry.calculateDistance(x2, y2, x3, y3);

        // Semiparameter to calculate the area of the triangle
        semiParameter = (dist12 + dist13 + dist23)/2;

        // Heron's Formula to find area of triangle
        triangleArea = Math.sqrt(semiParameter*(semiParameter-dist12)*(semiParameter-dist13)*(semiParameter-dist23));

        // Radius of the circumcircle
        circumradius = (dist12*dist13*dist23)/(4*triangleArea);

        // if circumradius is larger than radius at least one point cannot be contaied
        // within the circle
        if (circumradius > RADIUS1) return true;
      }
      return false;
    }

    public boolean calculateRule2(double[] xCoordinates, double[] yCoordinates, double EPSILON) {
        //issue#4

        //point 1
        double p1x = 0;
        double p1y = 0;

        //point 2
        double p2x = 0;
        double p2y = 0;

        //point 3
        double p3x = 0;
        double p3y = 0;

        double angle = 0;

        if (xCoordinates.length < 3 || yCoordinates.length < 3) {
            return false;
        }

        for (int i = 0; i < xCoordinates.length - 2; i++) {
            //point 1
             p1x = xCoordinates[i];
             p1y = yCoordinates[i];

            //point 2, always the vertex as per requirements
             p2x = xCoordinates[i + 1];
             p2y = yCoordinates[i + 1];

            //Point 3
             p3x = xCoordinates[i + 2];
             p3y = yCoordinates[i + 2];

            //if point 1 or 3 coincides with the vertex (p2) then skip!
            if (p1x == p2x && p1y == p2y || p3x == p2x && p3y == p2y)
                continue;

            //amgle using (p2x,p2y) as the vertex
            angle = Geometry.calculateAngle(p2x, p2y, p1x, p1y, p3x, p3y);

            if (angle < (Math.PI - EPSILON) || angle > (Math.PI + EPSILON)) {
                return true;
            }

        }

        return false;
    }

    public boolean calculateRule3(double[] xCoordinates, double[] yCoordinates, double AREA1) {
        //issue#5
        return false;
    }

    public boolean calculateRule4(double[] xCoordinates, double[] yCoordinates, int Q_PTS) {
        //issue#6
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
    ) {
        //#issue17
    }

}
