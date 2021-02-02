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

  public static double calculateLargeAngle(double vertexX, double vertexY,double x1, double y1, double x2, double y2){
    //solved using Law of cosines where side a and b come from the vertex point
    double sideA= Geometry.calculateDistance(vertexX,vertexY,x1,y1);
    double sideB= Geometry.calculateDistance(vertexX,vertexY,x2,y2);
    double sideC= Geometry.calculateDistance(x1,y1,x2,y2);

    double top =(sideA * sideA) + (sideB * sideB) - (sideC * sideC);
    if (top < 0){
      return (2*Math.PI) - Math.acos(((sideA * sideA) + (sideB * sideB) - (sideC * sideC))/(2.0 * sideA * sideB));
    }
    return Math.acos(((sideA * sideA) + (sideB * sideB) - (sideC * sideC))/(2.0 * sideA * sideB));
  }

}
