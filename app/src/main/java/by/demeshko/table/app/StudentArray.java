package by.demeshko.table.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 29.03.14.
 */
public class StudentArray {


    private int studentOnPage=10;
    private int currentPageNumber=1;
    List<Student> students = new ArrayList<Student>();

    public StudentArray(){
        //fillData();
    }

    public void fillData(List<Student> students){
       this.students=students;
    }



    public List<Student> getPage(int pageNumber){
        List<Student>  page = new ArrayList<Student>(studentOnPage);
        for(int i=(pageNumber-1)*studentOnPage;i<studentOnPage*pageNumber;i++)
        {
            try{
                page.add(students.get(i));
            } catch (IndexOutOfBoundsException x){
                break;
            }
        }
        currentPageNumber=pageNumber;
        return page;
    }

    public void addStudent(Student stud){
        students.add(stud);
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }



}
