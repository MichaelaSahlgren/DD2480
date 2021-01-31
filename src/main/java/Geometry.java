public class Geometry{
  public double calculateDistance(double x1, double y1, double x2, double y2){
    //issue#1
    //Solved using pythagoras theorem
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }
}
