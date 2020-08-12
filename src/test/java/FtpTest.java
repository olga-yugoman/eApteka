import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Feature("Подготовка энва к тестированию.")
public class FtpTest {
    private final FtpHelper ftp = new FtpHelper();;

    @Issue("ONE-9904")
    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void setUp(String host, String login, String password, String dir) throws Exception {
        String dateModified = ftp.upload(host, login, password, dir,
                new File("src/test/resources/test-2.dbf"), "test-2.dbf");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);

        Assert.assertEquals("The file wasn't modified today!", today, dateModified);
    }
}
