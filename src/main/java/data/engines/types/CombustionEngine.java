package data.engines.types;

import data.engines.Engine;

public class CombustionEngine extends Engine {

    private String fuel;
    private int cylinders;
    private String forcedInductionType;
    private int displacement;
    private int maxRPM;
    private int peakRPM;
    private double kmPerLiter;

    public CombustionEngine(
            long engineCode,
            int hp,
            int torque,
            String fuel,
            int cylinders,
            String forcedInductionType,
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
        this.forcedInductionType = forcedInductionType;
        this.displacement = displacement;
        this.maxRPM = maxRPM;
        this.peakRPM = peakRPM;
        this.kmPerLiter = kmPerLiter;
    }
}
