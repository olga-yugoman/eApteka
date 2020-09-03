import configuration.SqlConfig;
import helpers.SqlHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Feature("Перенос заказов для финального теста")
public class DbUploadsOrders {

    private final SqlHelper sqlHelper = new SqlHelper();
    String connectionUrl;

    @Issue("ONE-10203")
    @Owner("k.afanaciev@eapteka.ru")
    @Tag("BeforeFinal")
    @DisplayName("Перенос Заказов из копии прода")
    @Test
    public void dbUpoadOrdersTest() {
        final SqlConfig config = ConfigFactory.newInstance().create(SqlConfig.class);
        connectionUrl = sqlHelper.getConnectionUrl(config.server(), config.db(), config.user(), config.password());
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {
            sqlHelper.transferOrdersToTheTestBase(statement);
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
