package com.example.msm.FirebaseMSMTest.Service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestConnect extends MSMFireBaseMethod {

    public interface RequestConnectCallBack{
        public void getItemFinished(Request request);
    }
    public interface AllRequestsCallBack {
        public void getItemsFinished(ArrayList<Request> requests);
    }

    private static final String SERIAL_NUMBER = "serial_Number";
    /************         ChangeSpeed         ************/
    public static void setChangeSpeedRequest(Request request) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("change_Speed");
            Query query = reference.orderByChild("e_Mail").equalTo(request.getE_Mail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String RequestKey = dataSnapshot.getKey();
                            HashMap hashMap = new HashMap();
                            hashMap.put("message",request.getMessage());
                            reference.child(RequestKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(context,"Data has been updated successfully", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }else{
                        Serial_Numbers.getSerial_Number("Requests","change_Speed", new Serial_Numbers.Serial_NumbersCallBack() {
                            @Override
                            public void getItemFinished(String serial_Number) {
                                request.setSerial_Number(serial_Number);
                                reference.push().setValue(request);
                                Toast.makeText(context,"Request sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
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

    public static void getChangeSpeedRequests(AllRequestsCallBack callBack) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("change_Speed");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Request> requests = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            requests.add(dataSnapshot.getValue(Request.class));
                        }
                        callBack.getItemsFinished(requests);
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

    public static void responseOnChangeSpeedRequest(String serial_Number , boolean response){
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("change_Speed");
            Query query = reference.orderByChild("serial_Number").equalTo(serial_Number);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Request request = dataSnapshot.getValue(Request.class);
                            if(response == true){
                                HashMap hashMap = new HashMap();
                                hashMap.put("internet_Speed",Integer.parseInt(request.getMessage()));
                                CustomerConnect.upDate(request.getE_Mail(),hashMap);
                                dataSnapshot.getRef().removeValue();
                            }else{
                                dataSnapshot.getRef().removeValue();
                            }
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

    /************         support_Request         ************/
    public static void setMessageToCenterRequest(Request request) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("support_Request");
            Serial_Numbers.getSerial_Number("Requests","support_Request", new Serial_Numbers.Serial_NumbersCallBack() {
                @Override
                public void getItemFinished(String serial_Number) {
                    request.setSerial_Number(serial_Number);
                    reference.push().setValue(request);
                    Toast.makeText(context,"Request sent successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getMessageFromVisitorCustomerRequest(AllRequestsCallBack callBack) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("support_Request");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Request> requests = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            requests.add(dataSnapshot.getValue(Request.class));
                        }
                        callBack.getItemsFinished(requests);
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

    public static void responseOnMessageFromVisitorCustomerRequest(String serial_Number , boolean response){
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("support_Request");
            Query query = reference.orderByChild("serial_Number").equalTo(serial_Number);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            Request request = dataSnapshot.getValue(Request.class);
                            if(response == true){
//                                HashMap hashMap = new HashMap();
//                                hashMap.put("internet_Speed",request.getMessage());
//                                CustomerConnect.upDate(request.getE_Mail(),hashMap);
                                dataSnapshot.getRef().removeValue();
                            }else{
                                dataSnapshot.getRef().removeValue();
                            }
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

    /************         admin_Request         ************/
    public static void setMessageToAdminRequest(Request request) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("admin_Request");
            Serial_Numbers.getSerial_Number("Requests","admin_Request", new Serial_Numbers.Serial_NumbersCallBack() {
                @Override
                public void getItemFinished(String serial_Number) {
                    request.setSerial_Number(serial_Number);
                    reference.push().setValue(request);
                    Toast.makeText(context,"Request sent successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(context,"Your are not connected with Firebase", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getMessageFromCenterAdminRequest(AllRequestsCallBack callBack) {
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("admin_Request");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        ArrayList<Request> requests = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            requests.add(dataSnapshot.getValue(Request.class));
                        }
                        callBack.getItemsFinished(requests);
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

    public static void responseOnMessageFromCenterAdminRequest(String serial_Number , boolean response){
        if(isConnect) {
            DatabaseReference reference = database.getReference("Requests").child("admin_Request");
            Query query = reference.orderByChild("serial_Number").equalTo(serial_Number);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            Request request = dataSnapshot.getValue(Request.class);
                            if(response == true){
//                                HashMap hashMap = new HashMap();
//                                hashMap.put("internet_Speed",request.getMessage());
//                                CustomerConnect.upDate(request.getE_Mail(),hashMap);
                                dataSnapshot.getRef().removeValue();
                            }else{
                                dataSnapshot.getRef().removeValue();
                            }
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
