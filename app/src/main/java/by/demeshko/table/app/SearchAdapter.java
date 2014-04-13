package by.demeshko.table.app;

import android.app.ActionBar;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ф on 26.03.2014.
 */
public class SearchAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    List<Student> students;
    WindowManager windowManager;

    public SearchAdapter(Context context, List<Student> students, WindowManager windowManager){
        this.context=context;
        this.students=students;
        this.windowManager=windowManager;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return students.size();
    }

    @Override
    public Object getItem(int positions){
        return students.get(positions);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    Student getStudent(int position) {
        return ((Student) getItem(position));
    }

    private void initTextViewSizes(View view){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels-(int)(0.07*displaymetrics.widthPixels);
        view.findViewById(R.id.student_name).setLayoutParams(new LinearLayout.LayoutParams((int) (width * 0.22), 150));
        view.findViewById(R.id.parent_name).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.22),150));
        view.findViewById(R.id.job).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.14),150));
        view.findViewById(R.id.position).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.14),150));
        view.findViewById(R.id.experience).setLayoutParams(new LinearLayout.LayoutParams((int)(width*0.9),150));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.student_layout, parent, false);
        }

        Student stud = getStudent(position);
        ((TextView) view.findViewById(R.id.student_name)).setText(stud.getName());
        ((TextView) view.findViewById(R.id.parent_name)).setText(stud.getParentName());
        ((TextView) view.findViewById(R.id.job)).setText(stud.getJob());
        ((TextView) view.findViewById(R.id.position)).setText(stud.getPosition());
        ((TextView) view.findViewById(R.id.experience)).setText(stud.getExperience());
        initTextViewSizes(view);
        return view;
    }

}