package com.example.TravelPlanner.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.TravelPlanner.Model.Subject;
import com.example.TravelPlanner.R;

import java.util.List;

public class SubjectListAdapter extends ArrayAdapter<Subject> {

    private Activity context;
    private List<Subject> subjects;

    public SubjectListAdapter(@NonNull Activity context, @NonNull List<Subject> subjects) {
        super(context, R.layout.listview_layout, subjects);
        this.context = context;
        this.subjects = subjects;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_layout, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView teacher = (TextView) rowView.findViewById(R.id.teacher);
        TextView tools =  (TextView) rowView.findViewById(R.id.tools_listview);

        imageView.setImageResource(subjects.get(position).getImgId());

        titleText.setText(subjects.get(position).getName());
        titleText.setTextColor(Color.RED);

        teacher.setText(subjects.get(position).getTeacher());

        tools.setText(String.join(",", subjects.get(position).getTools()));
        return rowView;
    };
}
