package com.example.msm.ui.UI_Admin.Services;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.FirebaseMSMTest.Service.ServicesConnect;
import com.example.msm.R;

import java.util.ArrayList;


public class Services_fragment extends Fragment {
    RecyclerView rv;
    EditText title_et;
    EditText cost_et;
    EditText subject_et;
    Button add_new_item1;
    Button add_new_item2;
    LinearLayout LinearLayout_title_cost_serialnumber;
    LinearLayout LinearLayout_subject;


    private static final String ARG_PARAM1 = "ArrayList";
    ArrayList<com.example.msm.FirebaseMSMTest.Modles.Services> Services=new ArrayList<>();




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
        open_listener.onNewFragmentOpen("Services");
    }


    public Services_fragment() {
        // Required empty public constructor
    }



    public void removeItem(int position) {
        Services.remove(position);
//        adapter.notifyItemRemoved(position);
    }

    public static Services_fragment newInstance( ArrayList<Services> Services) {
        Services_fragment fragment = new Services_fragment();
        Bundle args = new Bundle();
        args.putSerializable(Services_fragment.ARG_PARAM1, Services);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Services = (ArrayList<Services>) bundle.getSerializable(Services_fragment.ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_services, container, false);

        rv=v.findViewById(R.id.S_F_recyclerview);
        title_et=v.findViewById(R.id.S_F_title_et);
        cost_et=v.findViewById(R.id.S_F_cost_et);
        subject_et=v.findViewById(R.id.S_F_subject_et);
        LinearLayout_title_cost_serialnumber=v.findViewById(R.id.S_F_LinearLayout_title_cost_serial);
        LinearLayout_subject=v.findViewById(R.id.S_F_LinearLayout_subject);
        add_new_item1=v.findViewById(R.id.S_F_add_new_item1_et);
        add_new_item2=v.findViewById(R.id.S_F_add_new_item2_et);


        add_new_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_new_item2.setVisibility(View.GONE);
                add_new_item1.setVisibility(View.VISIBLE);
                LinearLayout_title_cost_serialnumber.setVisibility(View.VISIBLE);
                LinearLayout_subject.setVisibility(View.VISIBLE);

            }
        });
        add_new_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title= title_et.getText().toString();
                String cost= cost_et.getText().toString();
                String subject= subject_et.getText().toString();
                ServicesConnect.setItem(new Services(title,subject,cost));
            }
        });
        Recyclerview_Adapter_Services adapter=new Recyclerview_Adapter_Services(Services);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new Recyclerview_Adapter_Services.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {

                removeItem(position);
            }
        });

        return v;
    }
}