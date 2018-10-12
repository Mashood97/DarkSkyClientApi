package com.example.okcomputers.darkskyclientapi;

import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OK Computers on 10/11/2018.
 */

public final class WeatherUtils {
public  static final Map<String,Integer> ICONS;
static {
    Map<String,Integer>  iconMap = new HashMap<>();
    iconMap.put("clear-day",R.drawable.ic_clear_day);
    iconMap.put("clear-night",R.drawable.ic_clear_night);
    iconMap.put("rain",R.drawable.ic_rain);
    iconMap.put("snow",R.drawable.ic_snow);
    iconMap.put("sleet",R.drawable.ic_sleet);
    iconMap.put("wind",R.drawable.ic_wind);
    iconMap.put("fog",R.drawable.ic_fog);
    iconMap.put("cloudy",R.drawable.ic_cloudy);
    iconMap.put("partly-cloudy-day",R.drawable.ic_partly_cloudy_day);
    iconMap.put("partly-cloudy-night",R.drawable.ic_partly_cloudy_night);
    iconMap.put("thunderstorm",R.drawable.ic_thunderstorm);
    ICONS = Collections.unmodifiableMap(iconMap);
}


}
