package Container;

/**
 * The DryStorage class represents a type of container that is used for dry storage.
 * It inherits from the Container class and has no additional attributes or methods.
 */
public class DryStorage extends Container {

    /**
     * Constructs a new DryStorage container.
     *
     * @param id The unique ID of the DryStorage container.
     * @param weight The weight of the DryStorage container.
     */
    public DryStorage(String id, double weight) {
        super(id, weight, 3.5, 4.6);
    }
}
