package com.example.msm.ui.UI_Admin.Advertisements;

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
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.FirebaseMSMTest.Service.AdvertisementsConnect;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;

import java.util.ArrayList;

public class Offers_And_Ads_Fragment extends Fragment {

    RecyclerView rv;
    Button Add_offers_btn;
    Button add_btn;
    EditText offer_et;
    EditText title_et;
    LinearLayout linearlayout_adding;



    private static final String ARG_PARAM1 = "ArrayList";

    public static ArrayList<Advertisements> Advertisements=new ArrayList<>();

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
        open_listener.onNewFragmentOpen("Advertisements");
    }
    public Offers_And_Ads_Fragment() {
        // Required empty public constructor
    }
    public void removeItem(int position) {
        AdvertisementsConnect.delete(Advertisements.get(position).getSerial_Number());
        /********static*******/
        //  Advertisements.remove(position);
        // adapter.notifyItemRemoved(position);
    }
    public static Offers_And_Ads_Fragment newInstance( ArrayList<Advertisements> Advertisements) {
        Offers_And_Ads_Fragment fragment = new Offers_And_Ads_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, Advertisements);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            Advertisements = (ArrayList<Advertisements>) bundle.getSerializable(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  v=inflater.inflate(R.layout.fragment_offers__and__ads, container, false);
        rv =v.findViewById(R.id.O_A_A_recyclerview);
        add_btn=v.findViewById(R.id.o_a_a_f_add_btn);
        Add_offers_btn=v.findViewById(R.id.O_A_A_Add_offers_btn);
        title_et=v.findViewById(R.id.o_a_a_f_title_et);
        offer_et=v.findViewById(R.id.o_a_a_f_offer_et);
        linearlayout_adding=v.findViewById(R.id.o_a_a_f_linearlayout_adding);
        final Recyclerview_Adapter_Advertisement_And_Display[] adapter = {new Recyclerview_Adapter_Advertisement_And_Display(Advertisements)};
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
                String title=title_et.getText().toString();
                String offer=offer_et.getText().toString();
                Advertisements ad=new Advertisements(title,offer);
                AdvertisementsConnect.setItem(ad);
                title_et.setText(null);
                offer_et.setText(null);
                /********static*******/
                 // adapter[0].notifyDataSetChanged();
            }
        });
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        adapter[0].setOnItemClickListener(new Recyclerview_Adapter_Advertisement_And_Display.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
            @Override
            public void onDeleteClick(String Serial_number) {
                Toast.makeText(getActivity(), Serial_number, Toast.LENGTH_SHORT).show();
                AdvertisementsConnect.delete(Serial_number);
                AdvertisementsConnect.getAllAdvertisements(new AdvertisementsConnect.AdvertisementsConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<com.example.msm.FirebaseMSMTest.Modles.Advertisements> advertisement) {
                        adapter[0].notifyDataSetChanged();
                        rv.setAdapter(adapter[0]);
                        adapter[0].notifyDataSetChanged();
                    }
                });
            }
        });
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter[0]);
        return v;
    }
}