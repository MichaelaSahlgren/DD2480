import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class LogicalConnectorMatrix {
    private String[][] lcm;
    //this class represents the logical connector matrix used when creating the preliminary unlocking matrix 
    public LogicalConnectorMatrix(){
        lcm = new String[][]{
            {"ANDD", "ANDD", "ORR", "ANDD", "NOTUSED"},
            {"ANDD", "ANDD", "ORR", "ORR", "NOTUSED"},
            {"ORR", "ORR", "ANDD", "ANDD", "NOTUSED"},
            {"ANDD", "ORR", "ANDD", "ANDD", "NOTUSED"},
            {"NOTUSED", "NOTUSED", "NOTUSED", "NOTUSED", "NOTUSED"}
        };
    }
    public LogicalConnectorMatrix(String file){
        lcm = new String[15][15];
        try {
            Scanner myReader = new Scanner(new File(file));
            int i = 0;
            while (myReader.hasNextLine()) {
                String[] row = myReader.nextLine().split("\\s+");
                for(int j = 0; j < row.length; j++){
                    lcm[i][j] = row[j].trim();
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean calculate(int i, int j, boolean a, boolean b){
        if(getRule(i, j).equals("ANDD")){
            return a && b;
        }
        else if(getRule(i, j).equals("ORR")){
            return a || b;
        }
        else {
            return true;
        }
    }

    private String getRule(int i, int j){
        return (i < 5 && j < 5)? lcm[i][j]: "NOTUSED";
    }
}
