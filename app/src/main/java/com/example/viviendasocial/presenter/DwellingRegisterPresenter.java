package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.DwellingRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.domain.Dwelling;
import com.example.viviendasocial.model.DwellingRegisterModel;

import java.time.LocalDate;
import java.util.List;

public class DwellingRegisterPresenter implements DwellingRegisterContract.Presenter,
		DwellingRegisterContract.Model.OnRegisterListener {

	private DwellingRegisterContract.Model model;
	private DwellingRegisterContract.View view;

	public DwellingRegisterPresenter(DwellingRegisterContract.View view) {
		model = new DwellingRegisterModel();
		this.view = view;
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

		model.registerDwelling(dwelling, this);
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
}
