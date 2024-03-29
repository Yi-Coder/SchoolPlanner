package com.example.TravelPlanner.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.TravelPlanner.Model.Subject;
import com.example.TravelPlanner.R;
import com.example.TravelPlanner.adapters.SubjectListAdapter;
import com.example.TravelPlanner.dao.DataBaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Curriculum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Curriculum extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Curriculum() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Curriculum.
     */
    // TODO: Rename and change types and number of parameters
    public static Curriculum newInstance(String param1, String param2) {
        Curriculum fragment = new Curriculum();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curriculum, container, false);
        DataBaseHandler db = new DataBaseHandler(getActivity());
        List<Subject> subjects = db.getAllSubjects();

        List<String> subjects_toShow = new ArrayList<>();
        for(Subject subject : subjects){
            subjects_toShow.add(subject.getName());
        }
        ArrayAdapter<String> dropDownAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, subjects_toShow);
        Spinner spinner = (Spinner) view.findViewById(R.id.addsubject_curriculum);
        spinner.setAdapter(dropDownAdapter);

        Button button = view.findViewById(R.id.addsubject_curriculum_button);
        ListView listView = view.findViewById(R.id.curriculumList);
        List<Subject> subjectsAdded = new ArrayList<>();

        SubjectListAdapter adapter =  new SubjectListAdapter(getActivity(), subjectsAdded);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectsAdded.add(db.findByTitle((String)spinner.getSelectedItem()));
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subjectsAdded.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        Button getTools = view.findViewById(R.id.gettools);

        getTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> tools = new HashSet<>();
                for(Subject subject: subjectsAdded){
                    tools.addAll(subject.getTools());
                }
                Bundle result = new Bundle();
                result.putStringArrayList("Tool", new ArrayList<>(tools));
                getActivity().getSupportFragmentManager().setFragmentResult("Tool", result);
                BottomNavigationView navView = getActivity().findViewById(R.id.bottom_navigation);
                navView.setSelectedItemId(R.id.tools_menu);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Tools()).commit();
            }
        });
        return view;
    }
}