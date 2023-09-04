package Container;

/**
 * The OpenTop class represents a type of container with an opened top.
 * It inherits from the Container class and has no additional attributes or methods.
 */
public class OpenTop extends Container {

    /**
     * Constructs a new OpenTop container.
     *
     * @param id The unique ID of the OpenTop container.
     * @param weight The weight of the OpenTop container.
     */
    public OpenTop(String id, double weight) {
        super(id, weight, 2.8, 3.2);
    }
}
