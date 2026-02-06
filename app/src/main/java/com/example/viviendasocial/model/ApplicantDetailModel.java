package com.example.viviendasocial.model;

import com.example.viviendasocial.api.ApplicantApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.ApplicantDetailContract;
import com.example.viviendasocial.domain.Applicant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicantDetailModel implements ApplicantDetailContract.Model {
    @Override
    public void loadApplicantById(long id, OnLoadApplicant listener) {
        ApplicantApiInterface api = ViviendaSocialApi.buildService(ApplicantApiInterface.class);
        Call<Applicant> getByIdCall = api.getApplicantById(id);
        getByIdCall.enqueue(new Callback<Applicant>() {
            @Override
            public void onResponse(Call<Applicant> call, Response<Applicant> response) {
                if (response.code() == 200) {
                    listener.onLoadSuccess(response.body());
                } else if (response.code() == 404) {
                    listener.onLoadError("Applicant couln't be found");
                }
            }

            @Override
            public void onFailure(Call<Applicant> call, Throwable t) {
                listener.onLoadError("Couldn't connect to the server");
            }
        });
    }
}
