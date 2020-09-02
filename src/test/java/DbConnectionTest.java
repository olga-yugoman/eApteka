import io.qameta.allure.Issue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.*;

public class DbConnectionTest {

    @Issue("ONE-10203")
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

        String tryDelete = "delete from s11dev.courierDS_test.dbo.t_orders";
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {
            // Create and execute a SELECT SQL statement.
            statement.execute(tryDelete);
            System.out.println("Delete");
            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


}
