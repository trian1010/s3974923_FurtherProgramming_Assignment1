/**
 * @author <Nguyen Tran Tri An - s3974923>
 */

import java.util.Scanner;

public class DataInput {
    private static DataInput input;
    private Scanner sc;
    private DataInput() {
        sc = new Scanner(System.in);
    }

    public static DataInput getDataInput() {
        if (input == null) {
            input = new DataInput();
        }
        return input;
    }

    public Scanner getScanner() {
        return sc;
    }
}
