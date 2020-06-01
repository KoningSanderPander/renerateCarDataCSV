package io;

import data.CarModel;
import data.engines.Engine;
import data.engines.types.CombustionEngine;

import java.io.IOException;
import java.io.Reader;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class IO {

    /**
     * @param FILE
     * @return
     */
    public ReusableBufferedReader loadFilePath(final FileReader FILE) {
        // File lookup

        return new ReusableBufferedReader(new char[1024 * 1024])
                .setSource(FILE);
    }

    /**
     * @param fuelTypesCsvReader
     * @return
     * @throws IOException
     */
    public static String[] loadFuelTypes(ReusableBufferedReader fuelTypesCsvReader) throws IOException {
        LinkedList<String> fuelTypes = new LinkedList<>();

        String line;
        while ((line = fuelTypesCsvReader.readLine()) != null) {
            fuelTypes.add(line);
        }

        fuelTypesCsvReader.close();
        return fuelTypes.toArray(String[]::new);
    }

    /**
     * @param combustionEngineCsvReader
     * @param fuelTypes
     * @return
     * @throws IOException
     */
    public static HashMap<String, TreeMap<Integer, Engine>> loadCombustionEngines(
            ReusableBufferedReader combustionEngineCsvReader, String[] fuelTypes) throws IOException {

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

        combustionEngineCsvReader.close();
        return engineMap;
    }

    /**
     * @param modelsCsvReader
     * @return
     * @throws IOException
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

        return modelHashMap;
    }
}
