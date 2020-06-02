package data;

public class CarOption implements Comparable<CarOption>{

    private int id;
    private String name;
    private int price;

    public CarOption(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(CarOption other) {
        return Integer.compare(id, other.getId());
    }
}
