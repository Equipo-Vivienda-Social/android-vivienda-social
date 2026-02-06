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
import com.example.viviendasocial.adapter.ApplicantAdapter;
import com.example.viviendasocial.contract.ApplicantListContract;
import com.example.viviendasocial.domain.Applicant;

import java.util.List;

public class ApplicantListView extends AppCompatActivity implements ApplicantListContract.View {

    private List<Applicant> applicantList;
    private ApplicantListContract.Presenter presenter;
    private ApplicantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_list_view);

        RecyclerView recyclerView = findViewById(R.id.applicant_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ApplicantAdapter(this, applicantList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadApplicants();
    }

    @Override
    public void showApplicants(List<Applicant> applicants) {
        applicantList.clear();
        applicantList.addAll(applicants);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}