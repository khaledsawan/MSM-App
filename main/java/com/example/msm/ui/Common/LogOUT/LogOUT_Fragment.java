package com.example.msm.ui.Common.LogOUT;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msm.MainActivity;
import com.example.msm.R;
import com.example.msm.ui.Common.Connect_With_Us.Connect_With_Us_Fragment;
import com.example.msm.ui.Common.LogIN.LogIN_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.CustomerUI_Main.Customer_Main_Fragment;
import com.google.android.material.navigation.NavigationView;


public class LogOUT_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String STATUS = "param1";

    // TODO: Rename and change types of parameters


    TextView lo_tv_Main_Text;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LogOUT_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LogOUT_Fragment newInstance() {
        LogOUT_Fragment fragment = new LogOUT_Fragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_log_out, container, false);
        lo_tv_Main_Text = view.findViewById(R.id.lo_tv_Main_Text);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        sharedPreferences = this.getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String Type = sharedPreferences.getString("type","Is not logged in");
        switch (Type){
            case "Is not logged in":
            {
                Toast.makeText(this.getActivity(),"Is not logged in",Toast.LENGTH_SHORT).show();
                lo_tv_Main_Text.setText("انت لم تقم بتسجيل الدخول اساسا");


                break;
            }
            case "Visitor":
            {
                Toast.makeText(this.getActivity(),"Visitor",Toast.LENGTH_SHORT).show();
                lo_tv_Main_Text.setText("تم تسجيل الخروج بنجاح");
                editor.clear();
                editor.apply();

                break;
            }
            case "Customer":
            {
                Toast.makeText(this.getActivity(),"Customer",Toast.LENGTH_SHORT).show();
                lo_tv_Main_Text.setText("تم تسجيل الخروج بنجاح");
                editor.clear();
                editor.apply();

                break;
            }
            case "Center":
            {
                Toast.makeText(this.getActivity(),"Center",Toast.LENGTH_SHORT).show();
                lo_tv_Main_Text.setText("تم تسجيل الخروج بنجاح");
                editor.clear();
                editor.apply();

                break;
            }
            case "Admin":
            {
                Toast.makeText(this.getActivity(),"Admin",Toast.LENGTH_SHORT).show();
                lo_tv_Main_Text.setText("تم تسجيل الخروج بنجاح");
                editor.clear();
                editor.apply();

                break;
            }
        }

        NavigationView navigationView;
        navigationView = (NavigationView) this.getActivity().findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        nav_Menu.findItem(R.id.nav_login).setVisible(true);
        nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
        nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
        nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
        nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);



        navigationView.getMenu().getItem(0).setChecked(true);

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fr.replace(R.id.nav_host_fragment,new LogIN_Fragment());
        fr.commit();

    }
}