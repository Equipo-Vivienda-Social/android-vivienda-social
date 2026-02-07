package com.example.viviendasocial.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.adapter.ApplicantDetailAdapter;
import com.example.viviendasocial.contract.ApplicantDetailContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.presenter.ApplicantDetailPresenter;

public class ApplicantDetailView extends AppCompatActivity implements ApplicantDetailContract.View {

    private ApplicantDetailContract.Presenter presenter;
    private ApplicantDetailAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_detail_view);

        recyclerView = findViewById(R.id.applicant_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ApplicantDetailPresenter(this);

        long applicantId = getIntent().getLongExtra("applicant_id", -1);
        presenter.loadApplicant(applicantId);
    }

    @Override
    public void showApplicant(Applicant applicant) {
        adapter = new ApplicantDetailAdapter(applicant);
        recyclerView.setAdapter(adapter);
    }

    public void deleteApplicant(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Are you sure you want to delete this applicant?")
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long applicant_id = getIntent().getLongExtra("applicant_id", -1);
                                presenter.deleteApplicant(applicant_id);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
        alert.create().show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToApplicantList() {
        Intent intent = new Intent(this, ApplicantListView.class);
        startActivity(intent);
        finish();
    }
}