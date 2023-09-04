package User;

import java.util.List;
import Container.Container;
import Trip.Trip;

/**
 * IPortManager defines the responsibilities of a Port Manager.
 * It extends the IUser interface and adds functionalities specific to managing a port.
 */
public interface IPortManager extends IUser {
    /**
     * Creates a new container at the port managed by this port manager.
     *
     * @param container The container object to be created.
     */
    void createContainer(Container container);

    /**
     * Retrieves a container's information based on its ID.
     *
     * @param id The unique ID of the container.
     * @return The container object associated with the ID, or null if not found.
     */
    Container readContainer(String id);

    /**
     * Updates an existing container's information.
     *
     * @param container The container object with updated values.
     */
    void updateContainer(Container container);

    /**
     * Deletes a container from the port managed by this port manager.
     *
     * @param id The unique ID of the container to be deleted.
     */
    void deleteContainer(String id);

    /**
     * Lists all trips that have occurred or are scheduled to occur at the port managed by this port manager.
     *
     * @return A list of all trips.
     */
    List<Trip> listTrips();
}
