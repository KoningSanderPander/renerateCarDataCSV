package data.engines.types;

import data.engines.Engine;

public class ElectricEngine extends Engine {

    private String fuel;
    private int maxVoltage;
    private int maxCurrent;
    private int kWh;
    private int kmPerKwh;

    public ElectricEngine(
            int engineCode,
            int hp,
            int torque,
            String fuel,
            int maxVoltage,
            int maxCurrent,
            int kWh,
            int kmPerKwh) {
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
