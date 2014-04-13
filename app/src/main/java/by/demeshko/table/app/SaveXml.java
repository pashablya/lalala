package by.demeshko.table.app;

/**
 * Created by alex on 06.04.14.

 */


import android.os.Environment;
import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;


public class SaveXml {


  public static void writeFileSD(List<Student> students, String filePath, String fileName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        File sdFile = new File(filePath+"/"+fileName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            bw.write(writeXml(students));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String writeXml(List<Student> students){
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "university");
            serializer.attribute("", "number", String.valueOf(students.size()));
            for (Student stud: students){
                serializer.startTag("", "student");
                serializer.attribute("", "name", stud.getName());

                serializer.startTag("", "parent_name");
                serializer.text(stud.getParentName());
                serializer.endTag("", "parent_name");

                serializer.startTag("", "job");
                serializer.text(stud.getJob());
                serializer.endTag("", "job");

                serializer.startTag("", "position");
                serializer.text(stud.getPosition());
                serializer.endTag("", "position");

                serializer.startTag("", "experience");
                serializer.text(stud.getExperience());
                serializer.endTag("", "experience");

                serializer.endTag("", "student");
            }
            serializer.endTag("", "university");
            serializer.endDocument();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
