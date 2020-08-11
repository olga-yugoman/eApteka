import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;

@Feature("Подготовка энва к тестированию.")
public class FtpTest {
    private final FtpHelper ftp = new FtpHelper();;

    @Issue("ONE-9904")
    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void setUp(String host, String login, String password, String dir) throws Exception {
        ftp.upload(host, login, password, dir,
                new File("src/test/resources/test-2.dbf"), "test-2.dbf");
    }
}
