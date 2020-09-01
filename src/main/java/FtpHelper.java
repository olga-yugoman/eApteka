import io.qameta.allure.Step;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    FTPClient ftp = new FTPClient();

    @Step("Подключение к FTP серверу.")
    public  void connectFtp(String host, String login, String password) throws IOException {
        ftp.connect(host);
        showServerReply(ftp);
        ftp.enterLocalPassiveMode();
        ftp.login(login, password);
        showServerReply(ftp);
    }

    @Step("Отключение от FTP сервера.")
    public  void disconnectFtp() throws IOException {
        ftp.logout();
        showServerReply(ftp);
        ftp.disconnect();
    }

    @Step("Загрузка файла на FTP сервер.")
    public void upload (String dir, File file, String target) throws IOException {
        navigate(dir);
        showServerReply(ftp);
        ftp.storeFile(target, new FileInputStream(file));
        showServerReply(ftp);
    }

    @Step("Перейти в папку {dir} на FTP сервере.")
    public void navigate (String dir) throws IOException {
        ftp.changeWorkingDirectory(dir);
        showServerReply(ftp);
    }

    @Step("Получить дату последнего редактирования {file}")
    public String getDateFileModified(File file) throws IOException {
        String date = ftp.getModificationTime(file.getName());

        return date.substring(0, 8);
    }

    @Step("Получить список файлов в текущей папке")
    public FTPFile[] getFilesList() throws IOException {
        return ftp.listFiles();
    }

    @Step("Показать ответ от FTP сервера.")
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
