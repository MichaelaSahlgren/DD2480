import java.lang.Math;
import java.util.Arrays;


public class ConditionsMetVector {
    public boolean calculateRule0(double[] xCoordinates, double[] yCoordinates, double LENGTH1) {
        for(int i = 0; i < xCoordinates.length - 1; i++){
            if(Geometry.calculateDistance(
                xCoordinates[i],
                yCoordinates[i],
                xCoordinates[i + 1],
                yCoordinates[i + 1]
            ) > LENGTH1){
                return true;
            }
        }
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
      if(xCoordinates.length < 3){
        return false;
      }
      for (int i = 0; i < (xCoordinates.length - 2); i++) {
        double x1 = xCoordinates[i];
        double y1 = yCoordinates[i];
        double x2 = xCoordinates[i+1];
        double y2 = yCoordinates[i+1];
        double x3 = xCoordinates[i+2];
        double y3 = yCoordinates[i+2];
        double triangleArea = Geometry.calculateTriangleArea(x1, y1, x2, y2, x3, y3);
        if(triangleArea>AREA1){
          return true;
        }
      }
        return false;
    }

    public boolean calculateRule4(double[] xCoordinates, double[] yCoordinates, int Q_PTS, int QUADS) {
      //issue#6

      //check special cases
      if(xCoordinates.length < Q_PTS || yCoordinates.length < Q_PTS) return false;
      if(QUADS < 1 || QUADS > 3) return false;

      //iterate over all data points
      for(int i = 0; i<(xCoordinates.length)-Q_PTS+1; i++){

        //check all subset of length Q_PTS
        boolean[] visitedQuads = new boolean[4];//visited QUADS
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
        for (boolean visitedQuad : visitedQuads) {
            if (visitedQuad) count++;
        }
        if(count == QUADS) return true;
      }
      return false;
    }

    public boolean calculateRule5(double[] xCoordinates, double[] yCoordinates) {
        for(int j = 1; j < xCoordinates.length; j++){
            if(xCoordinates[j] - xCoordinates[j - 1] < 0){
                return true;
            }
        }
        return false;
    }

    public boolean calculateRule6(double[] xCoordinates, double[] yCoordinates, int N_PTS, double DIST) {
        //issue#8
        //Condition is not met if NUMPOINTS < 3
        if (xCoordinates.length < 3) return false;

        double distance;
        double pointX;
        double pointY;
        double x1;
        double x2;
        double y1;
        double y2;
        int i = -1;

        //Loop over all consecutive sets of N_PST points
        while(i < xCoordinates.length-N_PTS) {
          i++;
          x1 = xCoordinates[i];
          x2 = xCoordinates[i+N_PTS-1];
          y1 = yCoordinates[i];
          y2 = yCoordinates[i+N_PTS-1];

          //Check if distance from (pointX, pointY) to the line between
          //(firstX, firstY) and (lastX, lastY) is greater than DIST
          for (int j = 1; j < N_PTS; j++) {
            pointX = xCoordinates[i+j];
            pointY = yCoordinates[i+j];

            // If the first and last point coinside the distance is to that point
            if (x1 == x2 && y1 == y2) distance = Geometry.calculateDistance(x1, y1, pointX, pointY);

            // If the first and last point are different the distance is to the line between them
            else distance = Math.abs((x2-x1)*(y1-pointY)-(x1-pointX)*(y2-y1))/Geometry.calculateDistance(x1, y1, x2, y2);

            //Condition met if the point lays a distance greater than DIST from the line/(point)
            if (distance > DIST) return true;
          }
        }
        return false;
    }

    public boolean calculateRule7(double[] xCoordinates, double[] yCoordinates, int K_PTS, double LENGTH1) {
        //issue#9
        if (xCoordinates.length < 3 || yCoordinates.length < 3) {
            return false;
        }
        //point 1
        double p1x = 0;
        double p1y = 0;

        //point 2
        double p2x = 0;
        double p2y = 0;

        for (int i = 0; i < xCoordinates.length - K_PTS - 1; i++) {
            //point1
            p1x = xCoordinates[i];
            p1y = yCoordinates[i];
            //point2
            p2x = xCoordinates[i + (K_PTS + 1)];
            p2y = yCoordinates[i + (K_PTS + 1)];

            if (Geometry.calculateDistance(p1x, p1y, p2x, p2y) > LENGTH1) {
                return true;
            }
        }

        return false;
    }

    public boolean calculateRule8(double[] xCoordinates, double[] yCoordinates, int A_PTS, int B_PTS, double RADIUS1) {
        //issue#10
        return false;
    }

