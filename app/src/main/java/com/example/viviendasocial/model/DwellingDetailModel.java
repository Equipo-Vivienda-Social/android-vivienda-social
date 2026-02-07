package com.example.viviendasocial.model;

import com.example.viviendasocial.api.DwellingApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.DwellingDetailListContract;
import com.example.viviendasocial.domain.Dwelling;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DwellingDetailModel implements DwellingDetailListContract.Model {
	@Override
	public void loadDwellingById(long id, OnLoadListener listener) {
		DwellingApiInterface api = ViviendaSocialApi.buildService(DwellingApiInterface.class);
		Call<Dwelling> getByIdCall = api.getDwellingById(id);
		getByIdCall.enqueue(new Callback<Dwelling>() {
			@Override
			public void onResponse(Call<Dwelling> call, Response<Dwelling> response) {
				if (response.code() == 200) {
					listener.onLoadSuccess(response.body());
				} else if (response.code() == 404) {
					listener.onLoadError("Dwelling couldn't be found");
				}
			}

			@Override
			public void onFailure(Call<Dwelling> call, Throwable t) {
				listener.onLoadError("Couldn't connect to the server");
			}
		});
	}

	@Override
	public void deleteDwelling(long id, onDeleteListener listener) {
		DwellingApiInterface dwellingApi = ViviendaSocialApi.buildService(DwellingApiInterface.class);
		Call<Void> deleteCall = dwellingApi.deleteDwelling(id);
		deleteCall.enqueue(new Callback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				if (response.code() == 204) {
					listener.onDeleteSuccess();
				} else if (response.code() == 404) {
					listener.onDeleteError("Dwelling couldn't be found");
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				listener.onDeleteError("Couldn't connect to the server");
			}
		});
	}
}
