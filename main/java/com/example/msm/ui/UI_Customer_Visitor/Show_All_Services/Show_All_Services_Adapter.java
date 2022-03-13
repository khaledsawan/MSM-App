package com.example.msm.ui.UI_Customer_Visitor.Show_All_Services;



import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.msm.FirebaseMSMTest.Modles.Services;
        import com.example.msm.R;

        import java.util.ArrayList;


public class Show_All_Services_Adapter extends RecyclerView.Adapter<Show_All_Services_Adapter.SASViewHolder>{
    ArrayList<Services> servicesArrayList;

    public Show_All_Services_Adapter(ArrayList<Services> servicesArrayList) {
        this.servicesArrayList = servicesArrayList;
    }

    //  holder class for recycler view
    class SASViewHolder extends RecyclerView.ViewHolder{

        TextView custom_show_all_tv_ID_Text;
        TextView custom_show_all_tv_Title_Text;
        TextView custom_show_all_tv_Subject_Text;
        TextView custom_show_all_tv_Cost_Text;
        Services item;

        public SASViewHolder(@NonNull View itemView) {
            super(itemView);
            custom_show_all_tv_ID_Text = itemView.findViewById(R.id.custom_show_all_tv_ID_Text);
            custom_show_all_tv_Title_Text = itemView.findViewById(R.id.custom_show_all_tv_Title_Text);
            custom_show_all_tv_Subject_Text = itemView.findViewById(R.id.custom_show_all_tv_Subject_Text);
            custom_show_all_tv_Cost_Text = itemView.findViewById(R.id.custom_show_all_tv_Cost_Text);
        }

        void bind (Services item){
            this.item = item;
            custom_show_all_tv_ID_Text.setText("Serial: "+item.getSerial_Number());
            custom_show_all_tv_Title_Text.setText("Title: "+item.getTitle());
            custom_show_all_tv_Subject_Text.setText("Subject: "+item.getSubject());
            custom_show_all_tv_Cost_Text.setText("Cost: "+item.getCost());
        }
    }
    @NonNull
    @Override
    public SASViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_show_all_services_recycler_layout,null,false);
        SASViewHolder rtpViewHolder = new SASViewHolder(view);
        return rtpViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull SASViewHolder holder, int position) {
        Services items = servicesArrayList.get(position);
        holder.bind(items);
    }
    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }

}

