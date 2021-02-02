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
        for(int j = 1; j < xCoordinates.length; j++){
            if(xCoordinates[j] - xCoordinates[j - 1] < 0){
                return true;
            }
        }
        return false;
    }

    public boolean calculateRule6(double[] xCoordinates, double[] yCoordinates, int N_PTS, double DIST) {
        //issue#8
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
