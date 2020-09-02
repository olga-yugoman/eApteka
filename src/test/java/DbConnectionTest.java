import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.*;

public class DbConnectionTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/dbproperties.csv", numLinesToSkip = 1)
    public void dbConnectionTest(String server, String db, String user, String password){
        String connectionUrl =
                "jdbc:sqlserver://"+ server +":1433;"
                        + "database="+ db +";"
                        + "user="+ user +";"
                        + "password="+ password +";"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
            System.out.println("nice");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
