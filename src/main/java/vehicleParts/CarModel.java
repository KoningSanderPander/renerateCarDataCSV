/**
 * Vehicle model used to create vehicles.
 *
 * @author Sander van den Oetelaar
 */
package vehicleParts;

public class CarModel {
    private int id;
    private String make;
    private String model;
    private String year;
    private String fuelType;
    private String gearbox;
    private int hp;
    private int msrp;
    private String parent;

    public CarModel(
            int id,
            String make,
            String model,
            String year,
            String fuelType,
            String gearbox,
            int hp,
            int msrp,
            String parent) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.gearbox = gearbox;
        this.hp = hp;
        this.msrp = msrp;
        this.parent = parent;
    }

    public String toCsv() {
        return String.format("%d,%s,%s,%s,%s,%s,%d,%d,%s",
                id,
                make,
                model,
                year,
                fuelType,
                gearbox,
                hp,
                msrp,
                parent);
    }
}
