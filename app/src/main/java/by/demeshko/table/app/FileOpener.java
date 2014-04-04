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
    public static File readFileSD(String path) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File sdFile = new File(path);
        return sdFile;
    }
}
