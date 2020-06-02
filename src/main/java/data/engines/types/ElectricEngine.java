package data.engines.types;

import data.engines.Engine;

public class ElectricEngine extends Engine {

    private String fuel;
    private int maxVoltage;
    private int maxCurrent;
    private int kWh;
    private double kmPerKwh;

    public ElectricEngine(
            long engineCode,
            int hp,
            int torque,
            String fuel,
            int maxVoltage,
            int maxCurrent,
            int kWh,
            double kmPerKwh) {
        super(
                engineCode,
                hp,
                torque
        );
        this.fuel = fuel;
        this.maxVoltage = maxVoltage;
        this.maxCurrent = maxCurrent;
        this.kWh = kWh;
        this.kmPerKwh = kmPerKwh;
    }
}
