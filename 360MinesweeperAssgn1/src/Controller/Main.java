package Controller;

import Model.FieldData;

public class Main extends FieldData {
    private static FieldData data = new FieldData();
    public static void main(final String[] args) { // main method
        // accepts command line inputs and runs the program
        if (args.length > 0) {
            try {
                data.setFile(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getField();

    }
}
