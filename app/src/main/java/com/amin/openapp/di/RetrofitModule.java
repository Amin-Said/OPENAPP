package com.amin.openapp.di;

import com.amin.openapp.Constants;
import com.amin.openapp.api.LeagueApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public static LeagueApiService provideLeagueApiService(){
        return new Retrofit.Builder().baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(LeagueApiService.class);
    }
}
