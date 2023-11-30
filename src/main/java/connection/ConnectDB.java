package connection;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection;
    private static ISettingsFile env = new JsonSettingsFile("data.json");

    public static Connection getConnection() {
        if (connection == null) {
            initializeConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeConnection() {
        String driverClass = env.getValue("/driverClass").toString();
        String user = env.getValue("/user").toString();
        String password = env.getValue("/password").toString();
        String url = String.format("%s://%s:%s/%s",
                env.getValue("/protocol").toString(),
                env.getValue("/host").toString(),
                env.getValue("/port").toString(),
                env.getValue("/rout").toString());

        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
