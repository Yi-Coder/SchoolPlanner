package com.example.TravelPlanner.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.TravelPlanner.Model.Subject;
import com.example.TravelPlanner.dao.DataBaseHandler;
import com.example.TravelPlanner.R;
import com.example.TravelPlanner.adapters.SubjectListAdapter;

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
        DataBaseHandler db = new DataBaseHandler(getActivity());
        List<Subject> subjects = db.getAllSubjects();
        SubjectListAdapter listAdapter = new SubjectListAdapter(getActivity(), subjects);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if(db.deleteByName(subjects.get(position).getName())){
                    subjects.remove(position);
                    listAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        // Inflate the layout for this fragment

        return view;
    }
}