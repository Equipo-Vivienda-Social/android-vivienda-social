package com.example.viviendasocial.model;

import com.example.viviendasocial.api.ApplicantApiInterface;
import com.example.viviendasocial.api.ViviendaSocialApi;
import com.example.viviendasocial.contract.ApplicantRegisterContract;
import com.example.viviendasocial.domain.Applicant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicantRegisterModel implements ApplicantRegisterContract.Model {
    @Override
    public void register(Applicant applicant, OnRegisterListener listener) {
        ApplicantApiInterface api = ViviendaSocialApi.buildService(ApplicantApiInterface.class);
        Call<Applicant> postApplicantCall = api.registerApplicant(applicant);
        postApplicantCall.enqueue(new Callback<Applicant>() {
            @Override
            public void onResponse(Call<Applicant> call, Response<Applicant> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onRegisterError("Validation error");
                } else if (response.code() == 404) {
                    listener.onRegisterError("Applicant not found");
                }
            }

            @Override
            public void onFailure(Call<Applicant> call, Throwable t) {
                listener.onRegisterError("Couldn't connect to the server");
            }
        });
    }
}
