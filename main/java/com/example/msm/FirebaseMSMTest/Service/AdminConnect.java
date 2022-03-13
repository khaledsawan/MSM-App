package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Admin;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminConnect extends MSMFireBaseMethod {

    public interface AdminConnectCallBack{
        public void getItemFinished(Admin admin);

    }

    private static final String ADMIN_KEY ="e_Mail";

    public static void setItem(Admin admin) {
        if(isConnect) {
            DatabaseReference myRef = database.getReference("Admins");
            myRef.push().setValue(admin);
            Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void upData(String e_Mail, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Admins");
            Query query = reference.orderByChild(ADMIN_KEY).equalTo(e_Mail);

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
        if(isConnect){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Admins");
            Query query = reference.orderByChild(ADMIN_KEY).equalTo(e_Mail);

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

    public static void getItem(String e_Mail , AdminConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Admins");
            Query query = reference.orderByChild(ADMIN_KEY).equalTo(e_Mail);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Admin admin =  dataSnapshot.getValue(Admin.class);
                            callBack.getItemFinished(admin);
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

//    public static void check_Account(String E_Mail , String password, CustomerConnectCallBack callBack) {
//        getItem(E_Mail, new CustomerConnectCallBack() {
//            @Override
//            public void getItemFinished(Customer customer) {
//                if(customer.getPassword() == password){
//                    callBack.accountChecked(true);
//                    callBack.getItemFinished(customer);
//                }else{
//                    callBack.accountChecked(false);
//                    callBack.getItemFinished(customer);
//                }
//            }
//
//            @Override
//            public void accountChecked(Boolean isExist) {
//
//            }
//        });
//
//    }
}
