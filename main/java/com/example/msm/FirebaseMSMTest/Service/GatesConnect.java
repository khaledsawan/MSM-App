package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Gates;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class GatesConnect extends MSMFireBaseMethod {


    public interface GateConnectCallBack{
        public void getItemFinished(Gates gate);
    }

    private static final String GATE_KEY = "area_Code";

    public static void setItem(Gates gates) {
        if(isConnect) {
            DatabaseReference myRef = database.getReference("Gates");
            myRef.push().setValue(gates);
            Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void upData(String area_Code, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Gates");
            Query query = reference.orderByChild(GATE_KEY).equalTo(area_Code);

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

    public static void delete(String area_Code) {
        if(isConnect){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Gates");
            Query query = reference.orderByChild(GATE_KEY).equalTo(area_Code);

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
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }

    }

    public static void getItem(String area_Code , GateConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Gates");
            Query query = reference.orderByChild(GATE_KEY).equalTo(area_Code);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Gates gates =  dataSnapshot.getValue(Gates.class);
                            callBack.getItemFinished(gates);

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
}
