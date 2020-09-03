import Helpers.FtpHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Constants.FtpConstans.PRICE_DIRECTORY;

@Feature("Подготовка энва к тестированию.")
public class FtpUploadPrices {
    private final FtpHelper ftp = new FtpHelper();
    private final File file = new File("src/test/resources/test-2.dbf");

    @Issue("ONE-9904")
    @Tag("BeforeFunctionalTests")
    @Owner("o.yugoman@eapteka.ru")
    @DisplayName("Загружаю Прайс лист на ФТП - {host}")
    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void ftpUploadPrices(String host, String login, String password) throws Exception {
        ftp.connectFtp(host, login, password);
        ftp.upload(PRICE_DIRECTORY, file, "test-2.dbf");
        String dateModified = ftp.getDateFileModified(file);
        ftp.disconnectFtp();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);

        Assert.assertEquals("The file wasn't modified today!", today, dateModified);
    }
}
