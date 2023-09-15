package database;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {

    private static final Logger logger = Logger.getLogger(DatabaseHandler.class.getName());

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
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write objects to file: " + filePath, e);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Unexpected error during write operation", e);
        }
    }

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
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Class not found during deserialization from file: " + filePath, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read objects from file: " + filePath, e);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Unexpected error during read operation", e);
        }

        return objects;
    }
}
