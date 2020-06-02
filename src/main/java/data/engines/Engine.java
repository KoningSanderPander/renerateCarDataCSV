package data.engines;

public abstract class Engine {

    private int engineCode;
    private int hp;
    private int torque;

    public Engine(int engineCode, int hp, int torque) {
        this.engineCode = engineCode;
        this.hp = hp;
        this.torque = torque;
    }

    public int getHp() {
        return hp;
    }
}
