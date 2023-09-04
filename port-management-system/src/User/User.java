package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * User class implementing IUser interface for user authentication and role-specific functionalities.
 */
public class User implements IUser {

    // Member variables for username, password, and user type
    private String username;
    private String password;
    private String userType;
    private String managedPort;

    /**
     * Constructor to initialize a User object.
     *
     * @param username Username of the user
     * @param password Password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Authenticate a user based on username and password.
     *
     * @param username Username entered for login
     * @param password Password entered for login
     * @return Authenticated User object if successful, null otherwise
     */
    public static User authenticateUser(String username, String password) {
        // Reading the user database (users.txt) file
        InputStream is = User.class.getClassLoader().getResourceAsStream("Database/users.txt");

        // Check if file exists
        if (is == null) {
            System.out.println("Error: File not found.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            // Loop through each line in users.txt
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                if (st.countTokens() < 3) continue;

                // Parsing tokens from the current line
                String userType = st.nextToken().trim();
                String dbUsername = st.nextToken().trim();
                String dbPassword = st.nextToken().trim();

                String managedPort = null;

                // If user is a Port Manager, get the managed port
                if (userType.equals("PORT-MANAGER") && st.hasMoreTokens()) {
                    managedPort = st.nextToken().trim();
                }

                // Check if the entered username and password match
                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                    User authenticatedUser = new User(username, password);
                    authenticatedUser.userType = userType;
                    authenticatedUser.managedPort = managedPort;
                    return authenticatedUser;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return null;
    }

    /**
     * Login method for authenticating a user.
     *
     * @param username Username entered for login
     * @param password Password entered for login
     * @return true if authentication is successful, false otherwise
     */
    @Override
    public boolean login(String username, String password) {
        User authenticatedUser = authenticateUser(username, password);
        if (authenticatedUser != null) {
            this.userType = authenticatedUser.userType;
            this.managedPort = authenticatedUser.managedPort;
            return true;
        }
        return false;
    }

    /**
     * Logout method for the user.
     */
    @Override
    public void logout() {
        System.out.println("Logged out successfully.");
    }

    /**
     * Main method for testing user login functionality.
     *
     * @param args Command-line arguments (not used)

    public static void main(String[] args) {
        String username = "admin";  // Replace with user input
        String password = "admin123";  // Replace with user input

        User user = new User(username, password);

        // Perform login
        if (user.login(username, password)) {
            System.out.println("User type: " + user.userType);
            if (user.managedPort != null) {
                System.out.println("Managed Port: " + user.managedPort);
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }
    */
}
