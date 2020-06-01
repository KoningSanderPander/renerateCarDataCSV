package data.engines.types;

import data.engines.Engine;

public class Combustion extends Engine {

    private String fuel;
    private int cylinders;
    private int hasForcedInduction;
    private int displacement;
    private int maxRPM;
    private int peakRPM;
    private double kmPerLiter;

    public Combustion(
            String engineCode,
            int hp,
            int torque,
            String fuel,
            int cylinders,
            int hasForcedInduction,
            int displacement,
            int maxRPM,
            int peakRPM,
            double kmPerLiter) {
        super(
                engineCode,
                hp,
                torque
        );
        this.fuel = fuel;
        this.cylinders = cylinders;
        this.hasForcedInduction = hasForcedInduction;
        this.displacement = displacement;
        this.maxRPM = maxRPM;
        this.peakRPM = peakRPM;
        this.kmPerLiter = kmPerLiter;
    }
}
