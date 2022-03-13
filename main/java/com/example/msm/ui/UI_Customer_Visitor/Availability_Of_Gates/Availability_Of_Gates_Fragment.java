package com.example.msm.ui.UI_Customer_Visitor.Availability_Of_Gates;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Gates;
import com.example.msm.FirebaseMSMTest.Service.GatesConnect;
import com.example.msm.R;

public class Availability_Of_Gates_Fragment extends Fragment {



    private AppBarListener open_listener;

    @Override
    public void onResume() {
        super.onResume();
        open_listener.onNewFragmentOpen("Availability Of Gates");
    }

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

    Button aog_btn_OK;
    EditText aog_et_Number;

    public Availability_Of_Gates_Fragment() {
        // Required empty public constructor
    }

    public static Availability_Of_Gates_Fragment newInstance() {
        Availability_Of_Gates_Fragment fragment = new Availability_Of_Gates_Fragment();
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
        View view = inflater.inflate(R.layout.availability_of_gates_fragment, container, false);
        aog_btn_OK = view.findViewById(R.id.aog_btn_Check_Up);
        aog_et_Number = view.findViewById(R.id.aog_et_Number);

        aog_btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(aog_et_Number.getText().length()==10){
                    GatesConnect.getItem(aog_et_Number.getText().toString().substring(0,3), new GatesConnect.GateConnectCallBack() {
                        @Override
                        public void getItemFinished(Gates gate) {
                            if(gate.getIs_Available() == true){
                                Toast.makeText(getContext(),"هنالك بوابات متوفرة لهذه المدينة",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(),"لا يوجد بوابات متوفرة حالياً لهذه المدينة",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }


            }
        });


        return view;
    }
}