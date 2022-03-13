package com.example.msm.FirebaseMSMTest.Service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public abstract class MSMFireBaseMethod {

    protected static boolean  isConnect;
    protected static Context context ;
    protected static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void setContext(Context context){
        MSMFireBaseMethod.context = context;

    }


    public static void Check_Connect() {

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
//                    Log.d("TAG", "connected");
                    isConnect = true;
//                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();

                } else {

                    connectedRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean connected = snapshot.getValue(Boolean.class);
                            if(connected){
//                                Log.d("TAG", "connected");
                                isConnect = true;
//                                Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
                            }else{
//                                Log.d("TAG", "not connected");
                                isConnect = false;
//                                Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.d("TAG", "not connected");
                            isConnect = false;
//                            Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("TAG", "Listener was cancelled");
                isConnect = false;
//                Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
