import com.mifmif.common.regex.Generex;
import com.mifmif.common.regex.util.Iterator;
import io.IO;
import vehicleParts.CarModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * The purpose of this project is to generate a .CSV file
 * which can be read directly inserted to the Car_Dealer database.
 *
 * @author Sander van den Oetelaar, SrvdO
 * @version 1.0.0
 */

public class Main {

    public static void main(String[] args) {
        final String PATH_VEHICLE_MODELS_CSV = "src/main/resources/vehicleModelData.csv";

        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();


        try {
            modelHashMap = IO.loadModels(IO.loadFilePath(PATH_VEHICLE_MODELS_CSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(modelHashMap);

/*
        Generex generex = new Generex("[0-3]([a-c]|[e-g]{1,2})");

        // Generate random String
        String randomStr = generex.random();
        System.out.println(randomStr);// a random value from the previous String list

        // generate the second String in lexicographical order that match the given Regex.
        String secondString = generex.getMatchedString(2);
        System.out.println(secondString);// it print '0b'

        // Generate all String that matches the given Regex.
        List<String> matchedStrs = generex.getAllMatchedStrings();

        // Using Generex iterator
        Iterator iterator = generex.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
*/
    }

}
