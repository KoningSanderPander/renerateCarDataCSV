import classes.CarModel;
import io.IO;

import java.io.IOException;
import java.util.HashMap;

/**
 * The purpose of this project is to generate a .CSV file
 * which can be read directly inserted to the Car_Dealer database.
 *
 * @author Sander van den Oetelaar, SrvdO
 * @version 1.0.0
 */

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();

        try {
            modelHashMap = IO.loadModels();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(modelHashMap);


    }

}
