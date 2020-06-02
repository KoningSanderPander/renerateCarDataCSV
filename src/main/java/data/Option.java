package data;

public class Option {

    private String name;
    private int price;

    private static final double CARMODEL_PRICE_MULTIPLIER = 0.00001;

    public Option(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void recalculateOptionPrice(int carModelPrice) {
        setPrice((int) (this.price * (carModelPrice * CARMODEL_PRICE_MULTIPLIER)));
    }
}
