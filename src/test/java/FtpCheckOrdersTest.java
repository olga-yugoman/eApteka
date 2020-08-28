import io.qameta.allure.Issue;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FtpCheckOrdersTest {
    private final FtpHelper ftp = new FtpHelper();;

    @Issue("ONE-9959")
    @ParameterizedTest
    @CsvFileSource(resources = "/properties.csv", numLinesToSkip = 1)
    public void tearDown(String host, String login, String password) throws Exception {
        ftp.connectFtp(host, login, password);
        String dir = "/order";
        ftp.navigate(dir);
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

        Assert.assertTrue(String.format("There is no file added on %s in %s directory!", today, dir),
                dates.contains(today));
    }
}
