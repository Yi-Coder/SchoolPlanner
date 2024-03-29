package com.example.TravelPlanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.TravelPlanner.R;

public class SpinnerAdapter  extends ArrayAdapter<Integer> {

    public SpinnerAdapter(@NonNull Context context, @NonNull Integer[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinneritem, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.subjectimg);
        Integer currentItem = getItem(position);
        if (currentItem != null) {
            imageView.setImageResource(currentItem);
        }
        return convertView;
    }
}
