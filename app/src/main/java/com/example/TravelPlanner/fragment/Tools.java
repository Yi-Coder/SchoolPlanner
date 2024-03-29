package com.example.TravelPlanner.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.TravelPlanner.adapters.ToolListAdapter;
import com.example.TravelPlanner.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tools#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tools extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<String> tools = new ArrayList<>();
    private List<Boolean> checked = new ArrayList<Boolean>();


    public Tools() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tools.
     */
    // TODO: Rename and change types and number of parameters
    public static Tools newInstance(String param1, String param2) {
        Tools fragment = new Tools();
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
        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        ListView listView = view.findViewById(R.id.toolList);

        ToolListAdapter adapter = new ToolListAdapter(getActivity(), tools, checked);
        listView.setAdapter(adapter);
        getActivity().getSupportFragmentManager().setFragmentResultListener("Tool", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                tools.addAll(result.getStringArrayList("Tool"));
                checked.addAll(Arrays.asList(new Boolean[tools.size()]));
                Collections.fill(checked, Boolean.FALSE);
                adapter.notifyDataSetChanged();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}