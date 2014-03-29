package by.demeshko.table.app;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ф on 14.3.14.
 */
public class FileOpener {
    public static String readFileSD(String path) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return "ERROR";
        }

        File sdFile = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            StringBuffer buffer = new StringBuffer();
            while ((str = br.readLine()) != null) {
                buffer.append(str + "\n");
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }








}
