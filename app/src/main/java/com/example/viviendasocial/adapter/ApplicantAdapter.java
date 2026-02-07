package com.example.viviendasocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.view.ApplicantDetailView;

import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantHolder> {

    private Context context;
    private List<Applicant> applicantList;

    public ApplicantAdapter(Context context, List<Applicant> applicantList) {
        this.context = context;
        this.applicantList = applicantList;
    }

    @NonNull
    @Override
    public ApplicantAdapter.ApplicantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.applicant_item, parent, false);
        return new ApplicantHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantAdapter.ApplicantHolder holder, int position) {

        Applicant applicant = applicantList.get(position);

        holder.itemApplicantName.setText(applicant.getName() + " " + applicant.getSurname());
        holder.itemApplicantDni.setText("ID: " + applicant.getDni());
        holder.itemApplicantSalary.setText(String.valueOf("Salary: " + applicant.getSalary() + " €"));
        holder.itemApplicantEmployed.setText(applicant.isEmployed()
                ? "Employed"
                : "Unemployed"
        );

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ApplicantDetailView.class);
            Log.d("ApplicantAdapter", "Seleccionado applicant ID: " + applicant.getId());
            intent.putExtra("applicant_id", applicant.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return applicantList.size();
    }

    public class ApplicantHolder extends RecyclerView.ViewHolder {

        private TextView itemApplicantName;
        private TextView itemApplicantDni;
        private TextView itemApplicantSalary;
        private TextView itemApplicantEmployed;

        public ApplicantHolder(@NonNull View itemView) {
            super(itemView);

            itemApplicantName = itemView.findViewById(R.id.item_applicant_name);
            itemApplicantDni = itemView.findViewById(R.id.item_applicant_dni);
            itemApplicantSalary = itemView.findViewById(R.id.item_applicant_salary);
            itemApplicantEmployed = itemView.findViewById(R.id.item_applicant_employed);
        }
    }

}
