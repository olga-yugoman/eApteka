import Helpers.FtpHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Constants.FtpConstans.ORDER_DIRECTORY;

@Feature("Проверяем выгрузку Заказов поставщиков на ФТП")
public class FtpCheckOrdersTest {
    private final FtpHelper ftp = new FtpHelper();

    @Issue("ONE-9959")
    @Owner("o.yugoman@eapteka.ru")
    @Tag("AfterFunctionalTests")
    @DisplayName("Проверяю наличие заказа в {host}")
    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void ftpCheckOrderTest(String host, String login, String password) throws Exception {
        ftp.connectFtp(host, login, password);
        ftp.navigate(ORDER_DIRECTORY);
        FTPFile[] files = ftp.getFilesList();
        List<String> dates = new ArrayList();
        for (FTPFile file : files) {
            DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormater.format(file.getTimestamp().getTime());
            dates.add(date);
        }

        ftp.disconnectFtp();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);

        Assert.assertTrue(String.format("There is no file added on %s in %s directory!", today, ORDER_DIRECTORY),
                dates.contains(today));
    }
}
