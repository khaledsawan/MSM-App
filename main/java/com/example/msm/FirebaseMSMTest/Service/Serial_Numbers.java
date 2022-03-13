package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Service.MSMFireBaseMethod;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Serial_Numbers extends MSMFireBaseMethod {

    private String table_Name;


    public interface Serial_NumbersCallBack{
        public void getItemFinished(String serial_Number);

    }

    private static final String SERIAL_NUM_KEY = "serial_Number";

    public static void getSerial_Number(String table_Name1 ,String table_Name2 , Serial_NumbersCallBack callBack){
        if(isConnect){
            if(table_Name2 != null){
//                Requests
                DatabaseReference reference = database.getReference(table_Name1).child(table_Name2);
                Query query = reference.orderByChild(SERIAL_NUM_KEY);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String serial_Number = "0";
                            serial_Number = String.valueOf(snapshot.getChildrenCount()+1);
                            callBack.getItemFinished(serial_Number);
                        }else{
                            callBack.getItemFinished("1");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else{
                DatabaseReference reference = database.getReference(table_Name1);
                Query query = reference.orderByChild(SERIAL_NUM_KEY);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String serial_Number = "0";
                            serial_Number = String.valueOf(snapshot.getChildrenCount()+1);
                            callBack.getItemFinished(serial_Number);
                        }else{
                            callBack.getItemFinished("1");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }

    }
}
