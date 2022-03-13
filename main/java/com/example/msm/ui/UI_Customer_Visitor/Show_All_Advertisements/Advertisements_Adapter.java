package com.example.msm.ui.UI_Customer_Visitor.Show_All_Advertisements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.R;

import java.util.ArrayList;

public class Advertisements_Adapter extends RecyclerView.Adapter<Advertisements_Adapter.advertisementsViewHolder>{


    ArrayList<Advertisements> advertisements ;


    public Advertisements_Adapter(ArrayList<Advertisements> advertisements ) {
        this.advertisements = advertisements;
    }

    class advertisementsViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView subject;
        Advertisements advertisement;
        public advertisementsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_adver_customer_visiter);
            subject = itemView.findViewById(R.id.text_adver_customer_visiter);

        }

//        void bind (Advertisements advertisement){
//            this.advertisement = advertisement;
//            Serial_number.setText(advertisement.getSerial_Number());
//            title.setText(advertisement.getTitle());
//            subject.setText(advertisement.getSubject());
//        }
    }

    @NonNull
    @Override
    //  هذه الدالة يتم عرضها فقط عندما يعرض العنصر لاول مرة على الشاشة
    public advertisementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_advertisement_and_displa_for_customer_and_visiter,null,false);
        advertisementsViewHolder catViewHolder = new advertisementsViewHolder(view);
        return catViewHolder;
/*  CustomContactItemBinding binding = CustomContactItemBinding.inflate(LayoutInflater.from(parent.getContext()))
        ContactHolder contactHolder = new ContactHolder(binding.getRoot());
        return contactHolder;  */
/*public ContactHolder(@NonNull CustomContactItemBinding itemView) {
            super(itemView.getRoot());
            binding = CustomContactItemBinding.bind(itemView.getRoot());*/

    }


    @Override
    public void onBindViewHolder(@NonNull advertisementsViewHolder holder, int position) {
        Advertisements cat = advertisements.get(position);
        holder.title.setText(cat.getTitle());
        holder.subject.setText(cat.getSubject());
    }

    @Override
    public int getItemCount() {
        return advertisements.size();
    }


}
