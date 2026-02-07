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
import com.example.viviendasocial.adapter.DwellingDetailAdapter;
import com.example.viviendasocial.contract.DwellingDetailListContract;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.presenter.ApplicantDetailPresenter;
import com.example.viviendasocial.presenter.DwellingDetailViewPresenter;

public class DwellingDetailView extends AppCompatActivity implements DwellingDetailListContract.View{

	private RecyclerView recyclerView;
	private DwellingDetailListContract.Presenter presenter;
	private DwellingDetailAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dwelling_detail_view);

		recyclerView = findViewById(R.id.dwelling_detail_recycler);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		presenter = new DwellingDetailViewPresenter(this);

		long dwellingId = getIntent().getLongExtra("dwelling_id", -1);
		presenter.loadDwelling(dwellingId);
	}

	@Override
	public void showDwelling(Dwelling dwelling) {
		adapter = new DwellingDetailAdapter(dwelling);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void showError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}