package by.demeshko.table.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by alex on 04.04.14.
 */
public class SaveDialog {

    private EditText edit;


    public SaveDialog(FileExplorerActivity fileActivity){
        edit= new EditText(fileActivity);
        AlertDialog dialog=getAlert(fileActivity);
        dialog.setView(edit);
        dialog.show();
    }

    private AlertDialog getAlert(final FileExplorerActivity fileActivity){
        AlertDialog.Builder builder = new AlertDialog.Builder(fileActivity,3);
        builder.setTitle("Enter file name")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StringBuilder builder=new StringBuilder(edit.getText().toString());
                        if(builder.indexOf(".xml")!=-1) {
                            fileActivity.saveResult(builder.toString());
                            dialog.cancel();
                        }
                        else {
                            dialog.cancel();
                            new SaveDialog(fileActivity);
                            Toast.makeText(fileActivity, "name must consist \".xml\"", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return  builder.create();
    }
}