package Container;

/**
 * The Refrigerated class represents a type of container that is refrigerated.
 * It inherits from the Container class and has no additional attributes or methods.
 */
public class Refrigerated extends Container {

    /**
     * Constructs a new Refrigerated container.
     *
     * @param id The unique ID of the Refrigerated container.
     * @param weight The weight of the Refrigerated container.
     */
    public Refrigerated(String id, double weight) {
        super(id, "Refrigerated", weight, 4.5, 5.4);
    }
}
