package com.runtime.langs.app.mylanguages.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {


    public static final String base_url = "https://raw.githubusercontent.com/MichaelBourkeCF/TestResources/master/";

    private static ApiService instance;
    private final ApiInterface service;

    public synchronized static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public ApiService() {
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(base_url)// Normally in Build config or strings res file
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retro.create(ApiInterface.class);

    }

    /**
     *
     * Load the list of languages
     *
     */
    public Single<LangPayload> getLanguages() {
        return service
                .listLanguages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
