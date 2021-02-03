public class LogicalConnectorMatrix {
    LcmValue[][] matrix;
    public LogicalConnectorMatrix() {
        //by default, every entry in the array is NOTUSED to start with
        matrix = new LcmValue[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matrix[i][j] = LcmValue.NOTUSED;
            }
        }
    }
}
