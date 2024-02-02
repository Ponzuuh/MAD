package com.sp.mobileappliancesdirector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    Context context;
    ArrayList<Appliances> appliancesArrayList;

    public DashboardAdapter(Context context, ArrayList<Appliances> appliancesArrayList) {
        this.context = context;
        this.appliancesArrayList = appliancesArrayList;
    }

    @NonNull
    @Override
    public DashboardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.MyViewHolder holder, int position) {

        Appliances appliances = appliancesArrayList.get(position);

        holder.applianceType.setText(appliances.NAME);
        holder.applianceModel.setText(appliances.MODEL);

    }

    @Override
    public int getItemCount() {
        return appliancesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView applianceImage;
        TextView applianceType, applianceModel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            applianceImage = itemView.findViewById(R.id.applianceImage);
            applianceType = itemView.findViewById(R.id.applianceType);
            applianceModel = itemView.findViewById(R.id.applianceModel);
        }
    }
}
