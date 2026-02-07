package com.example.viviendasocial.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.viviendasocial.R;
import com.example.viviendasocial.contract.ApplicantRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.presenter.ApplicantRegisterPresenter;
import com.example.viviendasocial.util.DateUtil;

import java.time.LocalDate;

public class ApplicantRegisterView extends AppCompatActivity implements ApplicantRegisterContract.View {

    private ApplicantRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_register_view);

        //Recogemos applicant solo si editamos
        Applicant applicant =(Applicant) getIntent().getSerializableExtra("applicant");
        presenter = new ApplicantRegisterPresenter(this, applicant);

        if (applicant != null) {
            ((EditText) findViewById(R.id.applicant_name)).setText(applicant.getName());
            ((EditText) findViewById(R.id.applicant_surname)).setText(applicant.getSurname());
            ((EditText) findViewById(R.id.applicant_dni)).setText(applicant.getDni());
            ((EditText) findViewById(R.id.applicant_birthDate)).setText(DateUtil.formatDate(applicant.getBirthDate()));
            ((EditText) findViewById(R.id.applicant_salary)).setText(String.valueOf(applicant.getSalary()));
            ((EditText) findViewById(R.id.applicant_familyMembers)).setText(String.valueOf(applicant.getFamilyMembers()));
            ((CheckBox) findViewById(R.id.applicant_employed)).setChecked(applicant.isEmployed());
        }
    }

    public void registerApplicant(View view) {
        String name = ((EditText) findViewById(R.id.applicant_name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.applicant_surname)).getText().toString();
        String dni = ((EditText) findViewById(R.id.applicant_dni)).getText().toString();
        LocalDate birthDate = DateUtil.parseDate(((EditText) findViewById(R.id.applicant_birthDate)).getText().toString());
        int salary = Integer.parseInt(((EditText) findViewById(R.id.applicant_salary)).getText().toString());
        int familyMembers = Integer.parseInt(((EditText) findViewById(R.id.applicant_familyMembers)).getText().toString());
        boolean employed = ((CheckBox) findViewById(R.id.applicant_employed)).isChecked();

        Applicant applicant = (Applicant) getIntent().getSerializableExtra("applicant");
        boolean isEditing= applicant != null;

        if (isEditing) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("¿Are you sure you want to modify the applicant?")
                    .setPositiveButton("Modify", (dialogInterface, i) -> {
                        presenter.register(name, surname, dni, birthDate,salary, familyMembers,employed);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            alert.create().show();
        } else {
            presenter.register(name, surname, dni, birthDate,salary, familyMembers,employed);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToApplicantList() {
        Intent intent = new Intent(this, ApplicantListView.class);
        startActivity(intent);
        finish();
    }
}