package by.demeshko.table.app;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ф on 26.03.2014.
 */
public class Student {

    private String name="";
    private String parentName="";
    private String job="";
    private String position="";
    private String experience="";

    public Student(String name, String parentName, String job, String position, String experience){
        this.name=name;
        this.parentName=parentName;
        this.job=job;
        this.position=position;
        this.experience=experience;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public String getPosition() {
        return position;
    }

    public String getJob() {
        return job;
    }

    public String getParentName() {
        return parentName;
    }

}
