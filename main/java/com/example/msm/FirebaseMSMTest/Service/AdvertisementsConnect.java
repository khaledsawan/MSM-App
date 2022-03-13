package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdvertisementsConnect extends MSMFireBaseMethod {

    public interface AdvertisementsConnectCallBackAllServices {
        public void getItemsFinished(ArrayList<Advertisements> advertisement);
    }

    interface AdvertisementConnectCallBack{
        public void getItemFinished(Advertisements advertisement);
    }

    private static final String ADVERTISEMENTS_KEY = "serial_Number";

    public static void setItem(Advertisements advertisements) {

        if(isConnect) {
            Serial_Numbers.getSerial_Number("Advertisements", null, new Serial_Numbers.Serial_NumbersCallBack() {
                @Override
                public void getItemFinished(String serial_Number) {
                    DatabaseReference myRef = database.getReference("Advertisements");
                    advertisements.setSerial_Number(serial_Number);
                    myRef.push().setValue(advertisements);
                    Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }

    }



    public static void upData(String advertisement_ID, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Advertisements");
            Query query = reference.orderByChild(ADVERTISEMENTS_KEY).equalTo(advertisement_ID);

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

    public static void delete(String advertisement_ID) {
        if(isConnect){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Advertisements");
            Query query = reference.orderByChild(ADVERTISEMENTS_KEY).equalTo(advertisement_ID);

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

    public static void getItem(String advertisement_ID , AdvertisementConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Advertisements");
            Query query = reference.orderByChild(ADVERTISEMENTS_KEY).equalTo(advertisement_ID);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Advertisements advertisements =  dataSnapshot.getValue(Advertisements.class);
                            callBack.getItemFinished(advertisements);
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
//
//    public static void getSerial_Number(Serial_Numbers.Serial_NumbersCallBack callBack) {
//        if (isConnect) {
//            DatabaseReference reference = database.getReference("Advertisements");
//            Query query = reference.orderByChild("serial_Number");
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        String serial_Number = "0";
//                        HashMap map = new HashMap();
//
//                        serial_Number = String.valueOf(snapshot.getChildrenCount() + 1);
//
////                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////                        dataSnapshot.getChildrenCount();
////                        map = (HashMap) dataSnapshot.getValue();
////                        serial_Number = (Integer.valueOf((String) map.get("serial_Number"))+1)+"";
////                    }
//
//                        callBack.getItemFinished(serial_Number);
//                    } else {
//                        callBack.getItemFinished("1");
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }else{
//            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
//        }
//    }

    public static void getAllAdvertisements( AdvertisementsConnectCallBackAllServices callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Advertisements");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Advertisements> services = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            services.add(dataSnapshot.getValue(Advertisements.class)) ;
                        }
                        callBack.getItemsFinished(services);
                    }
                    else{
                        callBack.getItemsFinished(new ArrayList<Advertisements>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
            callBack.getItemsFinished(new ArrayList<Advertisements>());
        }
    }
}
