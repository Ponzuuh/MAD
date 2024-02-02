package com.sp.mobileappliancesdirector;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Appliances> appliancesArrayList;
    DashboardAdapter dashboardAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        db = FirebaseFirestore.getInstance();
        appliancesArrayList = new ArrayList<>();
        dashboardAdapter = new DashboardAdapter(requireContext(), appliancesArrayList);

        recyclerView.setAdapter(dashboardAdapter);

        EventChangeListener();


        return rootView;
    }

    private void EventChangeListener() {

        db.collection("TEST APPLIANCES").orderBy("NAME", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){
                                appliancesArrayList.add(dc.getDocument().toObject(Appliances.class));
                            }

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                        dashboardAdapter.notifyDataSetChanged();
                        Log.d("RecyclerView", "Data size: " + appliancesArrayList.size());
                    }
                });

    }
}