package com.example.msm.ui.UI_Admin.Payment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;


public class Payment_Fragment extends Fragment {


    ImageView PaymentImage;
    TextView Payment_Cost;
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

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
        open_listener.onNewFragmentOpen("Payment");
    }

    public Payment_Fragment() {
        // Required empty public constructor
    }
    public static Payment_Fragment newInstance(String payments) {
        Payment_Fragment fragment = new Payment_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, payments);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_payment_, container, false);
        PaymentImage=v.findViewById(R.id.P_F_imageview);
        Payment_Cost=v.findViewById(R.id.P_F_payments_cost);
        Payment_Cost.setText("payments:"+mParam1);
        return v;
    }
}