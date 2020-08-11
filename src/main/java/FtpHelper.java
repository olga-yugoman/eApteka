import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    FTPClient ftp = new FTPClient();

    public void upload (String host, String login, String password, String dir, File file, String target) throws IOException {
        ftp.connect(host);
        showServerReply(ftp);
        ftp.login(login, password);
        showServerReply(ftp);
        ftp.changeWorkingDirectory(dir);
        showServerReply(ftp);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        showServerReply(ftp);
        ftp.logout();
        showServerReply(ftp);
        ftp.disconnect();
    }

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
