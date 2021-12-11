package com.example.schoolplanner.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolplanner.Model.Subject;
import com.example.schoolplanner.R;
import com.example.schoolplanner.adapters.SpinnerAdapter;
import com.example.schoolplanner.dao.DataBaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSubject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubject extends Fragment {


    private Integer [] images = new  Integer [] { R.drawable.math, R.drawable.english, R.drawable.science,  R.drawable.science};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSubject() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSubjuectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSubject newInstance(String param1, String param2) {
        AddSubject fragment = new AddSubject();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_subjuect, container, false);;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        DataBaseHandler db = new DataBaseHandler(getActivity());
        Button button = getView().findViewById(R.id.button_addSubject);
        EditText editTitle = view.findViewById(R.id.input_title);
        EditText editTeacher = view.findViewById(R.id.teacherName);
        EditText editTools = view.findViewById(R.id.tools);
        Spinner mySpinner = view.findViewById(R.id.imgid);
        mySpinner.setPrompt("please select subject icon");
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), images);
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Arrays.asList(new String[]{"1", "2"}));
        mySpinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String teacher = editTeacher.getText().toString();
                Integer imgId =  (Integer) mySpinner.getSelectedItem();
                List<String> tools = Arrays.asList(editTools.getText().toString().split(","));

                if(title.isEmpty() || teacher.isEmpty() || imgId == null || tools.isEmpty()){
                    Toast.makeText(getContext(), "input can't be null", Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject = new Subject(title,teacher, imgId, tools);
                    db.addSubject(subject);
                    BottomNavigationView navView = getActivity().findViewById(R.id.bottom_navigation);
                    navView.setSelectedItemId(R.id.subjects);
                }
            }
        });
    }
}