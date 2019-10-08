package com.example.gamingnews.request;

import com.example.gamingnews.NewsModal;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mobile on 23.03.2018.
 */

public interface NewsService {
    @GET
    Observable<NewsModal> fetchSongs(@Url String url);

}
