package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Bill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillConnect extends MSMFireBaseMethod {


    public interface BillsConnectCallBackTotal_Bills {
        public void getItemsFinished(Integer sum);
    }

    public interface BillsConnectCallBackAllServices {
        public void getItemsFinished(ArrayList<Bill> bills);
    }

    interface BillConnectCallBack{
        public void getItemFinished(Bill bill);
    }

    private static final String BILL_KEY ="serial_Number";

    public static void setItem(Bill bill) {
        if(isConnect) {
            Serial_Numbers.getSerial_Number("Bill", null, new Serial_Numbers.Serial_NumbersCallBack() {
                @Override
                public void getItemFinished(String serial_Number) {
                    DatabaseReference myRef = database.getReference("Bill");
                    bill.setSerial_Number(serial_Number);
                    myRef.push().setValue(bill);
                    Toast.makeText(context,"Item added successfully", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }
    public static void getItem(String serial_Number ,BillConnectCallBack callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Bill");
            Query query = reference.orderByChild(BILL_KEY).equalTo(serial_Number);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Bill bill =  dataSnapshot.getValue(Bill.class);
                            callBack.getItemFinished(bill);
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

    public static void getAllBills( BillsConnectCallBackAllServices callBack) {
        if(isConnect){
            DatabaseReference reference = database.getReference("Bill");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Bill> services = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            services.add(dataSnapshot.getValue(Bill.class)) ;
                        }
                        callBack.getItemsFinished(services);
                    }
                    else{
                        callBack.getItemsFinished(new ArrayList<Bill>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
            callBack.getItemsFinished(new ArrayList<Bill>());
        }
    }

//    total bills
public static void getTotal_Bills( BillsConnectCallBackTotal_Bills callBack) {
    if(isConnect){
        DatabaseReference reference = database.getReference("Bill");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Integer sum = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        sum += Integer.parseInt((dataSnapshot.getValue(Bill.class)).getInvoice_Value()) ;
                    }
                    callBack.getItemsFinished(sum);
                }
                else{
                    callBack.getItemsFinished(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }else{
        Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        callBack.getItemsFinished(0);
    }
}
}
