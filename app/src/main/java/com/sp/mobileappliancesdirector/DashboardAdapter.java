package com.sp.mobileappliancesdirector;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Appliances appliances = appliancesArrayList.get(position); // Retrieve the Appliances object at the specified position
        if (appliances != null) {
            holder.applianceType.setText(appliances.NAME);
            holder.applianceModel.setText(appliances.MODEL);
            Log.d("Image URL", appliances.IMAGE_URL);
            if (holder.applianceImage != null && context != null && appliances.IMAGE_URL != null) {
                Picasso.with(context).load(appliances.IMAGE_URL).into(holder.applianceImage);
            } else {
                Log.e("DashboardAdapter", "One of the views or context is null");
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    if (appliances.NAME != null && appliances.MODEL != null && appliances.IMAGE_URL != null) {
                        intent.putExtra("APPLIANCE_TYPE", appliances.NAME);
                        intent.putExtra("APPLIANCE_MODEL", appliances.MODEL);
                        intent.putExtra("APPLIANCES_IMAGE_URL", appliances.IMAGE_URL);
                        Log.d("DashboardAdapter", "Appliance Type: " + appliances.NAME);
                        Log.d("DashboardAdapter", "Appliance Model: " + appliances.MODEL);
                        Log.d("DashboardAdapter", "Appliance Image URL: " + appliances.IMAGE_URL);
                        context.startActivity(intent);
                    } else {
                        Log.e("DashboardAdapter", "One of the appliance attributes is null");
                    }
                }
            });
        } else {
            Log.e("DashboardAdapter", "Appliances object is null");
        }
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
