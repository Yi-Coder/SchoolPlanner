package com.example.schoolplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.schoolplanner.dao.DataBaseHandler;
import com.example.schoolplanner.fragment.AddSubject;
import com.example.schoolplanner.fragment.Curriculum;
import com.example.schoolplanner.fragment.Subjects;
import com.example.schoolplanner.fragment.Tools;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = " com.example.schoolplanner.MESSAGE";
    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.addsubject:
                    selectedFragment = new AddSubject();
                    break;
                case R.id.subjects:
                    selectedFragment = new Subjects();
                    break;
                case R.id.curriculum:
                    selectedFragment = new Curriculum();
                    break;
                case R.id.tools:
                    selectedFragment = new Tools();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSubject()).commit();
//
//        Map<String, List<String>> subjects = new HashMap<>();
//
//        List<String> mathList = new ArrayList<>();
//        mathList.add("math textbook");
//        mathList.add("calculator");
//        mathList.add("paper");
//        subjects.put("math", mathList);
//
//        List<String> englishList = new ArrayList<>();
//        mathList.add("english textbook");
//        mathList.add("notebook");
//        mathList.add("paper");
//        subjects.put("english", englishList);
//
//
//        List<String> scienceList = new ArrayList<>();
//        mathList.add("computer");
//        mathList.add("notebook");
//        mathList.add("tool");
//        subjects.put("science", scienceList);
//
//
//        //get the spinner from the xml.
//        Spinner dropdown = findViewById(R.id.subject_spinner);
//        ArrayAdapter<String> dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>(subjects.keySet()));
//        dropdown.setAdapter(dropDownAdapter);
//
//
//        List<String> subjectsToShow = new ArrayList<>();
//        ArrayAdapter listAdapter = new ArrayAdapter<String>(this,
//                R.layout.listview_layout, subjectsToShow);
//
//        ListView listView = (ListView) findViewById(R.id.subject_list);
//        listView.setAdapter(listAdapter);
//
//        Button addSubjectButton = (Button) findViewById(R.id.button_addsubject);
//        addSubjectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              String selectedSubject =   dropdown.getSelectedItem().toString();
//              subjectsToShow.add(selectedSubject);
//              listView.setAdapter(listAdapter);
//            }
//        });
//
//
//        Button button = (Button) findViewById(R.id.button_send);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), toolsToBringActivity.class);
////                EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
////                String message = editText.getText().toString();
//                Set<String> message = new HashSet<>();
//
//                for (int i = 0; i <  listView.getAdapter().getCount(); i++) {
//                    message.addAll(subjects.get(listView.getAdapter().getItem(i).toString()));
//
//                }
//
//                intent.putStringArrayListExtra(EXTRA_MESSAGE, new ArrayList<>(message));
////                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
////                // Do something in response to button click
////                TextView welcomeTextView = (TextView) findViewById(R.id.welcome);
////                welcomeTextView.setText("hello again");
//            }
//        });
    }

}