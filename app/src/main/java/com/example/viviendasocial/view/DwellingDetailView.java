package com.example.viviendasocial.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

	public void deleteDwelling(View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage("¿Are you sure you want to delete this dwelling?")
				.setPositiveButton("Delete",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								long dwelling_id = getIntent().getLongExtra("dwelling_id", -1);
								presenter.deleteDwelling(dwelling_id);
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

	public void editDwelling(View view) {
		long dwellingId = getIntent().getLongExtra("dwelling_id", -1);
		if (dwellingId == -1) return;

		Dwelling dwelling = adapter.getDwelling();

		Intent intent = new Intent(this, DwellingRegisterView.class);
		intent.putExtra("dwelling", dwelling);
		startActivity(intent);
	}

	@Override
	public void showError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void navigateToDwellingListView() {
		Intent intent = new Intent(this, DwellingListView.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void showMessage(String message) {

	}
}