package by.demeshko.table.app;

/**
 * Created by ф on 14.3.14.
 */
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class FileExplorerActivity extends ListActivity {

    private File currentDir;
    private FileArrayAdapter adapter;
    private boolean save=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File("/sdcard");
        fill(currentDir);
        if(getIntent().getExtras().get("Type").equals("Save")){
            save=true;
        }
    }

    private void fill(File f)
    {
        File[] dirs = f.listFiles();
        this.setTitle("Current Dir: "+f.getName());
        List<Item> dir = new ArrayList<Item>();
        List<Item> fls = new ArrayList<Item>();
        try{
            for(File ff: dirs)
            {
                Date lastModDate = new Date(ff.lastModified());
                DateFormat formater = DateFormat.getDateTimeInstance();
                String date_modify = formater.format(lastModDate);
                if(ff.isDirectory()){
                    File[] fbuf = ff.listFiles();
                    int buf = 0;
                    if(fbuf != null){
                        buf = fbuf.length;
                    }
                    else{
                        buf = 0;
                    }
                    String num_item = String.valueOf(buf);
                    if(buf == 0) {
                        num_item = num_item + " item";
                    }
                    else{
                        num_item = num_item + " items";
                    }
                    dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),"directory_icon"));
                }
                else
                {
                    String extension = "";
                    int i = ff.getAbsolutePath().lastIndexOf('.');
                    if (i > 0) {
                        extension = ff.getAbsolutePath().substring(i + 1);
                    }
                    if(extension.equals("xml"))
                    fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"file_icon"));
                }
            }
        }catch(Exception e)
        {

        }
        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if(!f.getName().equalsIgnoreCase("sdcard")){
            dir.add(0,new Item("..","Parent Directory","",f.getParent(),"directory_up"));
        }
        if(!f.getName().equalsIgnoreCase("sdcard") && save){
            dir.add(0,new Item("","SAVE HERE","",f.getAbsolutePath(),"directory_up"));
        }
        adapter = new FileArrayAdapter(FileExplorerActivity.this,R.layout.file_exployer,dir);
        this.setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Item o = adapter.getItem(position);
        if(o.getImage().equalsIgnoreCase("directory_icon")||o.getImage().equalsIgnoreCase("directory_up")){
            if(o.getData().equals("SAVE HERE")){
                new SaveDialog(this);
            }
            currentDir = new File(o.getPath());
            fill(currentDir);
        }
        else
        {
            if(save){
              saveResult(o.getName());
           }
            onFileClick(o);
        }
    }
    private void onFileClick(Item o)
    {
        Intent intent = new Intent();
        intent.putExtra("GetPath",currentDir.toString());
        intent.putExtra("GetFileName",o.getName());
        intent.putExtra("Type","Open");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void saveResult(String fileName)
    {
        Intent intent = new Intent();
        intent.putExtra("GetPath",currentDir.toString());
        intent.putExtra("GetFileName",fileName);
        intent.putExtra("Type","Save");
        setResult(RESULT_OK, intent);
        finish();
    }


}