package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.DwellingRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.model.DwellingRegisterModel;

import java.time.LocalDate;
import java.util.List;

public class DwellingRegisterPresenter implements DwellingRegisterContract.Presenter,
		DwellingRegisterContract.Model.OnRegisterListener, DwellingRegisterContract.Model.onModifyListener {

	private DwellingRegisterContract.Model model;
	private DwellingRegisterContract.View view;
	private Dwelling currentDwelling;

	public DwellingRegisterPresenter(DwellingRegisterContract.View view, Dwelling dwelling) {
		model = new DwellingRegisterModel();
		this.view = view;
		this.currentDwelling = dwelling;
	}

	@Override
	public void registerDwelling(String street, String city, String type, int room, LocalDate buildDate, boolean available, List<Long> applicantsIds) {
		Dwelling dwelling = Dwelling.builder()
				.street(street)
				.city(city)
				.type(type)
				.room(room)
				.available(available)
				.buildDate(buildDate)
				.applicantsIds(applicantsIds)
				.build();

		if (currentDwelling == null) {
			model.registerDwelling(dwelling, this);
		} else {
			dwelling.setId(currentDwelling.getId());
			model.modifyDwelling(dwelling, this);
		}

		model.registerDwelling(dwelling, this);
	}

	@Override
	public void modifyDwelling(long id, String street, String city, String type, int room, LocalDate buildDate, boolean available, List<Long> applicantsIds) {
		registerDwelling(street,city, type, room, buildDate, available, applicantsIds);
	}

	@Override
	public void loadApplicants() {
		model.loadApplicants(new DwellingRegisterContract.Model.OnLoadApplicantsListener() {

			@Override
			public void OnLoadSuccess(List<Applicant> applicants) {
				view.showApplicants(applicants);
			}

			@Override
			public void OnLoadError(String message) {
				view.showError(message);
			}
		});
	}

	@Override
	public void onRegisterSuccess(Dwelling dwellings) {
		view.showMessage("Dwelling registered successfully");
		view.navigateToDwellingListView();
	}

	@Override
	public void onRegisterError(String message) {
		view.showError(message);
	}

	@Override
	public void onModifySuccess(Dwelling dwelling) {
		view.showMessage("Dwelling modified succesfully");
	}

	@Override
	public void onModifyError(String message) {
		view.showError(message);
	}
}
