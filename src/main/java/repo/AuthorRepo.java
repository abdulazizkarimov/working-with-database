package repo;

import connection.ConnectDB;
import model.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorRepo {
    public static void add(Author author) {
        Statement statement = null;
        String query = String.format("INSERT INTO Author (name) VALUES ('%s');", author.getName());

        try {
            statement = ConnectDB.getConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Author get(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Author author = null;
        String query = String.format("SELECT * FROM Author WHERE id = %d;", id);

        try {
            statement = ConnectDB.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            author = new Author(
                    resultSet.getInt(1),
                    resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return author;
    }
}