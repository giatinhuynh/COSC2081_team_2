package database;

import java.io.*;

/**
 * The DatabaseHandler class provides methods to write and read data from a file.
 */
public class DatabaseHandler {

    /**
     * Writes data to a file.
     *
     * @param filePath The path to the file where data will be written.
     * @param data     The data object to be written to the file.
     * @throws IOException If there is an error while writing to the file.
     */
    public void writeToFile(String filePath, Object data) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(data);
        }
    }

    /**
     * Reads data from a file.
     *
     * @param filePath The path to the file from which data will be read.
     * @throws IOException            If there is an error while reading from the file.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public void readFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            in.readObject();
        }
    }
}
