package by.demeshko.table.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;

/**
 * Created by alex on 28.03.14.
 */
public class AddStudentDialog {

    private MainActivity mainActivity;
    private View fragmentLayout;


    public AddStudentDialog(MainActivity mainActivity, int width){
        this.mainActivity=mainActivity;
        fragmentLayout=mainActivity.getLayoutInflater().inflate(R.layout.add_dialog, null);
        AlertDialog dialog=getAlert();
        dialog.setView(fragmentLayout);
        dialog.show();
        dialog.getWindow().setLayout(width-(int)(0.03*width), 400);
    }

    private AlertDialog getAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity,3);
        builder.setTitle("New student")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name=((EditText)fragmentLayout.findViewById(R.id.student_name)).getText().toString();
                        String parentName=((EditText)fragmentLayout.findViewById(R.id.parent_name)).getText().toString();
                        String job=((EditText)fragmentLayout.findViewById(R.id.job)).getText().toString();
                        String position=((EditText)fragmentLayout.findViewById(R.id.position)).getText().toString();
                        String experience=((EditText)fragmentLayout.findViewById(R.id.experience)).getText().toString();
                        mainActivity.studentArray.addStudent(new Student(name,parentName,job,position,experience));
                        mainActivity.goToPage(new View(mainActivity));
                        dialog.cancel();
                    }
                });
        return  builder.create();
    }




}
