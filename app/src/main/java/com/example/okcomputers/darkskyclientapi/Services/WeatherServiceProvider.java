package com.example.okcomputers.darkskyclientapi.Services;

import android.util.Log;

import com.example.okcomputers.darkskyclientapi.ErrorEvent;
import com.example.okcomputers.darkskyclientapi.MainActivity;

import org.greenrobot.eventbus.EventBus;

import Models.Currently;
import Models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OK Computers on 10/10/2018.
 */

public class WeatherServiceProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/dcbff592099baae357263b395c40d7a8/";
    private Retrofit retrofit;

    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getWeather(double Lat, double Long)
    {


        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather(Lat,Long);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                if (weather != null) {
                    Currently currently = weather.getCurrently();
                    Log.e(TAG, "Temperature = " + currently.getTemperature());
                    Log.e(TAG, "Time = " + currently.getTime());
                    EventBus.getDefault().post(new WeatherEvent(weather));
                }
                else {
                    Log.e(TAG,"No Response = Check Api Secret Key");
                    EventBus.getDefault().post(new ErrorEvent("No Weather Data available"));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"onFailure = unable to get Weather Data");
                 EventBus.getDefault().post(new ErrorEvent("unable to get Weather Data"));
            }
        });
    }
}
