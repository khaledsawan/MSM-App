package com.example.msm.ui.UI_Customer_Visitor.Change_Speed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.R;

import java.util.ArrayList;

public class CS_Recycler_View_Adapter extends RecyclerView.Adapter<CS_Recycler_View_Adapter.CSViewHolder> {




    public interface OnItemClickListener {
        void onItemClick(CS_Custom_Recycler_Item items);
        void onRadioButtonClick(String speedselected);
    }

    ArrayList<CS_Custom_Recycler_Item> items;
    OnItemClickListener listener;
    ArrayList<RadioButton> radioButtons;

    public CS_Recycler_View_Adapter(ArrayList<CS_Custom_Recycler_Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.radioButtons = new ArrayList<>();
    }

    class CSViewHolder extends RecyclerView.ViewHolder{
        TextView tv_SpeedTitle;
        TextView iv_Text;
        RadioButton radioButton;

        CS_Custom_Recycler_Item items;
        public CSViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_SpeedTitle = itemView.findViewById(R.id.cs_Custom_Recycler_Layout_SpeedTitle);
            iv_Text = itemView.findViewById(R.id.cs_Custom_Recycler_Layout_Text);
            radioButton = itemView.findViewById(R.id.cs_Custom_Recycler_Layout_rb);
            radioButtons.add(radioButton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(RadioButton r : radioButtons){
                        if(r == radioButton){
                            radioButton.setChecked(true);
                            listener.onRadioButtonClick(items.getText());
                        }else
                            r.setChecked(false);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(items);
                }
            });
        }

        void bind (CS_Custom_Recycler_Item items){
            this.items = items;
            tv_SpeedTitle.setText(items.getText()+"m");
            iv_Text.setText(items.getSpeed_Titel());
        }
    }
    @NonNull
    @Override
    public CSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cs_recycler_layout,null,false);
        CSViewHolder holder= new CSViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CSViewHolder holder, int position) {
        CS_Custom_Recycler_Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
