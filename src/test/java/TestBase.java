import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;

public class TestBase {
    private final FtpHelper ftp = new FtpHelper();;

    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void setUp(String host, String login, String password, String dir) throws Exception {
        ftp.upload(host, login, password, dir,
                new File("src/test/resources/test-2.dbf"), "test-2.dbf");
    }
}
