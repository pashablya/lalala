package by.demeshko.table.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final int REQUEST_PATH = 1;
    List<Student> currentPage = new ArrayList<Student>();
    StudentAdapter studentAdapter;
    StudentArray studentArray=new StudentArray();
    View footer;
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPage=studentArray.getPage(1);
        studentAdapter = new StudentAdapter(this, currentPage,getWindowManager());
        footer = getLayoutInflater().inflate(R.layout.footer, null);
        lvMain = (ListView) findViewById(R.id.listView);
        lvMain.addFooterView(footer);
        lvMain.setAdapter(studentAdapter);
        initHeader();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            new AddStudentDialog(this,getWidth());
            return true;
        }
        if (id == R.id.open) {
            getfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getfile(){
        Intent intent = new Intent(this, FileExplorerActivity.class);
        startActivityForResult(intent,REQUEST_PATH);
    }
    // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH){
            if (resultCode == RESULT_OK) {
                Toast.makeText(getBaseContext(), "Yo choose: " + data.getStringExtra("GetPath") + "/" + data.getStringExtra("GetFileName"), Toast.LENGTH_LONG).show();
                File file= FileOpener.readFileSD(data.getStringExtra("GetPath")+"/"+data.getStringExtra("GetFileName"));
                studentArray.fillData(new XmlDomParser(file).getStudents());
                goToPage(new View(this));
            }
        }
    }

    public void goToPage(View v){
        String s=((EditText)(footer.findViewById(R.id.pageNumber))).getText().toString();
        try{
             int i=Integer.parseInt(s);
             currentPage=studentArray.getPage(i);
             studentAdapter=new StudentAdapter(this, currentPage,getWindowManager());
             lvMain.setAdapter(studentAdapter);
        }catch (Exception e){}
    }

    public void nextPage(View v){
        ((EditText)(footer.findViewById(R.id.pageNumber))).setText(""+(studentArray.getCurrentPageNumber()+1));
        goToPage(v);
    }

    public void prevPage(View v){
        ((EditText)(footer.findViewById(R.id.pageNumber))).setText(""+(studentArray.getCurrentPageNumber()-1));
        goToPage(v);
    }


    public int getWidth(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels-(int)(0.07*displaymetrics.widthPixels);
        return width;
    }


    private void initHeader(){
        int width = getWidth();
        findViewById(R.id.student_name).setLayoutParams(new LinearLayout.LayoutParams((int) (width * 0.27), 90));
        findViewById(R.id.parent_name).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.27),90));
        findViewById(R.id.job).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.17),90));
        findViewById(R.id.position).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.18),90));
        findViewById(R.id.experience).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.11),90));
    }

}