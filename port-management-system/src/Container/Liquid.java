package Container;

/**
 * The Liquid class represents a type of container that is used for liquid storage.
 * It inherits from the Container class and has no additional attributes or methods.
 */
public class Liquid extends Container {

    /**
     * Constructs a new Liquid container.
     *
     * @param id The unique ID of the Liquid container.
     * @param weight The weight of the Liquid container.
     */
    public Liquid(String id, double weight) {
        super(id, weight, 4.8, 5.3);
    }
}
