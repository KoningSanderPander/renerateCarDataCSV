package io;

import data.CarModel;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class IO {

    public static BufferedReader loadFilePath (String path){
        // File lookup
        Reader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.printf("models CSV file could not be found\n" +
                    "%s\n" +
                    "Try using the code found on line 24 and 25 of the IO class to find the filepath.\n\n", e);

            File file = new File(".");
            for(String fileNames : Objects.requireNonNull(file.list())) System.out.println(fileNames);
            System.exit(1);
        }

        // Return Buffered file reader
        return new BufferedReader(fileReader);

    }


    public static HashMap<Integer, CarModel> loadModels(BufferedReader modelsCsvReader) throws IOException {
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

        return modelHashMap;
    }
}
