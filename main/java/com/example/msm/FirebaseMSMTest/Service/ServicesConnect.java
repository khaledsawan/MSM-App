package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.FirebaseMSMTest.Service.MSMFireBaseMethod;
import com.example.msm.FirebaseMSMTest.Service.Serial_Numbers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ServicesConnect extends MSMFireBaseMethod {

    public interface ServicesConnectCallBack{
        public void getItemFinished(Services services);
    }
    public interface ServicesConnectCallBackAllServices{
        public void getItemsFinished(ArrayList<Services> services);
    }

    private static final String SERVICE_KEY = "serial_Number";

    public static void setItem(Services services) {
        if(isConnect) {
            Serial_Numbers.getSerial_Number("Services", null, new Serial_Numbers.Serial_NumbersCallBack() {
                @Override
                public void getItemFinished(String serial_Number) {
                    DatabaseReference myRef = database.getReference("Services");
                    services.setSerial_Number(serial_Number);
                    myRef.push().setValue(services);
                    Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void upData(String services_ID, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Services");
            Query query = reference.orderByChild(SERVICE_KEY).equalTo(services_ID);

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

    public static void delete(String services_ID) {
        if(isConnect){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Services");
            Query query = reference.orderByChild(SERVICE_KEY).equalTo(services_ID);

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

    public static void getItem(String services_ID , ServicesConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Services");
            Query query = reference.orderByChild(SERVICE_KEY).equalTo(services_ID);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Services services =  dataSnapshot.getValue(Services.class);
                            callBack.getItemFinished(services);
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
    public static void getAllServices( ServicesConnectCallBackAllServices callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Services");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Services> services = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            services.add(dataSnapshot.getValue(Services.class)) ;
                        }
                        callBack.getItemsFinished(services);
                    }
                    else{
                        callBack.getItemsFinished(new ArrayList<Services>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
            callBack.getItemsFinished(new ArrayList<Services>());
        }
    }
}
