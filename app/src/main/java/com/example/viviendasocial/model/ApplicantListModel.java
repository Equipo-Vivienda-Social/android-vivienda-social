package com.example.viviendasocial.model;


import com.example.viviendasocial.api.ApplicantApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.ApplicantListContract;
import com.example.viviendasocial.domain.Applicant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicantListModel implements ApplicantListContract.Model {

    @Override
    public void loadApplicants(OnLoadListener listener) {
        ApplicantApiInterface api = ViviendaSocialApi.buildService(ApplicantApiInterface.class);
        Call<List<Applicant>> getApplicantsCall = api.getApplicants();
        getApplicantsCall.enqueue(new Callback<List<Applicant>>() {
            @Override
            public void onResponse(Call<List<Applicant>> call, Response<List<Applicant>> response) {
                if (response.code() == 200) {
                    List<Applicant> applicants = response.body();
                    listener.OnLoadSuccess(applicants);
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
