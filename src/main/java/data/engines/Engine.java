package models.engines;

public abstract class Engine {

    private String engineCode;
    private int hp;
    private int torque;

    protected abstract void setTorque();

}
