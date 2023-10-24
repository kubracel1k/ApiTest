package com.example.apitest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("photos") // Öğeleri listeleyen endpoint
    Call<List<Photo>> getPhotos();

    @GET("photos/{id}") // Belirli bir resmi getiren endpoint
    Call<Photo> getPhotoDetail(@Path("id") int id);
}
