package by.demeshko.table.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

/**
 * Created by alex on 06.04.14.
 */
public class SearchDialog {
    private MainActivity mainActivity;
    private View fragmentLayout;


    public SearchDialog(final MainActivity mainActivity, int width){
        this.mainActivity=mainActivity;
        fragmentLayout=mainActivity.getLayoutInflater().inflate(R.layout.search_dialog, null);
        fragmentLayout.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) fragmentLayout.findViewById(R.id.editName)).getText().toString();
                String parentName = ((EditText) fragmentLayout.findViewById(R.id.editParent)).getText().toString();
                String job = ((EditText) fragmentLayout.findViewById(R.id.editJob)).getText().toString();
                String experienceFrom = ((EditText) fragmentLayout.findViewById(R.id.editExpFrom)).getText().toString();
                String experienceTo = ((EditText) fragmentLayout.findViewById(R.id.editExpTo)).getText().toString();


                List<Student> students = mainActivity.studentArray.getSearchResult(name, parentName, job, experienceFrom, experienceTo);
                SearchAdapter studentSearchAdapter = new SearchAdapter(mainActivity, students, mainActivity.getWindowManager());
                ListView searchList = (ListView) fragmentLayout.findViewById(R.id.searchResult);
                fragmentLayout.findViewById(R.id.toHide).setVisibility(View.GONE);
                searchList.setAdapter(studentSearchAdapter);
            }
        });
        AlertDialog dialog=getAlert();
        dialog.setView(fragmentLayout);
        dialog.show();
        dialog.getWindow().setLayout(width, 1400);
    }

    private AlertDialog getAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity,3);
        builder.setTitle("Search student")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return  builder.create();
    }

}
