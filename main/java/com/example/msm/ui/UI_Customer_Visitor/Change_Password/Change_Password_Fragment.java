package com.example.msm.ui.UI_Customer_Visitor.Change_Password;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;


public class Change_Password_Fragment extends Fragment {

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
        open_listener.onNewFragmentOpen("Change Password");
    }




    public Change_Password_Fragment() {
        // Required empty public constructor
    }


    SharedPreferences sharedPreferences;
    ScrollView cp_sv_Main_ScrollView ;
    LinearLayout cp_sv_Main_ScrollView_LinearLyout;
    TextView cp_tv_Text;
    TextInputLayout cp_pet_New_Password_layout;
    EditText cp_pet_New_Password;
    TextInputLayout cp_pet_Confirm_Password_Change_layout;
    EditText cp_pet_Confirm_Password_Change;
    Button cp_btn_Confirm_Password_Change;

    public static Change_Password_Fragment newInstance() {
        Change_Password_Fragment fragment = new Change_Password_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);


        /********************************************************************************************************************************/

        cp_sv_Main_ScrollView = view.findViewById(R.id.cp_sv_Main_ScrollView);
        cp_sv_Main_ScrollView_LinearLyout = view.findViewById(R.id.cp_sv_Main_ScrollView_LinearLyout);
        cp_tv_Text = view.findViewById(R.id.cp_tv_main_Text);
        cp_pet_New_Password_layout = view.findViewById(R.id.cp_pet_New_Password_layout);
        cp_pet_New_Password = view.findViewById(R.id.cp_pet_New_Password);
        cp_pet_Confirm_Password_Change_layout = view.findViewById(R.id.cp_pet_Confirm_Password_Change_layout);
        cp_pet_Confirm_Password_Change = view.findViewById(R.id.cp_pet_Confirm_Password_Change);
        cp_btn_Confirm_Password_Change = view.findViewById(R.id.cp_btn_Confirm_Password_Change);

        /********************************************************************************************************************************/


        cp_sv_Main_ScrollView_LinearLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getRootView().requestFocus();
            }
        });

        /********************************************************************************************************************************/

        cp_pet_New_Password.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==0){
                    cp_pet_New_Password_layout.setError(null);
                    cp_pet_New_Password_layout.setHelperText("*مطلوب");
                }
                for (char c : s.toString().toCharArray()) {
                    if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c >= '0' && c <= '9')) {
                        cp_pet_New_Password_layout.setHelperText("");
                        cp_pet_New_Password_layout.setError("إدخال خاطئ");
                        break;
                    }else{
                        cp_pet_New_Password_layout.setHelperText("*مطلوب");
                        cp_pet_New_Password_layout.setError(null);
                    }
                }
            }
        });

        /********************************************************************************************************************************/

        cp_pet_New_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        /********************************************************************************************************************************/

        cp_pet_Confirm_Password_Change.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==0){
                    cp_pet_Confirm_Password_Change_layout.setError(null);
                    cp_pet_Confirm_Password_Change_layout.setHelperText("*مطلوب");
                }
                for (char c : s.toString().toCharArray()) {
                    if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c >= '0' && c <= '9')) {
                        cp_pet_Confirm_Password_Change_layout.setHelperText("");
                        cp_pet_Confirm_Password_Change_layout.setError("إدخال خاطئ");
                        break;
                    }else{
                        cp_pet_Confirm_Password_Change_layout.setHelperText("*مطلوب");
                        cp_pet_Confirm_Password_Change_layout.setError(null);
                    }
                }
            }
        });

        /********************************************************************************************************************************/

        cp_pet_Confirm_Password_Change.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        /********************************************************************************************************************************/

        cp_btn_Confirm_Password_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cp_pet_Confirm_Password_Change_layout.getError() == null && cp_pet_New_Password_layout.getError() == null
                        &&    cp_pet_New_Password.getText().toString().equals(cp_pet_Confirm_Password_Change.getText().toString())
                        &&    !cp_pet_New_Password.getText().toString().equals("")
                ){
                    sharedPreferences = getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);
                    String e_Mail = sharedPreferences.getString("e_Mail", "Is not logged in");
                    HashMap hashMap = new HashMap();
                    hashMap.put("password",cp_pet_New_Password.getText().toString());
                    CustomerConnect.upDate(e_Mail,hashMap);
                    Toast.makeText(getActivity(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

/********************************************************************************************************************************/

        return view;
    }
}