package com.example.msm.ui.UI_Center.Edit_Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;

import java.util.HashMap;


public class Edit_Customer_Fragment extends Fragment {



    EditText et_first_name,et_last_name,et_password,et_phone;
    Button btn_edit;

    String e_Mail;

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
        open_listener.onNewFragmentOpen("Edit Customer");
    }

    public Edit_Customer_Fragment() {
        // Required empty public constructor
    }


    public static Edit_Customer_Fragment newInstance(String e_Mail) {
        Edit_Customer_Fragment fragment = new Edit_Customer_Fragment();
        Bundle args = new Bundle();
        args.putString("e_Mail",e_Mail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            e_Mail = bundle.getString("e_Mail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        et_first_name = view.findViewById(R.id.pos_edit_cus_et_new_firstname);
        et_last_name = view.findViewById(R.id.pos_edit_cus_et_new_lastname);
        et_password = view.findViewById(R.id.pos_edit_cus_et_new_password);
        et_phone = view.findViewById(R.id.pos_edit_cus_et_new_phone);
        btn_edit = view.findViewById(R.id.pos_edit_cus_btn_Edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String , String> hashMap = new HashMap<>();
                hashMap.put("fName" , et_first_name.getText().toString());
                hashMap.put("lName",et_first_name.getText().toString());
                hashMap.put("password",et_first_name.getText().toString());
                hashMap.put("phone_Number",et_first_name.getText().toString());
                CustomerConnect.upDate(e_Mail,hashMap);

            }
        });


        return view;
    }
}