package com.example.okcomputers.darkskyclientapi.Services;

import Models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by OK Computers on 10/10/2018.
 */

public interface WeatherService {
    @GET("{lat},{aLong}")
    Call<Weather> getWeather(@Path("lat") double lat, @Path("aLong") double aLong);
}
