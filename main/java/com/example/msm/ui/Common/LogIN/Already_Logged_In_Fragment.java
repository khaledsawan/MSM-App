package com.example.msm.ui.Common.LogIN;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Already_Logged_In_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Already_Logged_In_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEXT = "param1";

    // TODO: Rename and change types of parameters
    private String text;
    TextView ali_tv_Main_Text;

    private AppBarListener open_listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AppBarListener){
            open_listener = (AppBarListener) context;
        }else{
            throw new ClassCastException("Your activity doesn't implements AppBarListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        open_listener= null;
    }

    @Override
    public void onResume() {
        super.onResume();
        open_listener.onNewFragmentOpen("Login");
    }

    public Already_Logged_In_Fragment() {
        // Required empty public constructor
    }



    public static Already_Logged_In_Fragment newInstance(String text) {
        Already_Logged_In_Fragment fragment = new Already_Logged_In_Fragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_already_logged_in, container, false);
        ali_tv_Main_Text = view.findViewById(R.id.ali_tv_Main_Text);
        ali_tv_Main_Text.setText("لقد سجلت الدخول مسبقا ك "+text);
        return view;
    }
}