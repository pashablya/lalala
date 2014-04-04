package by.demeshko.table.app;

import android.renderscript.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by alex on 04.04.14.
 */
public class XmlDomParser {

    private List<Student> students;

    public XmlDomParser(File xmlFile){
     students=CreateStudents(getDocument(xmlFile));
    }

    public List<Student> getStudents(){
        return students;
    }

    private static Document getDocument(File file) {
        try {
             DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
             f.setValidating(false);
             DocumentBuilder builder = f.newDocumentBuilder();
             return builder.parse(file);
        } catch (SAXException sax) {
          return null;
        }
        catch (IOException io){
          return null;
        }
        catch (ParserConfigurationException parserException){
          return null;
        }
    }

    private List<Student> CreateStudents(Document doc){
       NodeList studentsXmlList = doc.getElementsByTagName("student");
       List<Student> students = new ArrayList<Student>();
        for (int temp = 0; temp < studentsXmlList.getLength(); temp++) {
            Node nNode = studentsXmlList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element  eElement = (org.w3c.dom.Element)nNode;

                String name = eElement.getAttribute("name");
                String parentName = eElement.getElementsByTagName("parent_name").item(0).getTextContent();
                String job = eElement.getElementsByTagName("job").item(0).getTextContent();
                String position = eElement.getElementsByTagName("position").item(0).getTextContent();
                String experience = eElement.getElementsByTagName("experience").item(0).getTextContent();

                students.add(new Student(name,parentName,job,position,experience));
            }
        }
        return students;
    }

}