package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CenterConnect extends MSMFireBaseMethod {

    public interface CenterConnectCallBack{
        public void getItemFinished(Center center);

    }

    public interface CenterConnectCallBackAll{
        public void getItemsFinished(ArrayList<Center> center);

    }
    private static final String CENTER_KEY = "e_Mail";

    public static void setItem(Center center) {
        if(isConnect) {
            DatabaseReference myRef = database.getReference("Centers");
            myRef.push().setValue(center);
            Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }




    public static void upData(String e_Mail, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Centers");
            Query query = reference.orderByChild(CENTER_KEY).equalTo(e_Mail);

            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String CustomerKey = dataSnapshot.getKey();
                            reference.child(CustomerKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(context,"Data has been updated successfully", Toast.LENGTH_SHORT);
                                }

                            });
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void delete(String e_Mail) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Centers");
        Query query = reference.orderByChild(CENTER_KEY).equalTo(e_Mail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        dataSnapshot.getRef().removeValue();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getItem(String e_Mail , CenterConnectCallBack callBack) {
        DatabaseReference reference = database.getReference("Centers");
        Query query = reference.orderByChild(CENTER_KEY).equalTo(e_Mail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Center center =  dataSnapshot.getValue(Center.class);
                        callBack.getItemFinished(center);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getAll_lItems( CenterConnectCallBackAll callBack) {
        DatabaseReference reference = database.getReference("Centers");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    ArrayList<Center> centers = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        centers.add(dataSnapshot.getValue(Center.class));
                    }
                    callBack.getItemsFinished(centers);
                }else{
                    callBack.getItemsFinished(new ArrayList<Center>());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
