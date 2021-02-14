package com.amin.openapp.api;

import com.amin.openapp.Constants;
import com.amin.openapp.model.TeamRecords;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LeagueApiService {
    @GET(Constants.table_path)
    Observable<TeamRecords> getRecords(@Header(Constants.API_KEY) String api_key,@Header(Constants.API_HOST) String api_host);
}
