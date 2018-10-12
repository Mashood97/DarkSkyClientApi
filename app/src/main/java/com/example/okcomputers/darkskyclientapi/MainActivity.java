package com.example.okcomputers.darkskyclientapi;

import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okcomputers.darkskyclientapi.Services.WeatherEvent;
import com.example.okcomputers.darkskyclientapi.Services.WeatherService;
import com.example.okcomputers.darkskyclientapi.Services.WeatherServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import Models.Currently;
import Models.Weather;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

        @BindView(R.id.tempTextView)
    TextView tempTextView;
        @BindView(R.id.iconImageView)
        ImageView iconImageView;
        @BindView(R.id.summaryTextView)
    TextView summaryTextView;
        @BindView(R.id.TimeTextView)
    TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCurrentWeather(30.3753 , 69.3451);
        ButterKnife.bind(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent event)
    {
        Currently currently = event.getWeather().getCurrently();
        tempTextView.setText(String.valueOf(Math.round(currently.getTemperature())));
        summaryTextView.setText(currently.getSummary());

        //convert unix time into normal time
        long itemLong = (long) (currently.getTime()/1000);
        java.util.Date d = new java.util.Date(itemLong*1000L);
        String itemDateStr = new SimpleDateFormat("dd-MMM HH:mm").format(d);
        Log.e("Time",itemDateStr);

        timeTextView.setText(String.valueOf(itemDateStr));
        iconImageView.setImageResource(WeatherUtils.ICONS.get(currently.getIcon()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent)
    {
        Toast.makeText(getApplicationContext(),errorEvent.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }
    private void requestCurrentWeather(double lat, double Long) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat,Long);
    }
}
