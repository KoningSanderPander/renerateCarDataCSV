package data.engines;

public abstract class Engine {

    private String engineCode;
    private int hp;
    private int torque;

    public Engine(String engineCode, int hp, int torque) {
        this.engineCode = engineCode;
        this.hp = hp;
        this.torque = torque;
    }
}
