package interfaces;

import java.util.List;

public interface ICRUD<T> {

    void create(T item);

    T read(int id);

    List<T> readAll();

    void update(T item);

    void delete(int id);
}
