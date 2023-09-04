package User;

public interface IPortManager extends IUser {
    void createContainer(Container container);
    Container readContainer(String id);
    void updateContainer(Container container);
    void deleteContainer(String id);
    List<Trip> listTrips();
}
