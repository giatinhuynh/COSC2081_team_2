package utils;

import java.util.Date;
import java.io.File;

public class Constants {
    public static final String USERS_FILE_PATH = "database" + File.separator + "data" + File.separator + "users.dat";
    public static final String TRIPS_FILE_PATH = "database" + File.separator + "data" + File.separator + "trips.dat";
    public static final String VEHICLES_FILE_PATH = "database" + File.separator + "data" + File.separator + "vehicles.dat";
    public static final String CONTAINERS_FILE_PATH = "database" + File.separator + "data" + File.separator + "containers.dat";
    public static final String PORTS_FILE_PATH = "database" + File.separator + "data" + File.separator + "ports.dat";


    public static final Date CURRENT_DATE = new Date("2021-01-01");
}
