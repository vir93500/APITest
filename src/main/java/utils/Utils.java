package utils;

import Testbase.TestSetup;
import com.aventstack.extentreports.Status;
import enums.LogType;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;

public class Utils extends TestSetup {

    public static <T> void logDetails(final T response, LogType logType) {
        //String res = JSONUtils.toJSON(response);
        String res = response.toString().replaceAll("\\\\", "");
        logger().log(Status.INFO,
                ("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + logType.getValue()
                        + "</font>" + "</b >" + "</summary>" + res.replaceAll(",", "<br>") + "</details>"
                        + " \n"));
    }

    public static <T> void logAssertionDetails(String response, Status status) {
        logger().log(status, response);
    }

    public static Properties readProperty(String propPathLocation) {
        File dir = new File(propPathLocation);
        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(dir);
            prop.load(fis);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return prop;
    }

    public static Properties readProperty(InputStream ins) {
        Properties prop = new Properties();

        try {
            prop.load(ins);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return prop;
    }

    public static void createReport() throws IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = f.format(new Date());
        File file = new File("./extentreport.html");
        file.renameTo(new File("./src/main/resources/report/" +"extentreport_" + timestamp + ".html"));

    }

    public static void moveReportToArchive() {

        File[] files = new File("./src/main/resources/report/").listFiles((FileFilter) new WildcardFileFilter("extentreport*.html"));
        //File[] files = new File(Constants.REPORT_PATHNAME).listFiles();

        for (int i = 0; i < files.length; i++) {
        File file = files[i];

        //File file = files[0];
        String fileName = file.getName();
        if (file.exists()) {

            file.renameTo(new File("./src/main/resources/archivedReports/" + fileName));
            System.out.println("File has been moved");
            file.delete();
        } else {
            System.out.println("File has not been moved");
        }
    }

    }

    public static int randomNumberGeneration() {
        Random ran = new Random(System.currentTimeMillis());
        return 10000000 + ran.nextInt(99999999);
    }
}