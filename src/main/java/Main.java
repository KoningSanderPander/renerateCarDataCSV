import data.CarModel;
import data.CarOption;
import data.Vehicle;
import data.engines.Engine;
import io.IO;

import java.io.FileReader;
import java.util.*;

/**
 * The purpose of this project is to generate a .CSV file
 * which can be read directly inserted to the Car_Dealer database.
 *
 * @author Sander van den Oetelaar, SrvdO
 * @version 1.0.0
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int nrOfVehicles;

        // File paths
        IO io = new IO();
        final String PATH_FUEL_TYPES_CSV = "src/main/resources/fueltypes.csv";
        final String PATH_ENGINES_CSV = "src/main/resources/engines.csv";
        final String PATH_MANUFACTURERS_CSV = "src/main/resources/manufacturers.csv";
        final String PATH_OPTIONS_CSV = "src/main/resources/options.csv";
        final String PATH_VEHICLE_MODELS_CSV = "src/main/resources/vehicleModelData.csv";

        // Data sets
        String[] manufacturers = null;
        String[] fuelTypes = null;
        HashMap<String, TreeMap<Integer, Engine>> engineMap = new HashMap<>();
        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();
        CarOption[] carOptions = null;

        // Load manufacturers, fuel types, engines, car models, and options
        try {
            manufacturers = IO.loadManufacturers(io.loadFilePath(new FileReader(PATH_MANUFACTURERS_CSV)));
            fuelTypes = IO.loadFuelTypes(io.loadFilePath(new FileReader(PATH_FUEL_TYPES_CSV)));
            engineMap = IO.loadEngines(io.loadFilePath(new FileReader(PATH_ENGINES_CSV)), fuelTypes);
            carOptions = IO.loadOptions(io.loadFilePath(new FileReader(PATH_OPTIONS_CSV)));
            modelHashMap = IO.loadModels(io.loadFilePath(new FileReader(PATH_VEHICLE_MODELS_CSV)));
        } catch (Exception e) {
            e.printStackTrace();

            System.out.printf("models CSV file could not be found\n" +
                    "%s\n" +
                    "Try using the commented code below to find the filepath.\n\n", e);
//            Debugging code
//            File file = new File(".");
//            for (String fileNames : Objects.requireNonNull(file.list())) System.out.println(fileNames);

            System.exit(1);
        }

        HashMap<CarModel, Option[]> optionsHashMap = new HashMap<>();

        for (CarModel model: modelHashMap.values()) {

            int modelprice = model.getMsrp();

            for (Option option : options) {
                option.recalculateOptionPrice(modelprice);
            }

            optionsHashMap.putIfAbsent(model, options);

        }


        System.out.printf("%nNr of vehicles: ");
        nrOfVehicles = scanner.nextInt();

        System.out.printf("%nGenerating Vehicles... ");
        LinkedList<Vehicle> vehicles = new LinkedList<>();
        for (int i = 0; i < nrOfVehicles; i++) {
            vehicles.add(new Vehicle(modelHashMap.get(random.nextInt(modelHashMap.size())+1)));
        }
        scanner.close();

        for (Vehicle vehicle : vehicles) {
            vehicle.setEngine(engineMap);
            vehicle.setOptions(carOptions);
        }

        System.out.println("Done");





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
