package com.example.viviendasocial.model;

import com.example.viviendasocial.api.ApplicantApiInterface;
import com.example.viviendasocial.api.DwellingApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.DwellingRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.domain.Dwelling;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DwellingRegisterModel implements DwellingRegisterContract.Model {

	@Override
	public void registerDwelling(Dwelling dwelling, OnRegisterListener listener) {
		DwellingApiInterface dwellingApi = ViviendaSocialApi.buildService(DwellingApiInterface.class);
		Call<Dwelling> postDwellingCall = dwellingApi.addDwelling(dwelling);
		postDwellingCall.enqueue(new Callback<Dwelling>() {
			@Override
			public void onResponse(Call<Dwelling> call, Response<Dwelling> response) {
				if (response.code() == 201) {
					listener.onRegisterSuccess(response.body());
				} else if (response.code() == 400) {
					listener.onRegisterError("Validation error");
				} else if (response.code() == 404) {
					listener.onRegisterError("Dwelling not found");
				}
			}

			@Override
			public void onFailure(Call<Dwelling> call, Throwable t) {
				listener.onRegisterError("Couldn't connect to the server");
			}
		});
	}

	@Override
	public void modifyDwelling(Dwelling dwelling, onModifyListener listener) {
		DwellingApiInterface api = ViviendaSocialApi.buildService(DwellingApiInterface.class);
		Call<Dwelling> putCall = api.modifyDwelling(dwelling.getId(), dwelling);
		putCall.enqueue(new Callback<Dwelling>() {
			@Override
			public void onResponse(Call<Dwelling> call, Response<Dwelling> response) {
				if (response.code() == 200) {
					listener.onModifySuccess(response.body());
				} else if (response.code() == 404) {
					listener.onModifyError("Dwelling couldn't be found");
				} else if (response.code() == 400) {
					listener.onModifyError("Bad Request");
				} else if (response.code() == 500) {
					listener.onModifyError("Internal Server error");
				}
			}

			@Override
			public void onFailure(Call<Dwelling> call, Throwable t) {
				listener.onModifyError("Couldn't connect to the server");
			}
		});
	}

	@Override
	public void loadApplicants(OnLoadApplicantsListener listener) {
		ApplicantApiInterface applicantApi = ViviendaSocialApi.buildService(ApplicantApiInterface.class);
		Call<List<Applicant>> getApplicantsCall = applicantApi.getApplicants();
		getApplicantsCall.enqueue(new Callback<List<Applicant>>() {
			@Override
			public void onResponse(Call<List<Applicant>> call, Response<List<Applicant>> response) {
				if (response.code() == 200) {
					listener.OnLoadSuccess(response.body());
				} else if (response.code() == 500) {
					listener.OnLoadError("Internal Server error");
				}
			}

			@Override
			public void onFailure(Call<List<Applicant>> call, Throwable t) {
				listener.OnLoadError("Couldn't connect to the server");
			}
		});
	}
}
