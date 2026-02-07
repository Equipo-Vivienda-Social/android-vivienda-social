package com.example.viviendasocial.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viviendasocial.R;
import com.example.viviendasocial.contract.DwellingRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.presenter.DwellingRegisterPresenter;
import com.example.viviendasocial.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DwellingRegisterView extends AppCompatActivity implements DwellingRegisterContract.View {

	private Spinner spinnerApplicant;
	private DwellingRegisterContract.Presenter presenter;
	private List<Applicant> applicantList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dwelling_register_view);

		spinnerApplicant = findViewById(R.id.spinner_Applicant_Name);

		Dwelling dwelling = (Dwelling) getIntent().getSerializableExtra("dwelling");
		presenter = new DwellingRegisterPresenter(this, dwelling);
		presenter.loadApplicants();


		if (dwelling != null) {
			((EditText) findViewById(R.id.dwelling_detail_street)).setText(dwelling.getStreet());
			((EditText) findViewById(R.id.dwelling_detail_city)).setText(dwelling.getCity());
			((EditText) findViewById(R.id.dwelling_detail_type)).setText(dwelling.getType());
			((EditText) findViewById(R.id.dwelling_room)).setText(String.valueOf(dwelling.getRoom()));
			((EditText) findViewById(R.id.dwelling_buildDate)).setText(DateUtil.formatDate(dwelling.getBuildDate()));
			((CheckBox) findViewById(R.id.dwelling_detail_available)).setChecked(dwelling.isAvailable());

		}
	}

	public void registerDwelling(View view) {
		String street =((EditText) findViewById(R.id.dwelling_detail_street)).getText().toString();
		String city = ((EditText) findViewById(R.id.dwelling_detail_city)).getText().toString();
		String type = ((EditText) findViewById(R.id.dwelling_detail_type)).getText().toString();
		int room = Integer.parseInt(((EditText) findViewById(R.id.dwelling_room)).getText().toString());
		LocalDate buildDate = DateUtil.parseDate(((EditText) findViewById(R.id.dwelling_buildDate)).getText().toString());
		boolean available = ((CheckBox) findViewById(R.id.dwelling_detail_available)).isChecked();

		long applicantId = getSelectedApplicantId();
		List<Long> applicantsIds = new ArrayList<>();
		applicantsIds.add(applicantId);

		Dwelling dwelling = (Dwelling) getIntent().getSerializableExtra("dwelling");
		boolean isEditing = dwelling != null;

		confirmRegisterDwelling(street, city, type, room, buildDate, available, applicantsIds, isEditing);
	}


	private void confirmRegisterDwelling(String street, String city, String type, int room,
	                                     LocalDate buildDate, boolean available, List<Long> applicantsIds,
	                                     boolean isEditing) {
		if (isEditing) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setMessage("¿Are you sure you want to modify the dwelling?")
					.setPositiveButton("Modify", (dialogInterface, i) -> {
						presenter.registerDwelling(street, city, type, room, buildDate, available, applicantsIds);
					})
					.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
			alert.create().show();
		} else {
			presenter.registerDwelling(street, city, type, room, buildDate, available, applicantsIds);
		}
	}

	private long getSelectedApplicantId() {
		int positionApplicantSelected = spinnerApplicant.getSelectedItemPosition();
		return applicantList.get(positionApplicantSelected).getId();
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showApplicants(List<Applicant> applicants) {
		applicantList = applicants;

		List<String> names =new ArrayList<>();
		for (Applicant ap : applicants) {
			names.add(ap.getName() + " " + ap.getSurname());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, names);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerApplicant.setAdapter(adapter);
	}

	@Override
	public void navigateToDwellingListView() {
		Intent intent = new Intent(this, DwellingListView.class);
		startActivity(intent);
		finish();
	}
}