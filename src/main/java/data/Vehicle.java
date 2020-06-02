package data;

import data.engines.Engine;

import java.util.*;
import java.util.stream.Collectors;

public class Vehicle {
    private static Random random = new Random();

    private CarModel model;
    private Engine engine;
    private Option[] options;

    private static final double POWER_OFFSET_MARGIN = 0.20;
    private final int MAX_POWER_OFFSET;

    public Vehicle(CarModel model) {
        this.model = model;
        MAX_POWER_OFFSET = (int) (model.getHp() * POWER_OFFSET_MARGIN);
    }

    public void setEngine(HashMap<String, TreeMap<Integer, Engine>> engineMap) {
        List<Engine> engines = filterEngines(engineMap.get(model.getFuelType()));
        this.engine = engines.get(random.nextInt(engines.size()));

        System.out.printf("model engine: %4d hp, fitted engine: %4d hp%n",model.getHp(), this.engine.getHp());
    }

    private List<Engine> filterEngines(TreeMap<Integer, Engine> engineTreeMap) {
        return engineTreeMap.values()
                .stream()
                .filter(this::isEngineWithinModelRange)
                .collect(Collectors.toList());
    }

    private boolean isEngineWithinModelRange(Engine engine) {
        return engine.getHp() <= model.getHp() + MAX_POWER_OFFSET &&
                engine.getHp() >= model.getHp() - MAX_POWER_OFFSET;
    }
}
