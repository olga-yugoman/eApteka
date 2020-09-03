package helpers;

import io.qameta.allure.Step;

import java.sql.SQLException;
import java.sql.Statement;

import static сonstants.SqlConstans.*;

public class SqlHelper {

    @Step("Заполняем строку подключения")
    public static String getConnectionUrl(String server, String db, String user, String password) {
        String connectionUrl =
                "jdbc:sqlserver://" + server + ":1433;"
                        + "database=" + db + ";"
                        + "user=" + user + ";"
                        + "password=" + password + ";"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        return connectionUrl;
    }

    @Step("Импортируем заказы из прода")
    public static void transferOrdersToTheTestBase(Statement statement) throws SQLException {
        statement.execute(DELETE_FROM_T_ORDERS);
        statement.execute(DELETE_FROM_T_OREDERS_PARTNER);
        statement.execute(DELETE_FROM_T_OREDERS_PRODUCT);
        statement.execute(INSERT_INTO_T_ORDERS);
        statement.execute(INSERT_INTO_T_OREDERS_PARTNER);
        statement.execute(INSERT_INTO_T_OREDERS_PRODUCT);
        statement.execute(UPDATE_T_ORDERS);
        statement.execute(UPDATE_T_ORDERS_PARTNER);
    }
}
