package util;

import java.util.Date;
import java.lang.Exception;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtil {

    public static void logError(String string) {
        try {
           File file = new File("log.txt");
            Date date = new Date();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(),true));
            writer.write(date + " " + string);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
