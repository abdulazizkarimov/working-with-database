package repo;

import connection.ConnectDB;
import model.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectRepo {
    public static void add(Project project) {
        Statement statement = null;
        String query = String.format("INSERT INTO Project (projectName) VALUES ('%s');", project.getProjectName());

        try {
            statement = ConnectDB.getConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Project get(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Project project = null;
        String query = String.format("SELECT * FROM Project WHERE id = %d;", id);

        try {
            statement = ConnectDB.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            project = new Project(
                    resultSet.getInt(1),
                    resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}
