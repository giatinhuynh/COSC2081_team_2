/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2023B
  Assessment: Group Assignment
  Group: Team Hi
  Members:
  Phan Nhat Minh - s3978598
  Huynh Duc Gia Tin - s3818078
  Nguyen Viet Ha - s3978128
  Vu Minh Ha - s3978681
  Created  date: 02/09/2023
  Acknowledgement: chat.openai.com, stackoverflow.com, geeksforgeeks.org, javatpoint.com, tutorialspoint.com, oracle.com, w3schools.com, github.com
*/
package utils;

import java.io.File;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Utility class that defines constants for the application.
 * This class provides constants related to file paths and a system date.
 */
public class Constants {

    // Root directory for serialized files
    private static final String ROOT_DIR = "src" + File.separator + "database" + File.separator + "data" + File.separator;

    // File names for serialized data
    private static final String CONTAINER_FILE = "containers.ser";
    private static final String PORT_FILE = "ports.ser";
    private static final String VEHICLE_FILE = "vehicles.ser";
    private static final String TRIP_FILE = "trips.ser";
    private static final String USER_FILE = "users.ser";

    // Full paths for serialized data files
    public static final String CONTAINER_FILE_PATH = ROOT_DIR + CONTAINER_FILE;
    public static final String PORT_FILE_PATH = ROOT_DIR + PORT_FILE;
    public static final String VEHICLE_FILE_PATH = ROOT_DIR + VEHICLE_FILE;
    public static final String TRIP_FILE_PATH = ROOT_DIR + TRIP_FILE;
    public static final String USER_FILE_PATH = ROOT_DIR + USER_FILE;

    // Represents the system's starting date
    public static final Date SYSTEM_DATE = initializeSystemDate();

    /**
     * Initializes the SYSTEM_DATE constant.
     *
     * @return The system's starting date.
     * @throws RuntimeException if there's an error parsing the date.
     */
    private static Date initializeSystemDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2020/01/01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse system date", e);
        }
    }
}

