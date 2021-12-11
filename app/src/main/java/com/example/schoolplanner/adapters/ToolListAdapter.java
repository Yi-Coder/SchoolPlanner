package com.example.schoolplanner.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.schoolplanner.Model.Subject;
import com.example.schoolplanner.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToolListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private List<String> tools;
    private List<Boolean> checked;

    public ToolListAdapter(@NonNull Activity context, @NonNull List<String> tools, @NonNull List<Boolean> checked) {
        super(context, R.layout.toolistview, tools);
        this.context = context;
        this.tools = tools;
        this.checked = checked;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.toolistview, null,true);
        TextView toolName = (TextView) rowView.findViewById(R.id.toolName);
        CheckBox checkBox = rowView.findViewById(R.id.tool_checkBox);




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked.set(position,isChecked);

                boolean rowResult = true;
                for(Boolean check: checked){
                    rowResult = check && rowResult;
                }

                if(rowResult){
                    Toast.makeText(context, "you are good to go", Toast.LENGTH_LONG).show();
                }
            }
        });

        toolName.setText(tools.get(position));
        toolName.setTextColor(Color.RED);

        return rowView;
    };

}
