public class Geometry{
  public static double calculateDistance(double x1, double y1, double x2, double y2){
    //issue#1
    //Solved using pythagoras theorem
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static double calculateAngle(double vertexX, double vertexY,double x1, double y1, double x2, double y2){
    //solved using Law of cosines where side a and b come from the vertex point
    double sideA= Geometry.calculateDistance(vertexX,vertexY,x1,y1);
    double sideB= Geometry.calculateDistance(vertexX,vertexY,x2,y2);
    double sideC= Geometry.calculateDistance(x1,y1,x2,y2);

    return Math.acos(((sideA * sideA) + (sideB * sideB) - (sideC * sideC))/(2.0 * sideA * sideB));
  }

  public static boolean checkIfPointsFitInCircle(double x1, double y1, double x2, double y2, double x3, double y3, double radius) {
      double dist12;
      double dist13;
      double dist23;
      double semiParameter;
      double triangleArea;
      double circumradius;

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
      return !(circumradius > radius);
  }

  public static double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3){
    double vertexX = x2;
    double vertexY = y2;
    double dist1 = calculateDistance(x1, y1, vertexX, vertexY);
    double dist2 = calculateDistance(x3, y3, vertexX, vertexY);
    double angle = calculateAngle(vertexX,vertexY, x1, y1, x3, y3);
    double triangleArea = (dist1*dist2*Math.sin(angle))/2;
    return triangleArea;
  }
}
