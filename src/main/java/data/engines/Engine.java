package data.engines;

public abstract class Engine {

    private long engineCode;
    private int hp;
    private int torque;

    public Engine(long engineCode, int hp, int torque) {
        this.engineCode = engineCode;
        this.hp = hp;
        this.torque = torque;
    }

    public int getHp() {
        return hp;
    }
}
