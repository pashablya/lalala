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
public class StudentDialog {

    private  String[] names ;


    private Activity MainActivity;
    private EditText editText;
    private View fragmentLayout;


    public StudentDialog(Activity MainActivity, int width){
        this.MainActivity=MainActivity;
        fragmentLayout=MainActivity.getLayoutInflater().inflate(R.layout.add_dialog, null);
        //editText=(EditText)fragmentLayout.findViewById(R.id.editText);
        AlertDialog dialog=getAlert();
        dialog.setView(fragmentLayout);
        dialog.show();
        dialog.getWindow().setLayout(width-(int)(0.03*width), 400);
    }

    private AlertDialog getAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity,3);
        builder.setTitle("New student")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        return  builder.create();
    }




}
