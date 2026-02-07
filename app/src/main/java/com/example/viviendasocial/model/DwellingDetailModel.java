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
}
