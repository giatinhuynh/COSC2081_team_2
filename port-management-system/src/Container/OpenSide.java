package Container;

/**
 * The OpenSide class represents a type of container that has opened sides.
 * It inherits from the Container class and has no additional attributes or methods.
 */
public class OpenSide extends Container {

    /**
     * Constructs a new OpenSide container.
     *
     * @param id The unique ID of the OpenSide container.
     * @param weight The weight of the OpenSide container.
     */
    public OpenSide(String id, double weight) {
        super(id, weight, 2.7, 3.2);
    }
}
