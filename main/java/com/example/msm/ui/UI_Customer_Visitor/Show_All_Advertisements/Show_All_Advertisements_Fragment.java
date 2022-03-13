package com.example.msm.ui.UI_Customer_Visitor.Show_All_Advertisements;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;

import java.util.ArrayList;


public class Show_All_Advertisements_Fragment extends Fragment {


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


    public Show_All_Advertisements_Fragment() {
        // Required empty public constructor
    }

    ArrayList<Advertisements> advertisements;
    RecyclerView show_rv;
    public static Show_All_Advertisements_Fragment newInstance(ArrayList<Advertisements> advertisements) {
        Show_All_Advertisements_Fragment fragment = new Show_All_Advertisements_Fragment();
        Bundle args = new Bundle();
        args.putSerializable("Key",advertisements);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            advertisements = (ArrayList<Advertisements>) getArguments().getSerializable("Key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show__all__advertisements_, container, false);
        show_rv = view.findViewById(R.id.show_rv);

        Advertisements_Adapter adapter = new Advertisements_Adapter(advertisements);
//        Toast.makeText(getActivity(), item.getSerial_Number(), Toast.LENGTH_SHORT).show();
//
        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        show_rv.setHasFixedSize(true);
        show_rv.setLayoutManager(lm);
        show_rv.setAdapter(adapter);
        return view;
    }
}
//<ImageView
//                android:id="@+id/ic_delete_forever"
//                        android:layout_width="0dp"
//                        android:layout_height="wrap_content"
//                        android:layout_weight="1"
//                        android:contentDescription="@string/todo"
//                        app:srcCompat="@android:drawable/ic_menu_delete" />