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
        fillData();
    }

    public void fillData(List<Student> students){
       this.students=students;
    }

    public void fillData(){
        for(int i=1;i<31;i++)
            students.add(new Student("name"+i,"parent","job"+i%2,"position",i+""));
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

    public List<Student> getSearchResult(String name, String parent, String job, String expFrom, String expTo){
        List<Student> searchStudents=new ArrayList<Student>();
        searchStudents.addAll(students);
        deleteByName(searchStudents,name);
        deleteByParent(searchStudents,parent);
        deleteByJob(searchStudents,job);
        deleteByExpFrom(searchStudents,expFrom);
        deleteByExpTo(searchStudents,expTo);
        return  searchStudents;
    }

    private void deleteByName(List<Student> studs, String name) {
        if(!name.equals("")) {
            for (int i=0; i<studs.size();i++) {
                if (!studs.get(i).getName().equals(name)){
                    studs.remove(i);
                    i=0;
                }
            }
        }
    }

    private void deleteByParent(List<Student> studs, String parent) {
        if(!parent.equals("")) {
            for (int i=0; i<studs.size();i++) {
                if (!studs.get(i).getParentName().equals(parent)){
                    studs.remove(i);
                    i=0;
                }
            }
        }
    }

    private void deleteByJob(List<Student> studs, String job) {
        if(!job.equals("")) {
            for (int i=0; i<studs.size();i++) {
                if (!studs.get(i).getJob().equals(job)){
                    studs.remove(i);
                    i=0;
                }
            }
        }
    }

    private void deleteByExpFrom(List<Student> studs, String expFrom) {

        double doubleExpFrom;
        try{
            doubleExpFrom=Double.parseDouble(expFrom);
        } catch(NullPointerException nullPointer){
            doubleExpFrom=0;
        } catch (NumberFormatException numbFormat){
            doubleExpFrom=0;
        }

        for (int i=0; i<studs.size();i++) {
            double studExp= Double.parseDouble(studs.get(i).getExperience());
            if (studExp<doubleExpFrom){
                 studs.remove(i);
                 i=0;
            }
        }
    }

    private void deleteByExpTo(List<Student> studs, String expTo) {

        double doubleExpTo;
        try{
            doubleExpTo=Double.parseDouble(expTo);
        } catch(NullPointerException nullPointer){
            doubleExpTo=999999999;
        } catch (NumberFormatException numbFormat){
            doubleExpTo=999999999;
        }

        for (int i=0; i<studs.size();i++) {
            double studExp= Double.parseDouble(studs.get(i).getExperience());
            if (studExp>doubleExpTo){
                studs.remove(i);
                i=0;
            }
        }
    }

    public void deleteStudents(List<Student> deleteStudents){
        students.removeAll(deleteStudents);
    }



    public void addStudent(String name, String parent, String job, String position,String exp){
        double doubleExp;
        try{
            doubleExp=Double.parseDouble(exp);
        } catch(NullPointerException nullPointer){
            doubleExp=0;
        } catch (NumberFormatException numbFormat){
            doubleExp=0;
        }
        students.add(new Student(name,parent,job,position,doubleExp+""));
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

}
