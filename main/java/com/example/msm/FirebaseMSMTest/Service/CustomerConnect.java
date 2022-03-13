package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerConnect extends MSMFireBaseMethod {

    public interface  CustomersConnectCallBackAllServices{
        public void getItemsFinished(ArrayList<Customer> customer);
    }

    public interface CustomerConnectCallBack{
        public void getItemFinished(Customer customer);

    }

    public interface CustomerConnectCustomerCount{
        public void getItemFinished(Long customerCount);

    }

    private static final String CUSTOMER_KEY = "e_Mail";


    public static void setItem(Customer customer) {

        if(isConnect) {
            DatabaseReference myRef = database.getReference("Customers");
            myRef.push().setValue(customer);
            Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }

    }

    public static void upDate(String e_Mail, HashMap hashMap) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Customers");
            Query query = reference.orderByChild(CUSTOMER_KEY).equalTo(e_Mail);

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
            DatabaseReference reference = database.getReference("Customers");
            Query query = reference.orderByChild(CUSTOMER_KEY).equalTo(e_Mail);

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

    public static void getItem(String e_Mail , CustomerConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Customers");
            Query query = reference.orderByChild(CUSTOMER_KEY).equalTo(e_Mail);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Customer customer =  dataSnapshot.getValue(Customer.class);
                            callBack.getItemFinished(customer);
                        }

                    }else{
                        callBack.getItemFinished(null);
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

    public static void getCustomersCount( CustomerConnectCustomerCount callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Customers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        callBack.getItemFinished(snapshot.getChildrenCount());

                    }else{
                        callBack.getItemFinished(null);
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

    public static void getAllCustomers( CustomersConnectCallBackAllServices callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Customers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Customer> Customer = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Customer.add(dataSnapshot.getValue(Customer.class)) ;
                        }
                        callBack.getItemsFinished(Customer);
                    }
                    else{
                        callBack.getItemsFinished(new ArrayList<Customer>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
            callBack.getItemsFinished(new ArrayList<Customer>());
        }
    }
}
