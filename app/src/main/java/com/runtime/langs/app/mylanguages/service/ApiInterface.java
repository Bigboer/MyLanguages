package com.runtime.langs.app.mylanguages.service;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by rudolphliebenberg on 27/03/19.
 */
public interface ApiInterface {

    @GET("data.json")
    Single<LangPayload> listLanguages();

}
