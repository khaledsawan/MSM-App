package com.example.msm.ui.UI_Customer_Visitor.Recharge_The_Package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.R;

import java.util.ArrayList;

public class RTP_Recycler_View_Adapter extends RecyclerView.Adapter<RTP_Recycler_View_Adapter.RTPViewHolder> {

    public RTP_Recycler_View_Adapter(ArrayList<RTP_Custom_Recycler_Item> RTPCustomRecycler_items, OnItemClickListener listener) {
        this.RTPCustomRecycler_items = RTPCustomRecycler_items;
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(RTP_Custom_Recycler_Item item);
        void onbtnOKClick(RTP_Custom_Recycler_Item item);
    }


    ArrayList<RTP_Custom_Recycler_Item> RTPCustomRecycler_items;
    OnItemClickListener listener;


    class RTPViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Size;
        TextView tv_Price;
        Button btn_Ok;

        RTP_Custom_Recycler_Item item;
        public RTPViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Size = itemView.findViewById(R.id.rtp_custom_recycler_layout_tv_Size);
            tv_Price = itemView.findViewById(R.id.rtp_custom_recycler_layout_tv_Price);
            btn_Ok = itemView.findViewById(R.id.rtp_custom_recycler_layout_btn_Ok);

            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onbtnOKClick(item);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(item);
//                }
//            });
        }

        void bind (RTP_Custom_Recycler_Item item){
            this.item = item;
            tv_Size.setText(item.getSize());
            tv_Price.setText(item.getPrice());
            btn_Ok.setText(item.getOK());
        }
    }

    @NonNull
    @Override
    //  هذه الدالة يتم عرضها فقط عندما يعرض العنصر لاول مرة على الشاشة
    public RTPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rtp_recycler_layout,null,false);
        RTPViewHolder catViewHolder = new RTPViewHolder(view);
        return catViewHolder;
/*  CustomContactItemBinding binding = CustomContactItemBinding.inflate(LayoutInflater.from(parent.getContext()))
        ContactHolder contactHolder = new ContactHolder(binding.getRoot());
        return contactHolder;  */
/*public ContactHolder(@NonNull CustomContactItemBinding itemView) {
            super(itemView.getRoot());
            binding = CustomContactItemBinding.bind(itemView.getRoot());*/

    }


    @Override
    public void onBindViewHolder(@NonNull RTPViewHolder holder, int position) {
        RTP_Custom_Recycler_Item items = RTPCustomRecycler_items.get(position);
        holder.bind(items);
    }

    @Override
    public int getItemCount() {
        return RTPCustomRecycler_items.size();
    }
}
