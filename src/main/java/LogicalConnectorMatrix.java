import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class LogicalConnectorMatrix {
    LcmValue[][] matrix;
    //this class represents the logical connector matrix used when creating the preliminary unlocking matrix 
    public LogicalConnectorMatrix() {
        //by default, every entry in the array is NOTUSED to start with
        matrix = new LcmValue[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matrix[i][j] = LcmValue.NOTUSED;
            }
        }
    }
    public LogicalConnectorMatrix(String file){
        matrix = new LcmValue[15][15];
        try {
            Scanner myReader = new Scanner(new File(file));
            int i = 0;
            while (myReader.hasNextLine()) {
                String[] row = myReader.nextLine().split("\\s+");
                for(int j = 0; j < row.length; j++){
                    String rule = row[j].trim();
                    if(rule.equals("ANDD")){
                        matrix[i][j] = LcmValue.ANDD;
                    }
                    else if (rule.equals("ORR")){
                        matrix[i][j] = LcmValue.ORR;
                    }
                    else {
                        matrix[i][j] = LcmValue.NOTUSED;
                    }
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
