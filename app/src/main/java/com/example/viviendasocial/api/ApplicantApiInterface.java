package com.example.viviendasocial.api;

import com.example.viviendasocial.domain.Applicant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApplicantApiInterface {

    @GET("applicants")
    Call<List<Applicant>> getApplicants();

    @GET("applicants/{id}")
    Call<Applicant> getApplicantById(@Path("id") long id);

    @POST("applicants")
    Call<Applicant> registerApplicant(@Body Applicant applicant);

    @PUT("applicants/{id}")
    Call<Applicant> modifyApplicant(@Path("id") long id, @Body Applicant applicant);

    @DELETE("applicants/{id}")
    Call<Void> deleteById(@Path("id") long id);
}
