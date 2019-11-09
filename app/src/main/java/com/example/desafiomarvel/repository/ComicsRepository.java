package com.example.desafiomarvel.repository;

import com.example.desafiomarvel.model.ComicsResponse;

import io.reactivex.Observable;

import static com.example.desafiomarvel.network.APIService.getApiService;

public class ComicsRepository {
    public Observable<ComicsResponse> getComics(String format, String formatType, boolean noVariants,
                                                String orderBy, String ts, String hash,String apikey){
        return getApiService().getAllComics(format, formatType,
                noVariants, orderBy, ts, hash, apikey);
    }

}
