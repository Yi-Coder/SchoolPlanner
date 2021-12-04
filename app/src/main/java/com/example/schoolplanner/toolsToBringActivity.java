package com.example.schoolplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class toolsToBringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_to_bring);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        List<String> tools = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.);
//        textView.setText(message);
        ArrayAdapter listAdapter = new ArrayAdapter<String>(this,
                R.layout.listview_layout, tools);

        ListView listView = (ListView) findViewById(R.id.tools_list);
        listView.setAdapter(listAdapter);

    }
}