    public boolean calculateRule9(double[] xCoordinates, double[] yCoordinates, int C_PTS, int D_PTS, double EPSILON) {
        //issue#11

        //check special cases
        if(xCoordinates.length<5 || yCoordinates.length<5) return false;

        double p1x = 0;//point 1
        double p1y = 0;
        double p2x = 0;//point 2
        double p2y = 0;
        double p3x = 0;//point 3
        double p3y = 0;
        double angle = 0;
        int p2idx;
        int p3idx;

        for(int i = 0; i<xCoordinates.length-(C_PTS+D_PTS)-2; i++){
          p1x = xCoordinates[i];
          p1y = yCoordinates[i];

          p2idx = i + C_PTS + 1;
          p2x = xCoordinates[p2idx];
          p2y = yCoordinates[p2idx];

          p3idx = p2idx + D_PTS + 1;
          p3x = xCoordinates[p3idx];
          p3y = yCoordinates[p3idx];

          //if point 1 or 3 coincides with the vertex (p2) then skip!
          if (p1x == p2x && p1y == p2y || p3x == p2x && p3y == p2y)
              continue;

          angle = Geometry.calculateAngle(p2x,p2y,p1x,p1y,p3x,p3y);

          if (angle > Math.PI+EPSILON || angle < Math.PI-EPSILON) return true;
        }

        return false;
    }

    public boolean calculateRule10(double[] xCoordinates, double[] yCoordinates, int E_PTS, int F_PTS, double AREA1) {
        //issue#12
        return false;
    }

    public boolean calculateRule11(double[] xCoordinates, double[] yCoordinates, int G_PTS) {
        //issue#13

        // Input check
        if (xCoordinates.length < 3 || G_PTS > xCoordinates.length-2 || G_PTS < 1) return false;

        double x1;
        double x2;

        // Loop over xCoordinates to see if (x2-x1 < 0) holds for two points x1 and x2
        // separated by G_PTS consecutive points
        for (int i = 0; i < xCoordinates.length-G_PTS-1; i++) {
          x1 = xCoordinates[i];
          x2 = xCoordinates[i+G_PTS+1];

          // Condition met
          if (x2-x1 < 0) return true;
        }
        return false;
    }

    public boolean calculateRule12(double[] xCoordinates, double[] yCoordinates, int K_PTS, double LENGTH1, double LENGTH2) {
        //issue#14

        if (xCoordinates.length < 3 || yCoordinates.length < 3 || K_PTS < 0 || LENGTH2 <=0) {
            return false;
        }
        //point 1
        double p1x = 0;
        double p1y = 0;

        //point 2
        double p2x = 0;
        double p2y = 0;

        //conditions
        boolean firstCondition=false;
        boolean secondCondition=false;

        double distance=0;

        for (int i = 0; i < xCoordinates.length - K_PTS - 1; i++) {
            //point1
            p1x = xCoordinates[i];
            p1y = yCoordinates[i];
            //point2
            p2x = xCoordinates[i + (K_PTS + 1)];
            p2y = yCoordinates[i + (K_PTS + 1)];
            
            distance = Geometry.calculateDistance(p1x, p1y, p2x, p2y);
            if (distance > LENGTH1 ) {
                firstCondition=true;
            }
            if (distance < LENGTH2 ) {
                secondCondition=true;
            }
        }


        return firstCondition && secondCondition;
    }

    public boolean calculateRule13(double[] xCoordinates, double[] yCoordinates, int A_PTS, int B_PTS, double RADIUS1, double RADIUS2) {
        //issue#15
        return false;
    }

    public boolean calculateRule14(double[] xCoordinates, double[] yCoordinates, int E_PTS, int F_PTS, double AREA1, double AREA2) {
        //issue#16

        //check special cases
        if(xCoordinates.length != yCoordinates.length) return false;
        if(xCoordinates.length<5) return false;

        double p1x = 0;//point 1
        double p1y = 0;
        double p2x = 0;//point 2
        double p2y = 0;
        double p3x = 0;//point 3
        double p3y = 0;
        double area = 0;
        boolean[] conditions = new boolean[2];
        int p2idx;
        int p3idx;

        for(int i = 0; i<xCoordinates.length-(E_PTS+F_PTS)-2; i++){
            p1x = xCoordinates[i];
            p1y = yCoordinates[i];

//            p2x = xCoordinates[i+E_PTS+1];
//            p2y = yCoordinates[i+E_PTS+1];
//
//            p3x = xCoordinates[i+E_PTS+F_PTS+2];
//            p3y = yCoordinates[i+E_PTS+F_PTS+2];

            p2idx = i + E_PTS + 1;
            p2x = xCoordinates[p2idx];
            p2y = yCoordinates[p2idx];

            p3idx = p2idx + F_PTS + 1;
            p3x = xCoordinates[p3idx];
            p3y = yCoordinates[p3idx];

            area = Geometry.calculateTriangleArea(p1x,p1y,p2x,p2y,p3x,p3y);//Geometry.calculateTriangleArea();

            if(area > AREA1){
                conditions[0] = true;
            }
            if(area > 0 && area < AREA2){
                conditions[1] = true;
            }
        }

        return conditions[0] && conditions[1];
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
