package database;

import java.io.*;

/**
 * The DatabaseHandler class provides methods to write and read arrays of objects to/from a file.
 */
public class DatabaseHandler {

    /**
     * Writes an array of objects to a file.
     *
     * @param filePath The path to the file where objects will be written.
     * @param objects  The array of objects to be written to the file.
     * @throws IllegalArgumentException If filePath is null or empty, or if objects is null.
     */
    public void writeObjects(String filePath, Object[] objects) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        if (objects == null) {
            throw new IllegalArgumentException("Objects to write must not be null.");
        }

        try (
                FileOutputStream fs = new FileOutputStream(filePath);
                ObjectOutputStream os = new ObjectOutputStream(fs);
        ) {
            os.writeObject(objects);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an array of objects from a file.
     *
     * @param filePath The path to the file from which objects will be read.
     * @return An array of objects read from the file.
     * @throws IllegalArgumentException If filePath is null or empty.
     */
    public Object[] readObjects(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }

        Object[] objects = null;

        try (
                FileInputStream fi = new FileInputStream(filePath);
                ObjectInputStream os = new ObjectInputStream(fi);
        ) {
            objects = (Object[]) os.readObject();

        } catch (ClassNotFoundException | IOException | RuntimeException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
