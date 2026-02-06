package com.example.viviendasocial.model;

import com.example.viviendasocial.api.DwellingApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.DwellingListContract;
import com.example.viviendasocial.domain.Dwelling;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DwellingListModel implements DwellingListContract.Model {
    @Override
    public void loadDwellings(OnLoadListener listener) {
        DwellingApiInterface api = ViviendaSocialApi.buildService(DwellingApiInterface.class);
        Call<List<Dwelling>> getAllCall = api.getAllDwellings();
        getAllCall.enqueue(new Callback<List<Dwelling>>() {
            @Override
            public void onResponse(Call<List<Dwelling>> call, Response<List<Dwelling>> response) {
                if (response.code() == 200) {
                    List<Dwelling> dwellingList = response.body();
                    listener.onLoadSuccesful(dwellingList);
                } else if (response.code() == 500) {
                    listener.onLoadError("Internal server error");
                }
            }

            @Override
            public void onFailure(Call<List<Dwelling>> call, Throwable t) {
                listener.onLoadError("Couldn't connect to the server");
            }
        });
    }
}
