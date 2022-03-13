package com.example.msm.ui.UI_Admin.Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Customers_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Customers_List_Fragment extends Fragment {

    RecyclerView rv;

    private static final String ARG_PARAM1 = "ArrayList";
    private static final String ARG_PARAM2 = "C";
    ArrayList<com.example.msm.FirebaseMSMTest.Modles.Customer> Customer=new ArrayList<>();

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
        open_listener.onNewFragmentOpen("All Customer");
    }

    public Customers_List_Fragment() {
        // Required empty public constructor
    }

    public static Customers_List_Fragment newInstance( ArrayList<Customer> Customer) {
        Customers_List_Fragment fragment = new Customers_List_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, Customer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Customer = (ArrayList<Customer>) bundle.getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_customers__list_, container, false);
        rv=v.findViewById(R.id.C_F_L_Recyclerview);


        Recyclerview_Adapter_Customer_list adapter=new Recyclerview_Adapter_Customer_list(Customer);

        adapter.setOnItemClickListener(new Recyclerview_Adapter_Customer_list.OnItemClickListener() {
            @Override
            public void onItemClick(String position) {
                Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                Customer_Fragment Customer_Fragment = new Customer_Fragment().newInstance(position);
                Customer_Fragment.getArguments().putString("w",position);
                fr.replace(R.id.nav_host_fragment,Customer_Fragment);
                fr.addToBackStack(null);
                fr.commit();
            }

        });

        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        return v;
    }
}