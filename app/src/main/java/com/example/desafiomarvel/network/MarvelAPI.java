package com.example.desafiomarvel.network;

import com.example.desafiomarvel.model.ComicsResponse;
import com.example.desafiomarvel.model.Data;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelAPI {

    @GET("comics")
    Observable<ComicsResponse> getAllComics(@Query("Format")String format,
                                            @Query("formatType") String formatType,
                                            @Query("noVariants") boolean noVariants,
                                            @Query("orderBy") String orderBy,
                                            @Query("ts") String ts,
                                            @Query("hash") String hash,
                                            @Query("apikey")String apikey);

}
