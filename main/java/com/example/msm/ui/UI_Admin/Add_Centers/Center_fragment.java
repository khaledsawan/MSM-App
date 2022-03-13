package com.example.msm.ui.UI_Admin.Add_Centers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.FirebaseMSMTest.Service.CenterConnect;
import com.example.msm.R;
import com.example.msm.ui.Common.Recyclerview_Adapter_centrs;

import java.util.ArrayList;


public class Center_fragment extends Fragment {

    RecyclerView rv;
    Button Add_offers_btn;
    Button add_btn;
    EditText email_et;
    EditText password_et;
    EditText location_et;
    LinearLayout linearlayout_adding;
    /////////////////

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
        open_listener.onNewFragmentOpen("Centers");
    }


    private static final String ARG_PARAM1 = "ArrayList";

    ArrayList<Center> Center=new ArrayList<>();



    public Center_fragment() {
        // Required empty public constructor
    }

    public static Center_fragment newInstance(ArrayList<Center> Center) {
        Center_fragment fragment = new Center_fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, Center);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            Center = (ArrayList<Center>) bundle.getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  v=inflater.inflate(R.layout.fragment_center_fragment, null, false);
        rv =v.findViewById(R.id.center_fragment_recyclerview);
        add_btn=v.findViewById(R.id.center_fragment_add_btn);
        Add_offers_btn=v.findViewById(R.id.center_fragment_Add_center_btn);
        email_et=v.findViewById(R.id.center_fragment_email_et);
        password_et=v.findViewById(R.id.center_fragment_password_et);
        location_et=v.findViewById(R.id.center_fragment_location_et);
        linearlayout_adding=v.findViewById(R.id.center_fragment_linearlayout_adding);

        Recyclerview_Adapter_centrs adapter=new Recyclerview_Adapter_centrs(Center);

        ///////هاد زر وقت تطغطو بيختفي وبيطلع زرار اضافة انا خافيهن

        Add_offers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_offers_btn.setVisibility(View.GONE);
                linearlayout_adding.setVisibility(View.VISIBLE);
            }
        });

        ///////////////هاد زر وقت ينضغط بيبعت محتويات ايدت تيكست وبحطن بالمصفوفة
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_string=email_et.getText().toString();
                String password_string=password_et.getText().toString();
                String location_string=location_et.getText().toString();
                Center center_add=new Center(email_string,password_string,location_string);
                CenterConnect.setItem(center_add);
                email_et.setText(null);
                password_et.setText(null);
                location_et.setText(null);

                CenterConnect.setItem(new Center(email_string,password_string,location_string));
                /********static*******/
                //  adapter.notifyDataSetChanged();
//                Toast.makeText(getActivity(), "center fragment ", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new Recyclerview_Adapter_centrs.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {

            }


        });


        return v;
    }
}