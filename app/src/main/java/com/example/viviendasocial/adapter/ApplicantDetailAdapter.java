package com.example.viviendasocial.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.domain.Applicant;

public class ApplicantDetailAdapter extends RecyclerView.Adapter<ApplicantDetailAdapter.ViewHolder> {

    public Applicant applicant;

    public ApplicantDetailAdapter(Applicant applicant) {
        this.applicant = applicant;
    }

    @NonNull
    @Override
    public ApplicantDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_applicant_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantDetailAdapter.ViewHolder holder, int position) {
        holder.itemDetailName.setText(applicant.getName() + " " + applicant.getSurname());
        holder.itemDetailDni.setText("ID: " + applicant.getDni());
        holder.itemDetailBirthDate.setText(String.valueOf("Date of birth: " + applicant.getBirthDate()));
        holder.itemDetailSalary.setText(String.valueOf("Salary: " + applicant.getSalary() + " €"));
        holder.itemDetailFamilyMembers.setText(String.valueOf("Number of family members: " + applicant.getFamilyMembers()));
        holder.itemDetailEmployed.setText(applicant.isEmployed()
                ? "Empleado 🟢"
                : "Desempleado 🔴"
        );
    }

    @Override
    public int getItemCount() {
        return applicant == null ? 0 : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemDetailName, itemDetailDni, itemDetailBirthDate, itemDetailSalary, itemDetailFamilyMembers, itemDetailEmployed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             itemDetailName = itemView.findViewById(R.id.applicant_detail_name);
             itemDetailDni = itemView.findViewById(R.id.applicant_detail_dni);
             itemDetailBirthDate = itemView.findViewById(R.id.applicant_detail_birthdate);
             itemDetailSalary = itemView.findViewById(R.id.applicant_detail_salary);
             itemDetailFamilyMembers = itemView.findViewById(R.id.applicant_detail_familyMembers);
             itemDetailEmployed = itemView.findViewById(R.id.applicant_detail_employed);
        }
    }


}
