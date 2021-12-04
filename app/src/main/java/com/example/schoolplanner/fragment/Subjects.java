package com.example.schoolplanner.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.schoolplanner.Model.Subject;
import com.example.schoolplanner.R;
import com.example.schoolplanner.adapters.SubjectListAdapter;
import com.example.schoolplanner.dao.DataBaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Subjects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Subjects extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Subjects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Subjects.
     */
    // TODO: Rename and change types and number of parameters
    public static Subjects newInstance(String param1, String param2) {
        Subjects fragment = new Subjects();
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

        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

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

        DataBaseHandler db = new DataBaseHandler(getActivity());

        List<Subject> subjects = db.getAllContacts();

//        subjects.add(new Subject("English", "Ms.eng", R.drawable.english, Arrays.asList("textBook", "paper", "pen")));
//        subjects.add(new Subject("Math", "Mr.math", R.drawable.math, Arrays.asList("ruler", "paper", "calculator")));
//        subjects.add(new Subject("Science", "Mr.sci", R.drawable.science, Arrays.asList("computer", "telescope", "pen")));
//        subjects.add(new Subject("English", "Ms.eng", R.drawable.english, Arrays.asList("textBook", "paper", "pen")));
//        subjects.add(new Subject("Math", "Mr.math", R.drawable.math, Arrays.asList("ruler", "paper", "calculator")));
//        subjects.add(new Subject("Science", "Mr.sci", R.drawable.science, Arrays.asList("computer", "telescope", "pen")));
//        subjects.add(new Subject("English", "Ms.eng", R.drawable.english, Arrays.asList("textBook", "paper", "pen")));
//        subjects.add(new Subject("Math", "Mr.math", R.drawable.math, Arrays.asList("ruler", "paper", "calculator")));
//        subjects.add(new Subject("Math", "Mr.math", R.drawable.math, Arrays.asList("ruler", "paper", "calculator")));

        SubjectListAdapter listAdapter = new SubjectListAdapter(getActivity(), subjects);

        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setAdapter(listAdapter);

        // Inflate the layout for this fragment
        return view;


    }
}