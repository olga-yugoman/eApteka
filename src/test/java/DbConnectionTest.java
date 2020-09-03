import Helpers.SqlHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.*;

@Feature("Перенос заказов для финального теста")
public class DbConnectionTest {

    @Issue("ONE-10203")
    @Owner("k.afanaciev@eapteka.ru")
    @Tag("BeforeFinal")
    @DisplayName("Перенос Заказов из {db}")
    @ParameterizedTest
    @CsvFileSource(resources = "/dbproperties.csv", numLinesToSkip = 1)
    public void dbConnectionTest(String server, String db, String user, String password) {
        SqlHelper tOrders = new SqlHelper();
        String connectionUrl = tOrders.getConnectionUrl(server, db, user, password);
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {
            tOrders.insertNewOrders(statement);
            System.out.println("Delete");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


}
