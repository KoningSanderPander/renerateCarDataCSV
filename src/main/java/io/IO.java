package io;

import data.CarModel;
import data.Option;
import data.engines.Engine;
import data.engines.types.CombustionEngine;
import data.engines.types.ElectricEngine;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class IO {

    /**
     * @param FILE Csv file
     * @return BufferedReader
     */
    public ReusableBufferedReader loadFilePath(final FileReader FILE) {
        // File lookup

        return new ReusableBufferedReader(new char[1024 * 1024])
                .setSource(FILE);
    }


    public static String[] loadManufacturers(ReusableBufferedReader manufacturersCsvReader) throws IOException {
        LinkedList<String> manufacturers = new LinkedList<>();

        String line;
        while ((line = manufacturersCsvReader.readLine()) != null) {
            manufacturers.add(line);
        }

        manufacturersCsvReader.close();
        System.out.printf("%24s: %5d%n", "Loaded manufacturers", manufacturers.size());
        return manufacturers.toArray(String[]::new);
    }

    /**
     * @param fuelTypesCsvReader BufferedReader
     * @return String array containing all fuel types
     * @throws IOException Input Output Error
     */
    public static String[] loadFuelTypes(ReusableBufferedReader fuelTypesCsvReader) throws IOException {
        LinkedList<String> fuelTypes = new LinkedList<>();

        String line;
        while ((line = fuelTypesCsvReader.readLine()) != null) {
            fuelTypes.add(line);
        }

        fuelTypesCsvReader.close();
        System.out.printf("%24s: %5d%n", "Loaded fuel types", fuelTypes.size());
        return fuelTypes.toArray(String[]::new);
    }

    /**
     * @param combustionEngineCsvReader BufferedFileReader
     * @param fuelTypes                 fuel types like petrol and diesel
     * @return Map of a sorted map based on fuel type and horsepower
     * @throws IOException Input Output Error
     */
    public static HashMap<String, TreeMap<Integer, Engine>> loadCombustionEngines(
            ReusableBufferedReader combustionEngineCsvReader,
            ReusableBufferedReader electricEngineCsvReader,
            String[] fuelTypes) throws IOException {

        HashMap<String, TreeMap<Integer, Engine>> engineMap = new HashMap<>();
        for (String fuelType : fuelTypes) {
            engineMap.putIfAbsent(fuelType, new TreeMap<>());
        }

        String line;
        while ((line = combustionEngineCsvReader.readLine()) != null) {
            String[] data = line.split(",");

            engineMap.get(data[3]).putIfAbsent(Integer.parseInt(data[1]),
                    new CombustionEngine(
                            Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            data[3],
                            Integer.parseInt(data[4]),
                            data[5],
                            Integer.parseInt(data[6]),
                            Integer.parseInt(data[7]),
                            Integer.parseInt(data[8]),
                            Double.parseDouble(data[9])
                    )
            );
        }

        while ((line = electricEngineCsvReader.readLine()) != null) {
            String[] data = line.split(",");

            engineMap.get(data[3]).putIfAbsent(Integer.parseInt(data[1]),
                    new ElectricEngine(
                            Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            data[3],
                            Integer.parseInt(data[4]),
                            Integer.parseInt(data[5]),
                            Integer.parseInt(data[6]),
                            Double.parseDouble(data[7])
                    )
            );
        }

        combustionEngineCsvReader.close();
        System.out.printf("%24s: %5d%n", "Loaded engines", engineMap.values().stream().mapToInt(TreeMap::size).sum());
        return engineMap;
    }

    public static Option[] loadOptions(ReusableBufferedReader optionsCsvReader) throws IOException {
        LinkedList<Option> options = new LinkedList<>();

        String line;
        while ((line = optionsCsvReader.readLine()) != null) {
            String[] data = line.split(",");
            options.add(
                    new Option(
                            data[0],
                            Integer.parseInt(data[1])
                    )
            );
        }

        optionsCsvReader.close();
        System.out.printf("%24s: %5d%n", "Loaded options", options.size());
        return options.toArray(Option[]::new);
    }

    /**
     * @param modelsCsvReader BufferedFileReader
     * @return Map with all car models
     * @throws IOException Input Output Error
     */
    public static HashMap<Integer, CarModel> loadModels(ReusableBufferedReader modelsCsvReader) throws IOException {
        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();

        // Enter data in to CarModel objects
        String line;
        while ((line = modelsCsvReader.readLine()) != null) {
            String[] data = line.split(",");

            modelHashMap.putIfAbsent(
                    Integer.valueOf(data[0]),
                    new CarModel(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            Integer.parseInt(data[6]),
                            Integer.parseInt(data[7]),
                            data[8]
                    ));
        }
        modelsCsvReader.close();

        System.out.printf("%24s: %5d%n", "Loaded models", modelHashMap.size());
        return modelHashMap;
    }
}
