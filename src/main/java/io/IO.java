package io;

import vehicleParts.CarModel;

import java.io.*;
import java.util.HashMap;

public class IO {
    private static final String PATH_TO_MODELS_CSV = "src/io/vehicleModelData.csv";

    public static HashMap<Integer, CarModel> loadModels() throws IOException {
        HashMap<Integer, CarModel> modelHashMap = new HashMap<>();

        // File lookup
        Reader fileReader = null;
        try {
            fileReader = new FileReader(PATH_TO_MODELS_CSV);
        } catch (FileNotFoundException e) {
            System.out.printf("models CSV file could not be found\n%s", e);
            System.exit(0);
        }

        // Read models file per line
        BufferedReader modelsCsvReader = new BufferedReader(fileReader);

        // Enter data in to CarModel objects
        String line;
        while ((line = modelsCsvReader.readLine()) != null) {
            String[] data = line.split(",");

//            System.out.println(Arrays.toString(data));


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

        return modelHashMap;

    }
}
