package com.example.viviendasocial.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.adapter.DwellingAdapter;
import com.example.viviendasocial.contract.DwellingListContract;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.presenter.DwellingListPresenter;

import java.util.ArrayList;
import java.util.List;

public class DwellingListView extends AppCompatActivity implements DwellingListContract.View {

    private List<Dwelling> dwellingList;
    private DwellingAdapter adapter;
    private DwellingListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new DwellingListPresenter(this);
        dwellingList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.dwelling_list);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DwellingAdapter(this, dwellingList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadDwellings();
    }

    @Override
    public void showDwellings(List<Dwelling> dwellings) {
        dwellingList.clear();
        dwellingList.addAll(dwellings);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_getAllApplicants) { //si se selecciona la opción de registrar evento
            Intent intent = new Intent(this, ApplicantListView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_register) {
            Intent intent = new Intent(this, DwellingRegisterView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}