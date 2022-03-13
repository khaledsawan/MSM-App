package com.example.msm.ui.UI_Center.Add_Offer;

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

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.FirebaseMSMTest.Service.AdvertisementsConnect;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Service.Serial_Numbers;
import com.example.msm.R;


public class Add_Offer_Fragment extends Fragment {


    EditText pos_add_offer_et_title, pos_add_offer_et_offer;
    Button pos_add_offer_btn_add_offer;

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
        open_listener.onNewFragmentOpen("Add Offer");
    }

    public Add_Offer_Fragment() {
        // Required empty public constructor
    }

    public static Add_Offer_Fragment newInstance(String param1, String param2) {
        Add_Offer_Fragment fragment = new Add_Offer_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_add_offer, container, false);


        pos_add_offer_et_title = view.findViewById(R.id.pos_add_offer_et_title);
        pos_add_offer_et_offer = view.findViewById(R.id.pos_add_offer_et_offer);
        pos_add_offer_btn_add_offer = view.findViewById(R.id.pos_add_offer_btn_add_offer);

        pos_add_offer_btn_add_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos_add_offer_et_title.getText().equals("") || pos_add_offer_et_offer.getText().equals(""))
                    Toast.makeText(getActivity(),"Something is loses",Toast.LENGTH_LONG).show();
                else {
                    String title = pos_add_offer_et_title.getText().toString();
                    String offer = pos_add_offer_et_offer.getText().toString();
                    Serial_Numbers.getSerial_Number("Advertisements",null,new Serial_Numbers.Serial_NumbersCallBack() {
                        @Override
                        public void getItemFinished(String serial_Number) {
                            Advertisements advertisements = new Advertisements( title, offer);
                            AdvertisementsConnect.setItem(advertisements);
                        }
                    });
                }
            }
        });

        return view;
    }
}