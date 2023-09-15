package utils;

import java.io.File;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Constants {

    // Root directory for serialized files
    private static final String ROOT_DIR = "src" + File.separator + "database" + File.separator + "data" + File.separator;

    // File paths
    private static final String CONTAINER_FILE = "containers.ser";
    private static final String PORT_FILE = "ports.ser";
    private static final String VEHICLE_FILE = "vehicles.ser";
    private static final String TRIP_FILE = "trips.ser";
    private static final String USER_FILE = "users.ser";

    // Build full paths using File.separator
    public static final String CONTAINER_FILE_PATH = ROOT_DIR + CONTAINER_FILE;
    public static final String PORT_FILE_PATH = ROOT_DIR + PORT_FILE;
    public static final String VEHICLE_FILE_PATH = ROOT_DIR + VEHICLE_FILE;
    public static final String TRIP_FILE_PATH = ROOT_DIR + TRIP_FILE;
    public static final String USER_FILE_PATH = ROOT_DIR + USER_FILE;

    public static final Date SYSTEM_DATE = initializeSystemDate();

    // Initialize SYSTEM_DATE using a non-deprecated method
    private static Date initializeSystemDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2020/01/01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse system date", e);
        }
    }
}
