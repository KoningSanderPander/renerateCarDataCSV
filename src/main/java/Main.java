import data.engines.Engine;
import io.IO;
import data.CarModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * The purpose of this project is to generate a .CSV file
 * which can be read directly inserted to the Car_Dealer database.
 *
 * @author Sander van den Oetelaar, SrvdO
 * @version 1.0.0
 */

public class Main {

    public static void main(String[] args) {
        // File paths
        IO io = new IO();
        final String PATH_FUEL_TYPES_CSV = "src/main/resources/fueltypes.csv";
        final String PATH_COMBUSTION_CSV = "src/main/resources/combustion.csv";
        final String PATH_ELECTRIC_CSV = "src/main/resources/electric.csv";
        final String PATH_MANUFACTURERS_CSV = "src/main/resources/manufacturers.csv";
        final String PATH_OPTIONS_CSV = "src/main/resources/options.csv";
        final String PATH_VEHICLE_MODELS_CSV = "src/main/resources/vehicleModelData.csv";

        // Data sets
        String[] fuelTypes = null;
        HashMap<String, TreeMap<Integer, Engine>> engineMap = new HashMap<>();


        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();

        // Load fuel types, engines, car models
        try {
            fuelTypes = IO.loadFuelTypes(io.loadFilePath(new FileReader(PATH_FUEL_TYPES_CSV)));
            engineMap = IO.loadCombustionEngines(io.loadFilePath(new FileReader(PATH_COMBUSTION_CSV)), fuelTypes);
            modelHashMap = IO.loadModels(io.loadFilePath(new FileReader(PATH_VEHICLE_MODELS_CSV)));
        } catch (Exception e) {
            e.printStackTrace();

            System.out.printf("models CSV file could not be found\n" +
                    "%s\n" +
                    "Try using the commented code below to find the filepath.\n\n", e);
//            Debugging code
//            File file = new File(".");
//            for (String fileNames : Objects.requireNonNull(file.list())) System.out.println(fileNames);

            System.exit(69);
        }

        System.out.println(Arrays.toString(fuelTypes));
        System.out.println(engineMap.get(fuelTypes[0]));
        System.out.println(modelHashMap.toString());










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
