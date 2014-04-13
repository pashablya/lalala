package by.demeshko.table.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by alex on 09.04.14.
 */
public class DeleteDialog {
    private MainActivity mainActivity;
    private View fragmentLayout;


    public DeleteDialog(final MainActivity mainActivity, int width){
        this.mainActivity=mainActivity;
        fragmentLayout=mainActivity.getLayoutInflater().inflate(R.layout.search_dialog, null);
        ((Button)fragmentLayout.findViewById(R.id.searchButton)).setText("Delete");
        fragmentLayout.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) fragmentLayout.findViewById(R.id.editName)).getText().toString();
                String parentName = ((EditText) fragmentLayout.findViewById(R.id.editParent)).getText().toString();
                String job = ((EditText) fragmentLayout.findViewById(R.id.editJob)).getText().toString();
                String experienceFrom = ((EditText) fragmentLayout.findViewById(R.id.editExpFrom)).getText().toString();
                String experienceTo = ((EditText) fragmentLayout.findViewById(R.id.editExpTo)).getText().toString();
                List<Student> students = mainActivity.studentArray.getSearchResult(name, parentName, job, experienceFrom, experienceTo);
                Toast.makeText(mainActivity,"Deleted "+students.size()+" students",Toast.LENGTH_LONG).show();
                mainActivity.studentArray.deleteStudents(students);
                mainActivity.studentAdapter.notifyDataSetChanged();
            }
        });
        
        AlertDialog dialog=getAlert();
        dialog.setView(fragmentLayout);
        dialog.show();
        dialog.getWindow().setLayout(width-(int)(0.03*width), 1400);
    }

    private AlertDialog getAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity,3);
        builder.setTitle("Delete student")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return  builder.create();
    }

}
