package com.example.viviendasocial.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}