package User;

// Import section
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoginValidation {

    /**
     * Authenticates the user based on the provided username and password.
     *
     * @param username the username
     * @param password the password
     * @return An array where the first element is the user type and the second element is the managed port if applicable.
     */
    public static String[] authenticateUser(String username, String password) {
        InputStream is = LoginValidation.class.getClassLoader().getResourceAsStream("Database/users.txt");

        if (is == null) {
            System.out.println("Error: File not found.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                if (st.countTokens() < 3) continue;

                String userType = st.nextToken().trim();
                String dbUsername = st.nextToken().trim();
                String dbPassword = st.nextToken().trim();

                String managedPort = null;

                if (userType.equals("PORT-MANAGER") && st.hasMoreTokens()) {
                    managedPort = st.nextToken().trim();
                }

                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                    return new String[]{userType, managedPort};
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        String username = "portmanager1";  // Replace with user input
        String password = "portmanager1";  // Replace with user input

        String[] authResult = authenticateUser(username, password);

        if (authResult != null) {
            System.out.println("User type: " + authResult[0]);
            if (authResult[1] != null) {
                System.out.println("Managed Port: " + authResult[1]);
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }
}

