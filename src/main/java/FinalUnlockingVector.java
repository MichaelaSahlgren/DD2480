public class FinalUnlockingVector {
    public void createFuv(
            boolean[][] pum,
            boolean[] puv,
            boolean[] fuv //createFuv populates the values in fuv
    ) {
        //issue#20
        //go through puv, if true, check all booleans of secondArray, should keep fuv[i] to true if all inner booleans are true
        for (int i = 0; i < puv.length; i++) {
            fuv[i] = true;
            if (puv[i]) {
                for (boolean p : pum[i]) {
                    if (!p) {
                        fuv[i] = false;
                        break;
                    }
                }
            }


        }
    }
}
