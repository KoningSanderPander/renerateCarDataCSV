package data;

import data.engines.Engine;

import java.util.*;
import java.util.stream.Collectors;

public class Vehicle {
    private static Random random = new Random();

    private CarModel model;
    private Engine engine;
    private TreeSet<CarOption> options;

    private static final double MODEL_OPTION_PRICE_MULTIPLIER = 0.0001;
    private static final double POWER_OFFSET_MARGIN = 0.20;
    private final int MAX_POWER_OFFSET;

    public Vehicle(CarModel model) {
        this.model = model;
        MAX_POWER_OFFSET = (int) (model.getHp() * POWER_OFFSET_MARGIN);
        options = new TreeSet<>();

    }

    public void setEngine(HashMap<String, TreeMap<Integer, Engine>> engineMap) {
        List<Engine> engines = getEnginesInModelRange(engineMap.get(model.getFuelType()));
        engine = engines.get(random.nextInt(engines.size()));
    }

    private List<Engine> getEnginesInModelRange(TreeMap<Integer, Engine> engineTreeMap) {
        return engineTreeMap.values()
                .stream()
                .filter(this::isEngineWithinModelRange)
                .collect(Collectors.toList());
    }

    private boolean isEngineWithinModelRange(Engine engine) {
        return engine.getHp() <= model.getHp() + MAX_POWER_OFFSET &&
                engine.getHp() >= model.getHp() - MAX_POWER_OFFSET;
    }

    public void setOptions(CarOption[] carOptions) {
        int numberOfOptions = random.nextInt(carOptions.length);

        while (options.size() < numberOfOptions) {
            options.add(carOptions[random.nextInt(carOptions.length)]);
        }
    }

    private int calculateOptionPrice(CarOption option) {
        return (int) (option.getPrice() * model.getMsrp() * MODEL_OPTION_PRICE_MULTIPLIER);
    }
}
