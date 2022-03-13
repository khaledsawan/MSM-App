package com.example.msm.ui.UI_Admin.Revenues;

import android.annotation.SuppressLint;
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


public class Revenues_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "number_of_customer";
    private static final String ARG_PARAM2 = "monthly_revenue";


    private Long number_of_customer;
    private Integer monthly_revenue;


    ImageView r_f_imageview;
    TextView r_f_number_of_customer;
    TextView r_f_monthly_revenue;


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
        open_listener.onNewFragmentOpen("Revenues");
    }

    public Revenues_Fragment() {
        // Required empty public constructor
    }


    public static Revenues_Fragment newInstance(Long param1, Integer param2) {
        Revenues_Fragment fragment = new Revenues_Fragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number_of_customer = getArguments().getLong(ARG_PARAM1);
            monthly_revenue = getArguments().getInt(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_revenues_, container, false);

        r_f_imageview=v.findViewById(R.id.r_f_imageview);
        r_f_monthly_revenue=v.findViewById(R.id.r_f_Monthly_revenue_et);
        r_f_number_of_customer=v.findViewById(R.id.r_f_number_of_customer_et);
        r_f_number_of_customer.setText("Customer count :"+number_of_customer);
        r_f_monthly_revenue.setText("monthly revenuer"+monthly_revenue);

        return v;
    }
}