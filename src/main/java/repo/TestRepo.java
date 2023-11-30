package repo;

import connection.ConnectDB;
import model.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestRepo {
    public static int add(Test test) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = String.format("INSERT INTO Test (name, time, status, authorId, projectId) VALUES ('%s', '%s', %d, %d, %d);",
                test.getName(), test.getTime(), test.getStatus(), test.getAuthorId(), test.getProjectId());
        int autoIncKeyFromApi = -1;

        try {
            stmt = ConnectDB.getConnection().createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoIncKeyFromApi;
    }

    public static Test get(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Test test = null;
        String query = String.format("SELECT * FROM Test WHERE id = %d;", id);

        try {
            statement = ConnectDB.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            test = new Test(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static List<Test> getListReDigits() {
        List<Test> testList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Test WHERE id REGEXP '([0-9])\\1+';";

        try {
            stmt = ConnectDB.getConnection().createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                testList.add(new Test(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public static void delete(int id) {
        Statement stmt = null;
        String query = String.format("DELETE FROM Test WHERE id = %d;", id);

        try {
            stmt = ConnectDB.getConnection().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Test test){
        Statement stmt = null;
        String query = String.format("UPDATE Test SET name = '%s', time = '%s', status = %d, authorId = %d, projectId = %d WHERE id = %d;",
                test.getName(), test.getTime(), test.getStatus(), test.getAuthorId(), test.getProjectId(),  test.getId());

        try {
            stmt = ConnectDB.getConnection().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
