package com.example.msm.ui.Common.LogIN;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Modles.Admin;
import com.example.msm.FirebaseMSMTest.Service.AdminConnect;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.FirebaseMSMTest.Service.CenterConnect;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;
import com.example.msm.ui.UI_Admin.AdminUI_Main.main_Fragment_manager;
import com.example.msm.ui.UI_Center.CenterUI_Main.Center_Main_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.CustomerUI_Main.Customer_Main_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.VisiorUI_Main.Visitor_Main_Fragment;
import com.google.android.material.navigation.NavigationView;


public class LogIN_Fragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private void checkLogin() {
        sharedPreferences = getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);
        NavigationView navigationView;
        navigationView = (NavigationView)  getActivity().findViewById(R.id.nav_view);

        String Type = sharedPreferences.getString("type", "Is not logged in");

        switch (Type) {
            case "Is not logged in": {

                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_login).setVisible(true);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);


                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(0).setChecked(true);



                break;
            }
            case "Visitor": {
                Menu nav_Menu = navigationView.getMenu();


                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(1).setChecked(false);

                Toast.makeText(this.getActivity(),Type,Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Visitor_Main_Fragment());
                fr.commit();

                break;
            }
            case "Customer": {

                Menu nav_Menu = navigationView.getMenu();


                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(2).setChecked(true);

                Toast.makeText(this.getActivity(),Type,Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Customer_Main_Fragment());
                fr.commit();

                break;
            }
            case "Center": {
                Menu nav_Menu = navigationView.getMenu();

                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(true);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(false);

                navigationView.getMenu().getItem(3).setChecked(true);

                Toast.makeText(this.getActivity(),Type,Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Center_Main_Fragment());
                fr.commit();
                break;
            }
            case "Admin": {


                Menu nav_Menu = navigationView.getMenu();

                nav_Menu.findItem(R.id.nav_login).setVisible(false);
                nav_Menu.findItem(R.id.nav_Visitor_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Customer_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Center_Main).setVisible(false);
                nav_Menu.findItem(R.id.nav_Admin_Main).setVisible(true);

                navigationView.getMenu().getItem(4).setChecked(true);

                Toast.makeText(this.getActivity(),Type,Toast.LENGTH_SHORT).show();

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new main_Fragment_manager());
                fr.commit();
                break;


            }
        }
    }



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


    @Override
    public void onStart() {

        super.onStart();

        checkLogin();

    }



    private EditText et_username,et_password;
    private Spinner sppiner_sign;
    private Button btn_sign_in;

    public LogIN_Fragment() {
        // Required empty public constructor

    }

    public static LogIN_Fragment newInstance() {
        LogIN_Fragment fragment = new LogIN_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        et_username = view.findViewById(R.id.fr_sign_in_et_username);
        et_password = view.findViewById(R.id.fr_sign_in_et_password);
        sppiner_sign = view.findViewById(R.id.fr_sign_in_spinner);
        btn_sign_in = view.findViewById(R.id.fr_sign_in_btn_sign_in);

        sharedPreferences = this.getActivity().getSharedPreferences("LogIN",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (sppiner_sign.getSelectedItem().toString()) {
                    case "Visitor":
                    {
                        editor.putString("type","Visitor");
                        editor.apply();

                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,new Visitor_Main_Fragment());
                        fr.commit();

                        checkLogin();

                        break;
                    }
                    case "Customer":
                    {
                        CustomerConnect.getItem(et_username.getText().toString(), new CustomerConnect.CustomerConnectCallBack() {
                            @Override
                            public void getItemFinished(Customer customer) {
                                if(customer != null){
                                    if(et_password.getText().toString().equals( customer.getPassword())){
                                        editor.putString("e_Mail",et_username.getText().toString());
                                        editor.putString("password",et_password.getText().toString());
                                        editor.putString("type","Customer");
                                        editor.apply();
                                        Toast.makeText(getActivity(), "Login succeeded", Toast.LENGTH_SHORT).show();


                                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                                        fr.replace(R.id.nav_host_fragment,new Customer_Main_Fragment());
                                        fr.commit();

                                        checkLogin();

                                    }
                                }
                            }
                        });
                        break;
                    }
                    case "Center" :
                    {
                        CenterConnect.getItem(et_username.getText().toString(), new CenterConnect.CenterConnectCallBack() {
                            @Override
                            public void getItemFinished(Center center) {
                                if(center != null){
                                    if(et_password.getText().toString().equals( center.getPassword())){
                                        editor.putString("e_Mail",et_username.getText().toString());
                                        editor.putString("password",et_password.getText().toString());
                                        editor.putString("type","Center");
                                        editor.apply();
                                        Toast.makeText(getActivity(), "Login succeeded", Toast.LENGTH_SHORT).show();

                                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                                        fr.replace(R.id.nav_host_fragment,new Center_Main_Fragment());
                                        fr.commit();

                                        checkLogin();
                                    }
                                }
                            }
                        });
                        break;
                    }
                    case  "Admin":
                    {
                        AdminConnect.getItem(et_username.getText().toString(), new AdminConnect.AdminConnectCallBack() {
                            @Override
                            public void getItemFinished(Admin admin) {

                                if(admin != null){
                                    if(et_password.getText().toString().equals(admin.getPassword())){
                                        editor.putString("e_Mail",et_username.getText().toString());
                                        editor.putString("password",et_password.getText().toString());
                                        editor.putString("type","Admin");
                                        editor.apply();
                                        Toast.makeText(getActivity(), "Login succeeded", Toast.LENGTH_SHORT).show();

                                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                                        fr.replace(R.id.nav_host_fragment,new main_Fragment_manager());
                                        fr.commit();

                                        checkLogin();
                                    }
                                }
                            }
                        });
                        break;
                    }
                }
            }});
        return view;
    }
}