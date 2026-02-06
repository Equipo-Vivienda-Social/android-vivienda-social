package com.example.viviendasocial.api;

import com.example.viviendasocial.domain.Dwelling;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DwellingApiInterface {

    @GET("dwellings")
    Call<List<Dwelling>> getAllDwellings();

    @GET("dwellings/{id}")
    Call<Dwelling> getDwellingById(@Path("id") long id);

    @POST("dwellings")
    Call<Dwelling> addDwelling(@Body Dwelling dwelling);

    @PUT("dwellings/{id}")
    Call<Dwelling> modifyDwelling(@Path("id") long id, @Body Dwelling dwelling);

    @DELETE("dwellings/{id}")
    Call<Void> deleteDwelling(@Path("id") long id);

}
