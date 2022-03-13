
package com.example.msm.ui.Common.Connect_With_Us;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Connect_With_Us_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Connect_With_Us_Fragment extends Fragment {


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
        open_listener.onNewFragmentOpen("Connect with us");
    }

    public Connect_With_Us_Fragment() {
        // Required empty public constructor
    }


    public static Connect_With_Us_Fragment newInstance(String param1, String param2) {
        Connect_With_Us_Fragment fragment = new Connect_With_Us_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_connect_with_us, container, false);

        return view;
    }
}