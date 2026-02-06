package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.DwellingListContract;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.model.DwellingListModel;

import java.util.List;

public class DwellingListPresenter implements DwellingListContract.Presenter,
        DwellingListContract.Model.OnLoadListener{

    private DwellingListContract.Model model;
    private DwellingListContract.View view;

    public DwellingListPresenter(DwellingListContract.View view) {
        this.view = view;
        model = new DwellingListModel();
    }

    @Override
    public void onLoadSuccesful(List<Dwelling> dwellings) {
        view.showDwellings(dwellings);
        view.showMessage("Dwellings have been loaded successfully");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void loadDwellings() {
        model.loadDwellings(this);
    }
}
