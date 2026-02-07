package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.DwellingDetailListContract;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.model.DwellingDetailModel;

public class DwellingDetailViewPresenter implements DwellingDetailListContract.Presenter,
		DwellingDetailListContract.Model.OnLoadListener, DwellingDetailListContract.Model.onDeleteListener {

	private DwellingDetailListContract.Model model;
	private DwellingDetailListContract.View view;


	public DwellingDetailViewPresenter(DwellingDetailListContract.View view) {
		this.view = view;
		model = new DwellingDetailModel();
	}


	@Override
	public void loadDwelling(long id) {
		model.loadDwellingById(id,this);
	}

	@Override
	public void deleteDwelling(long id) {
		model.deleteDwelling(id, this);
	}

	@Override
	public void onLoadSuccess(Dwelling dwelling) {
		view.showDwelling(dwelling);
	}

	@Override
	public void onLoadError(String message) {
		view.showError(message);
	}

	@Override
	public void onDeleteSuccess() {
		view.showMessage("Dwelling has been deleted successfully");
		view.navigateToDwellingListView();	}

	@Override
	public void onDeleteError(String message) {
		view.showError(message);
	}
}
